package entityService;

import coordinatesService.Coordinates;
import animalService.EntityManager;
import coordinatesService.MapService;
import movingService.FindsTarget;
import movingService.ChecksNeighbors;
import movingService.SearchAlgorithm;
import movingService.TargetClassifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {
    protected String typeOfAnimal;
    protected int animalSpeed;
    protected FindsTarget findsTarget;
    protected Class<? extends Entity> victim;

    protected EntityManager entityManager;
    protected final MapService mapService;
    protected final ChecksNeighbors checksNeighbors;

    private final EatingService eatingService;
    private final TargetClassifier targetClassifier;
    private final MovementService movementService;

    public Creature(Coordinates coordinates, String typeOfAnimal, int animalSpeed, Class<? extends Entity> victim, EntityManager entityManager, MapService mapService, ChecksNeighbors checksNeighbors) {
        super(coordinates);
        this.typeOfAnimal = typeOfAnimal;
        this.animalSpeed = animalSpeed;
        this.victim = victim;
        this.entityManager = entityManager;
        this.mapService = mapService;
        this.checksNeighbors = checksNeighbors;

        this.findsTarget = new FindsTarget();
        this.targetClassifier = new TargetClassifier(findsTarget, this);
        this.eatingService = new EatingService(entityManager, targetClassifier);
        this.movementService = new MovementService(entityManager);

        SearchAlgorithm searchAlgorithm = new SearchAlgorithm(findsTarget);
        searchAlgorithm.setEntityManager(entityManager);
        searchAlgorithm.ChecksNeighbors(checksNeighbors);
        this.findsTarget.setAlgorithm(searchAlgorithm);
        this.findsTarget.setCreature(this);
        this.findsTarget.setVictim(victim);
    }

    public abstract void createAnimal();

    public void makeMove() {
        if (victim == null) {
            return;
        }

        findsTarget.setVictim(victim);

        if (checkAdjacentAndEat()) {
            return;
        }

        if (pursueAndMaybeEat()) {
            return;
        }
        moveRandomly();
    }

    private boolean checkAdjacentAndEat() {
        Coordinates currentPos = getCoordinates();

        for (Coordinates neighbor : checksNeighbors.getWalkableNeighbors(currentPos)) {
            Entity entity = entityManager.getEntity(neighbor);

            if (targetClassifier.isFood(entity)) {
                eatingService.eatingFood(this, entity);
                movementService.moveTo(this, neighbor);
                return true;
            }
        }

        return false;
    }

    private boolean pursueAndMaybeEat() {
        Coordinates currentPos = getCoordinates();
        List<Coordinates> path = findsTarget.getTargetForFood(mapService, currentPos);

        if (path == null || path.isEmpty()) {
            return false;
        }

        if (path.size() == 2) {
            Coordinates victimCoordinates = path.get(1);
            Entity entityAtTarget = entityManager.getEntity(victimCoordinates);

            if (targetClassifier.isFood(entityAtTarget)) {
                eatingService.eatingFood(this, entityAtTarget);
                movementService.moveTo(this, victimCoordinates);
                return true;
            }
        }

        Coordinates nextStep = path.get(Math.min(getAnimalSpeed(), path.size() - 1));
        movementService.moveTo(this, nextStep);
        return true;
    }


    public void moveRandomly() {
        Random random = new Random();
        List<Coordinates> possibleMoves = new ArrayList<>();

        for (int dx = -getAnimalSpeed(); dx <= getAnimalSpeed(); dx++) {
            for (int dy = -getAnimalSpeed(); dy <= getAnimalSpeed(); dy++) {
                if (Math.abs(dx) + Math.abs(dy) > getAnimalSpeed() || (dx == 0 && dy == 0)) continue;

                int newX = getCoordinates().getMapWidth() + dx;
                int newY = getCoordinates().getMapHeight() + dy;

                Coordinates newCoordinates = new Coordinates(newX, newY);

                if (mapService.getInsideMapBorder(newCoordinates) && mapService.getSquareEmpty(newCoordinates, entityManager)) {
                    possibleMoves.add(newCoordinates);
                }
            }
        }

        if (!possibleMoves.isEmpty()) {
            Coordinates randomPosition = possibleMoves.get(random.nextInt(possibleMoves.size()));
            movementService.moveTo(this, randomPosition);
        }
    }

    public int getAnimalSpeed() {
        return animalSpeed;
    }

    public void setPosition(Coordinates newPosition) {
        if (newPosition == null) {
            throw new IllegalArgumentException("New position cannot be null");
        }
        super.setCoordinates(newPosition);

    }

}
