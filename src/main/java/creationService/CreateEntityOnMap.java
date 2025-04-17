package creationService;

import animalService.EntityManager;
import animalService.EnumObject;
import entityService.Entity;
import animals.Herbivore;
import animals.Predator;
import coordinatesService.Coordinates;
import coordinatesService.MapService;
import entity.Grass;
import entity.Rock;
import entity.Tree;
import movingService.ChecksNeighbors;
import movingService.WalkabilityChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CreateEntityOnMap {
    private static final int TOTAL_PERCENT_ENTITY = 5;
    private final EntityManager entityManager;
    private final MapService mapService;
    private final ChecksNeighbors checksNeighbors;

    public CreateEntityOnMap(int rows, int columns) {
        this.mapService = new MapService(rows, columns);
        this.entityManager = new EntityManager();

        WalkabilityChecker walkabilityChecker = new WalkabilityChecker(entityManager);
        this.checksNeighbors = new ChecksNeighbors(mapService, entityManager, walkabilityChecker);
    }


    public void fillTheMapWithObjects() {

        int totalCells = mapService.getTotalRows() * mapService.getTotalColumns();

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
                return new Grass(coordinates);
            case TREE:
                return new Tree(coordinates);
            case HERBIVORE:
                return new Herbivore(coordinates, "Rabbit", 2, entityManager, mapService, checksNeighbors);
            case PREDATOR:
                return new Predator(coordinates, "Wolf", 2, entityManager, mapService, checksNeighbors);
            default:
                throw new IllegalArgumentException("Unknown entity type" + enumObject);

        }

    }


    public Coordinates getRandomCell() {

        Random random = new Random();

        List<Coordinates> emptyCell = new ArrayList<>();

        for (int i = 0; i < mapService.getTotalRows(); i++) {
            for (int j = 0; j < mapService.getTotalColumns(); j++) {
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

    public MapService getGridManager() {
        return mapService;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
