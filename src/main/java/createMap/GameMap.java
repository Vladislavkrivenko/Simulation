package createMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import objects.Grass;
import objects.Rock;
import objects.Tree;

public class GameMap {
    Coordinates coordinates;
    private static final int TOTAL_PERCENT_ENTITY = 5;
    private final SimulationMap simulationMap;

    public GameMap(int rows, int columns) {
        this.simulationMap = new SimulationMap(rows, columns);
    }

    public void FillTheMapWithObjects() {

        int totalCells = simulationMap.getTotalRows() * simulationMap.getTotalColumns();

        int countingSpawnObjectsOnTheMap = Math.max(1, (int) Math.ceil((TOTAL_PERCENT_ENTITY / 100.0) * totalCells));
        HashSet<Coordinates> occupiedCells = simulationMap.getOccupiedCells();

        for (EnumObject enumObject : EnumObject.values()) {
            int count = 0;
            while (count < countingSpawnObjectsOnTheMap) {
                Coordinates randomCell = simulationMap.getRandomCell();

                if (randomCell == null || occupiedCells.contains(randomCell)) {
                    continue;
                }

                simulationMap.setObject(randomCell, createEntity(enumObject,randomCell));
                count++;
            }
        }
    }

    public SimulationMap getSimulationMap() {
        return simulationMap;
    }

    private Entity createEntity(EnumObject enumObject,Coordinates coordinates) {
        switch (enumObject) {
            case ROCK:
                return new Rock(coordinates);
            case GRASS:
                return new Grass(coordinates, 100);
            case TREE:
                return new Tree(coordinates);
            case HERBIVORE:
                return new Herbivore(coordinates, "Rabbit", 100, 25, 2);
            case PREDATOR:
                return new Predator(coordinates, "Wolf", 100, 50, 2);
            default:
                throw new IllegalArgumentException("Unknown entity type" + enumObject);

        }

    }

    public boolean isRabbitAt(Coordinates pos) {
        Entity entity = getSimulationMap().getObject(pos);
        // Проверка на карте, если там есть заяц
//        System.out.println("Перевіряємо клітинку " + pos + " -> " + (entity != null ? entity.getClass().getSimpleName() : "порожньо") + " | Це заєць? ");
        return entity instanceof Herbivore;
    }


    public List<Coordinates> getNeighbors(Coordinates pos) {
        List<Coordinates> neighbors = new ArrayList<>();
        // Перевірка на сусідні клітинки: вгору, вниз, вліво, вправо
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int newX = pos.getHorizontal() + dx[i];
            int newY = pos.getVertical() + dy[i];
            Coordinates newCoords = new Coordinates(newX, newY);

            // Перевірка, чи клітинка знаходиться в межах карти
            if (newCoords.possibleMovementsOfTheAnimal(getSimulationMap().getTotalColumns(), getSimulationMap().getTotalRows())) {
                neighbors.add(newCoords);
            }
        }
        return neighbors;
    }

    // Проверка, проходима ли клетка
    public boolean isWalkable(Coordinates pos) {
      if(simulationMap.isSquareEmpty(pos)){

      }
        return true;
    }


}
