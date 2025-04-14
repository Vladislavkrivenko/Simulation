package animals;

import animalService.Creature;
import coordinatesManager.Coordinates;
import animalManager.EntityManager;
import entity.Grass;
import coordinatesManager.GridManager;
import moveAnimal.GridNavigator;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, String typeOfAnimal, int animalSpeed, EntityManager entityManager, GridManager gridManager, GridNavigator gridNavigator) {
        super(coordinates, "Rabbit", 2, Grass.class, entityManager, gridManager, gridNavigator);
    }

    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }


    @Override
    public String getSprite() {
        return "🐰";
    }

    @Override
    public void createAnimal() {
        createHerbivore();

    }


    private void createHerbivore() {
        new Herbivore(getCoordinates(), "Rabbit", 2, entityManager, gridManager, gridNavigator);
    }

}


