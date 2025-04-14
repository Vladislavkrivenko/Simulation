package animals;

import animalService.Creature;
import coordinatesManager.Coordinates;
import animalManager.EntityManager;
import coordinatesManager.GridManager;
import moveAnimal.GridNavigator;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, String typeOfAnimal, int animalSpeed, EntityManager entityManager, GridManager gridManager, GridNavigator gridNavigator) {
        super(coordinates, "Wolf", 2, Herbivore.class, entityManager, gridManager, gridNavigator);

    }

    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }

    @Override
    public String getSprite() {
        return "🐺";
    }

    @Override
    public void createAnimal() {
        createPredator();

    }

    private void createPredator() {
        new Predator(getCoordinates(), "Wolf", 2, entityManager, gridManager, gridNavigator);
    }

}
