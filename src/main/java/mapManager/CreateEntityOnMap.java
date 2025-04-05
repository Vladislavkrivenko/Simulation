package mapManager;

import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import entity.Grass;
import entity.Rock;
import entity.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CreateEntityOnMap {
    private static final int TOTAL_PERCENT_ENTITY = 5;
    private final EntityManager entityManager;

    public CreateEntityOnMap(int rows, int columns) {
        this.entityManager = new EntityManager(rows, columns);
    }

    public void FillTheMapWithObjects() {

        int totalCells = entityManager.getTotalRows() * entityManager.getTotalColumns();

        int countingSpawnObjectsOnTheMap = Math.max(1, (int) Math.ceil((TOTAL_PERCENT_ENTITY / 100.0) * totalCells));
        Set<Coordinates> occupiedCells = entityManager.getOccupiedCells();

        for (EnumObject enumObject : EnumObject.values()) {
            int count = 0;
            while (count < countingSpawnObjectsOnTheMap) {
                Coordinates randomCell = getRandomCell();

                if (randomCell == null || occupiedCells.contains(randomCell)) {
                    continue;
                }

                entityManager.setEntity(randomCell, createEntity(enumObject, randomCell));
                count++;
            }
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private Entity createEntity(EnumObject enumObject, Coordinates coordinates) {//создания объектов
        switch (enumObject) {
            case ROCK:
                return new Rock(coordinates);
            case GRASS:
                return new Grass(coordinates, 100);
            case TREE:
                return new Tree(coordinates);
            case HERBIVORE:
                return new Herbivore(coordinates, "Rabbit", 2, entityManager);
            case PREDATOR:
                return new Predator(coordinates, "Wolf", 2, entityManager);
            default:
                throw new IllegalArgumentException("Unknown entity type" + enumObject);

        }

    }


    public Coordinates getRandomCell() {

        Random random = new Random();

        List<Coordinates> emptyCell = new ArrayList<>();

        for (int i = 0; i < entityManager.getTotalRows(); i++) {
            for (int j = 0; j < entityManager.getTotalColumns(); j++) {
                Coordinates coordinates = new Coordinates(i, j);

                if (!entityManager.getOccupiedCells().contains(coordinates)) {
                    emptyCell.add(coordinates);
                }
            }
        }

        if (emptyCell.isEmpty()) {
            return null;
        }

        return emptyCell.get(random.nextInt(emptyCell.size()));

    }

}
