package gameService;

import animals.Herbivore;
import creationService.DrawMap;
import entityService.Creature;

import java.util.List;

public class SimulationController {
    private final Object pauseLock = new Object();
    private boolean paused = false;
    private boolean running = true;

    public void pause() {
        synchronized (pauseLock) {
            paused = true;
        }
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public void stop() {
        synchronized (pauseLock) {
            running = false;
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public void runSimulationLoop(List<Creature> animals, DrawMap drawMap) {
        while (running) {
            synchronized (pauseLock) {
                while (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            if (noHerbivoresLeft(animals)) {
                System.out.println("There are no hares left on the map! The game ends.");
                stop();
                break;
            }

            for (Creature creature : animals) {
                creature.makeMove();
            }
            drawMap.drawingMap();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void oneIteration(List<Creature> animals, DrawMap drawMap) {
        synchronized (pauseLock) {
            for (Creature creature : animals) {
                creature.makeMove();
            }
            drawMap.drawingMap();
        }
    }

    private boolean noHerbivoresLeft(List<Creature> animals) {
        for (Creature creature : animals) {
            if (creature instanceof Herbivore) {
                return false;
            }
        }
        return true;
    }
}


