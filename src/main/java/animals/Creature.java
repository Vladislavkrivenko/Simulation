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
        this.findsTarget.setCreature(this);


    }

    public abstract void createAnimal();

    public void makeMove() {
        if (victim == null) {
            System.out.println("Отсутствует клас жертви для " + this.typeOfAnimal);
            return;
        }
        while (true) {
            List<Coordinates> path = findsTarget.getTargetForFood(entityManager, this.getCoordinates(), victim);//вызов метода поиска цели, передаем ему(екземпляр класа из методами управления, координ с которой начинаем поиски, и жертву что ищем)
            if ((path == null) || path.isEmpty() || (path.size() < 2)) {
                System.out.println(this.typeOfAnimal + " не нашел " + victim);
                moveRandomly();
                break;
            }


            Coordinates oldPosition = this.getCoordinates();
            Coordinates newPosition = path.get(1);

            Entity entityAtTarget = entityManager.getEntity(newPosition);
            if (findsTarget.isFood(entityAtTarget)) {
                eatVictim(entityManager, entityAtTarget);
                System.out.println(this.typeOfAnimal + " їсть жертву на " + newPosition);
            }

            System.out.println("Удалено с " + oldPosition + ": " + entityManager.getEntity(oldPosition));

            entityManager.removeObject(oldPosition, this);
            this.setPosition(newPosition);
            entityManager.setEntity(newPosition, this);

            System.out.println("Добавлен на " + newPosition + ": " + entityManager.getEntity(newPosition));
            break;
        }


    }

    private void moveRandomly() {
        Random random = new Random();
        List<Coordinates> possibleMoves = new ArrayList<>();
        for (int rows = -animalSpeed; rows <= animalSpeed; rows++) {
            for (int colum = -animalSpeed; colum <= animalSpeed; colum++) {
                if (rows == 0 && colum == 0)
                    continue;

                Coordinates newCoordinates = new Coordinates(this.getCoordinates().getMapWidth() + rows,
                        this.getCoordinates().getMapHeight() + colum);

                if (entityManager.isInsideMapBorder(newCoordinates) && entityManager.isSquareEmpty(newCoordinates)) {
                    possibleMoves.add(newCoordinates);
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
        if (!findsTarget.isFood(entity)) {
            System.out.println("This object is not food.");
        } else {
            Coordinates eatCoordinates = entity.getCoordinates();
            entityManager.removeObject(eatCoordinates, entity);
            System.out.println("Жертва сьедена и  удалена " + eatCoordinates + ": " + entity);
        }
    }

}
