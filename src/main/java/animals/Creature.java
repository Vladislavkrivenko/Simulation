package animals;

import mapManager.Coordinates;
import mapManager.EntityManager;
import mapManager.GridManager;
import moveAnimal.FindsTarget;
import moveAnimal.SearchAlgorithm;
import moveAnimal.TargetClassifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {
    protected String typeOfAnimal;
    protected int animalSpeed;
    protected FindsTarget findsTarget;
    protected Class<? extends Entity> victim;
    protected EntityManager entityManager;
    private final TargetClassifier targetClassifier;
    protected final GridManager gridManager;
    private final EatingService eatingService;
    private final MovementService movementService;

    private Creature creature;

    public Creature(Coordinates coordinates, String typeOfAnimal, int animalSpeed, Class<? extends Entity> victim, EntityManager entityManager, GridManager gridManager) {
        super(coordinates);
        this.typeOfAnimal = typeOfAnimal;
        this.animalSpeed = animalSpeed;
        this.victim = victim;
        this.entityManager = entityManager;
        this.gridManager = gridManager;

        this.findsTarget = new FindsTarget();
        this.targetClassifier = new TargetClassifier(findsTarget, this);
        this.eatingService = new EatingService(entityManager, targetClassifier);
        this.movementService = new MovementService(entityManager);

        SearchAlgorithm moveAnimals = new SearchAlgorithm(findsTarget);
        this.findsTarget.setAlgorithm(moveAnimals);
        this.findsTarget.setCreature(this);
        this.findsTarget.setVictim(victim);
    }

    public abstract void createAnimal();

    public void makeMove() {
        if (victim == null) {
            System.out.println("Жертва не задана для " + this.typeOfAnimal);
            return;
        }
        findsTarget.setVictim(victim);

        List<Coordinates> path = findsTarget.getTargetForFood(gridManager, getCoordinates(), animalSpeed);
        if (path == null || path.isEmpty()) {
            System.out.println(typeOfAnimal + " не знайшов їжу.");
            moveRandomly();
            return;
        }

        if (path.size() == 2) {
            Coordinates victimCoordinates = path.get(1);
            Entity entityAtTarget = entityManager.getEntity(victimCoordinates);
            if (targetClassifier.isFood(entityAtTarget)) {
                eatingService.eatVictim(entityAtTarget);
                movementService.moveTo(this, victimCoordinates);
                System.out.println(typeOfAnimal + " з'їв жертву на " + victimCoordinates);
                return;
            }
        }

        Coordinates nextStep = path.get(Math.min(getAnimalSpeed(), path.size() - 1));
        movementService.moveTo(this, nextStep);
    }


    private void moveRandomly() {
        Random random = new Random();
        List<Coordinates> possibleMoves = new ArrayList<>();

        for (int dx = -getAnimalSpeed(); dx <= getAnimalSpeed(); dx++) {
            for (int dy = -getAnimalSpeed(); dy <= getAnimalSpeed(); dy++) {
                if (Math.abs(dx) + Math.abs(dy) > getAnimalSpeed() || (dx == 0 && dy == 0)) continue;

                int newX = getCoordinates().getMapWidth() + dx;
                int newY = getCoordinates().getMapWidth() + dy;

                Coordinates newCoordinates = new Coordinates(newX, newY);

                if (gridManager.getInsideMapBorder(newCoordinates) && gridManager.getSquareEmpty(newCoordinates, entityManager)) {
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
