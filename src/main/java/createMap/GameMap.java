package createMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import objects.Grass;
import objects.Rock;
import objects.Tree;

public class GameMap {
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

                simulationMap.setObject(randomCell, createEntity(enumObject, randomCell));
                count++;
            }
        }
    }

    public SimulationMap getSimulationMap() {
        return simulationMap;
    }

    private Entity createEntity(EnumObject enumObject, Coordinates coordinates) {
        switch (enumObject) {
            case ROCK:
                return new Rock(coordinates);
            case GRASS:
                return new Grass(coordinates, 100);
            case TREE:
                return new Tree(coordinates);
            case HERBIVORE:
                return new Herbivore(coordinates, "Rabbit", 2);
            case PREDATOR:
                return new Predator(coordinates, "Wolf", 2);
            default:
                throw new IllegalArgumentException("Unknown entity type" + enumObject);

        }

    }

    public boolean isRabbitAt(Coordinates pos) {
        Entity entity = simulationMap.getLocationOfObject().get(pos);
        boolean result = entity instanceof Herbivore;
        return result;

    }


    public List<Coordinates> getNeighbors(Coordinates pos) {
        List<Coordinates> neighbors = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int newX = pos.getHorizontal() + dx[i];
            int newY = pos.getVertical() + dy[i];
            Coordinates newCoords = new Coordinates(newX, newY);


            if (newCoords.possibleMovementsOfTheAnimal(getSimulationMap().getTotalColumns(), getSimulationMap().getTotalRows())) {
                neighbors.add(newCoords);
            }
        }

        return neighbors;

    }


    public boolean isWalkable(Coordinates pos) {
        boolean result = !simulationMap.getLocationOfObject().containsKey(pos);
        return result;
    }


}
