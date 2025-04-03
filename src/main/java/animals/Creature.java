package animals;

import mapManager.Coordinates;
import mapManager.EntityManager;
import moveAnimal.FindsTarget;
import moveAnimal.SearchAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {
    protected String typeOfAnimal;
    protected int animalSpeed;
    private final SearchAlgorithm moveAnimals;
    private final FindsTarget findsTarget;
    protected Class<? extends Entity> victim;
    protected EntityManager entityManager;

    public Creature(Coordinates coordinates, String typeOfAnimal, int animalSpeed, Class<? extends Entity> victim, EntityManager entityManager) {
        super(coordinates);
        this.typeOfAnimal = typeOfAnimal;
        this.animalSpeed = animalSpeed;
        this.victim = victim;
        this.entityManager = entityManager;
        this.findsTarget = new FindsTarget();
        this.moveAnimals = new SearchAlgorithm(findsTarget);
        this.findsTarget.setAlgorithm(moveAnimals);


    }

    public abstract void createAnimal();

    public void makeMove() {
        if (victim == null) {
            System.out.println("Отсутствует клас жертви для " + this.typeOfAnimal);
            return;
        }
        while (true) {
            List<Coordinates> targetPosition = findsTarget.getTargetForFood(entityManager, this.getCoordinates(), victim);
            if (targetPosition == null || targetPosition.isEmpty()) {
                System.out.println("Охотник не нашел жертву");
                moveRandomly();
                break;
            }

            List<Coordinates> path = moveAnimals.getBfs(entityManager, this.getCoordinates());
            if (path.size() > 1) {
                Coordinates oldPosition = this.getCoordinates();
                Coordinates newPosition = path.get(1);

                this.setPosition(newPosition);
                entityManager.removeObject(oldPosition, this);
                System.out.println("Удалено с " + oldPosition + ": " + entityManager.getEntity(oldPosition));
                entityManager.setEntity(newPosition, this);
                System.out
                        .println("Добавлен на " + newPosition + ": " + entityManager.getEntity(newPosition));

                Entity entity = entityManager.getEntity(newPosition);
                if (victim.isInstance(entity)) {
                    System.out.println(" Волк ест зайца " + newPosition);
                    eatVictim(entityManager, victim.cast(entity));
                }
            } else {
                System.out.println("Нету возможного движения для " + this.typeOfAnimal);
                break;
            }

        }
    }

    private void moveRandomly() {
        Random random = new Random();
        List<Coordinates> possibleMoves = new ArrayList<>();
        for (int rows = -animalSpeed; rows <= animalSpeed; rows++) {
            for (int colum = -animalSpeed; colum <= animalSpeed; colum++) {
                if (rows == 0 && colum == 0)
                    continue;

                Coordinates newCoords = new Coordinates(this.getCoordinates().getMapWidth() + rows,
                        this.getCoordinates().getMapHeight() + colum);

                if (entityManager.isInsideMapBorder(newCoords) && entityManager.isSquareEmpty(newCoords)) {
                    possibleMoves.add(newCoords);
                }
            }
        }
        if (!possibleMoves.isEmpty()) {
            Coordinates newPosition = possibleMoves.get(random.nextInt(possibleMoves.size()));

            Coordinates oldPosition = this.getCoordinates();
            this.setPosition(newPosition);
            entityManager.removeObject(oldPosition, this);
            entityManager.setEntity(newPosition, this);

        }
    }

    public void setPosition(Coordinates newPosition) {
        if (newPosition == null) {
            throw new IllegalArgumentException("New position cannot be null");
        }
        super.setCoordinates(newPosition);

    }

    private void eatVictim(EntityManager entityManager, Entity entity) {
        if (!findsTarget.isFood(getCoordinates())) {
            System.out.println("This object is not food.");
        } else {
            entityManager.removeObject(entity.getCoordinates(), entity);
        }
    }

}
