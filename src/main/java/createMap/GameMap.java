package createMap;

import java.util.HashSet;

import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import objects.Grass;
import objects.Rock;
import objects.Tree;

public class GameMap {
    private static final int TOTAL_PERCENT_ENTITY = 10;
    private final SimulationMap simulationMap;

    public GameMap(int rows, int columns) {
        this.simulationMap = new SimulationMap(rows, columns);
    }


    public void IFillTheMapWithObjects() {

        int totalCells = simulationMap.getTotalRows() * simulationMap.getTotalColumns();

        int countingSpawnObjectsOnTheMap = Math.max(1, (int) Math.ceil((TOTAL_PERCENT_ENTITY / 100.0) * totalCells));
        HashSet<Coordinates> occupiedCells = simulationMap.getOccupiedCells();

        for (EnumObject enumObject : EnumObject.values()) {
            int count = 0;
            while (count < countingSpawnObjectsOnTheMap) {
                Coordinates randomCell = simulationMap.getRandomCell();

                if (randomCell == null || occupiedCells.contains(randomCell)) {
                    break;
                }
                simulationMap.setObject(randomCell, createEntity(enumObject));
                count++;
            }
        }
    }

    public SimulationMap getSimulationMap() {
        return simulationMap;
    }

        public void moveAnimal(Coordinates from, Coordinates to) {
        Entity entity = simulationMap.getObject(from);
        simulationMap.removeObject(from);
        simulationMap.setObject(to, entity);
    }
    private Entity createEntity(EnumObject enumObject) {
        switch (enumObject) {
            case ROCK:
                return new Rock();
            case GRASS:
                return new Grass();
            case TREE:
                return new Tree();
            case HERBIVORE:
                return new Herbivore();
            case PREDATOR:
                return new Predator();
            default:
                throw new IllegalArgumentException("Unknown entity type" + enumObject);

        }


    }


}
