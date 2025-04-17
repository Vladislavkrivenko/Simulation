package gameService;

import animalService.EntityManager;
import coordinatesService.Coordinates;
import coordinatesService.MapService;
import creationService.CreateEntityOnMap;
import creationService.DrawMap;
import entityService.Creature;
import entityService.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameMenu {
    private static final int START_GAME = 1;
    private static final int PAUSE = 2;
    private static final int RESUME = 3;
    private static final int ITERATE = 4;
    private static final int STOP_GAME = 5;

    private final Scanner scanner = new Scanner(System.in);
    private final SimulationController simulationController = new SimulationController();
    private final UserInput userInput = new UserInput();
    private List<Creature> animal;
    private DrawMap drawMap;
    private boolean gameStarted = false;

    public void startGame() {
        while (true) {
            startGameLoop();
        }
    }


    private void startGameLoop() {
        int choice = userInput.getGameMenu();
        switch (choice) {
            case START_GAME:
                if (!gameStarted) {
                    game();
                } else {
                    System.out.println("The game is already running.");
                }
                break;
            case PAUSE:
                if (gameStarted) {
                    simulationController.pause();
                    System.out.println("The game is paused!");
                } else {
                    System.out.println("First, launch the game.");
                }
                break;
            case RESUME:
                if (gameStarted) {
                    simulationController.resume();
                    System.out.println("Game resume!");
                } else {
                    System.out.println("First, launch the game.");
                }
                break;
            case ITERATE:
                if (gameStarted) {
                    System.out.println("One simulation step:");
                    if (animal != null && drawMap != null) {
                        simulationController.oneIteration(animal, drawMap);
                    }
                } else {
                    System.out.println("First, launch the game.");
                }
                break;
            case STOP_GAME:
                if (gameStarted) {
                    simulationController.stop();
                    System.out.println("Game stopped.");
                    gameStarted = false;
                } else {
                    System.out.println("The simulation has not yet started.");
                }
                break;
            default:
                System.out.println("Unknown team.");

        }
    }

    private void game() {
        System.out.println("The game has begun!");
        int width = userInput.getInputNumbersFromUser("Enter the map width:");
        int height = userInput.getInputNumbersFromUser("Enter the height of the map:");
        CreateEntityOnMap game = new CreateEntityOnMap(width, height);
        game.fillTheMapWithObjects();

        MapService mapService = game.getGridManager();
        EntityManager entityManager = game.getEntityManager();
        drawMap = new DrawMap(mapService, entityManager);
        animal = new ArrayList<>();


        for (Map.Entry<Coordinates, Entity> entry : game.getEntityManager().getLocationOfObject().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                Creature creature = (Creature) entry.getValue();
                animal.add(creature);
            }
        }
        drawMap.drawingMap();

        Thread simulationThread = new Thread(() -> simulationController.runSimulationLoop(animal, drawMap));
        simulationThread.start();
        gameStarted = true;
    }

}
