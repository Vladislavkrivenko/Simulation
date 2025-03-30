package mapManager;

import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import objects.Grass;
import objects.Rock;
import objects.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class CreateEntityOnMap {
    private static final int TOTAL_PERCENT_ENTITY = 5;
    private final EntityManager getEntityManager;

    public CreateEntityOnMap(int rows, int columns) {
        this.getEntityManager = new EntityManager(rows, columns);
    }

    public void FillTheMapWithObjects() {//заполнения карты существами

        int totalCells = getEntityManager.getTotalRows() * getEntityManager.getTotalColumns();

        int countingSpawnObjectsOnTheMap = Math.max(1, (int) Math.ceil((TOTAL_PERCENT_ENTITY / 100.0) * totalCells));
        HashSet<Coordinates> occupiedCells = getEntityManager.getOccupiedCells();

        for (EnumObject enumObject : EnumObject.values()) {
            int count = 0;
            while (count < countingSpawnObjectsOnTheMap) {
                Coordinates randomCell = getRandomCell();

                if (randomCell == null || occupiedCells.contains(randomCell)) {
                    continue;
                }

                getEntityManager.setEntity(randomCell, createEntity(enumObject, randomCell));
                count++;
            }
        }
    }

    public EntityManager getEntityManager() {
        return getEntityManager;
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
                return new Herbivore(coordinates, "Rabbit", 2);
            case PREDATOR:
                return new Predator(coordinates, "Wolf", 2);
            default:
                throw new IllegalArgumentException("Unknown entity type" + enumObject);

        }

    }


    public Coordinates getRandomCell() {//рандомная клетка для спавна

        Random random = new Random();

        List<Coordinates> emptyCell = new ArrayList<>();

        for (int i = 0; i < getEntityManager.getTotalRows(); i++) {
            for (int j = 0; j < getEntityManager.getTotalColumns(); j++) {
                Coordinates coordinates = new Coordinates(i, j);

                if (!getEntityManager.getOccupiedCells().contains(coordinates)) {
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
