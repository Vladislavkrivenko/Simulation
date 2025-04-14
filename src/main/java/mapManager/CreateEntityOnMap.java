package mapManager;

import animalManager.EntityManager;
import animalManager.EnumObject;
import animalService.Entity;
import animals.Herbivore;
import animals.Predator;
import coordinatesManager.Coordinates;
import coordinatesManager.GridManager;
import entity.Grass;
import entity.Rock;
import entity.Tree;
import moveAnimal.GridNavigator;
import moveAnimal.WalkabilityChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CreateEntityOnMap {
    private static final int TOTAL_PERCENT_ENTITY = 5;
    private final EntityManager entityManager;
    private final GridManager gridManager;
    private final GridNavigator gridNavigator;

    public CreateEntityOnMap(int rows, int columns) {
        this.gridManager = new GridManager(rows, columns);
        this.entityManager = new EntityManager();

        WalkabilityChecker walkabilityChecker = new WalkabilityChecker(entityManager);
        this.gridNavigator = new GridNavigator(gridManager, entityManager, walkabilityChecker);
    }


    public void fillTheMapWithObjects() {

        int totalCells = gridManager.getTotalRows() * gridManager.getTotalColumns();

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

    private Entity createEntity(EnumObject enumObject, Coordinates coordinates) {
        switch (enumObject) {
            case ROCK:
                return new Rock(coordinates);
            case GRASS:
                return new Grass(coordinates, 100);
            case TREE:
                return new Tree(coordinates);
            case HERBIVORE:
                return new Herbivore(coordinates, "Rabbit", 2, entityManager, gridManager, gridNavigator);
            case PREDATOR:
                return new Predator(coordinates, "Wolf", 2, entityManager, gridManager, gridNavigator);
            default:
                throw new IllegalArgumentException("Unknown entity type" + enumObject);

        }

    }


    public Coordinates getRandomCell() {

        Random random = new Random();

        List<Coordinates> emptyCell = new ArrayList<>();

        for (int i = 0; i < gridManager.getTotalRows(); i++) {
            for (int j = 0; j < gridManager.getTotalColumns(); j++) {
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

    public GridManager getGridManager() {
        return gridManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
