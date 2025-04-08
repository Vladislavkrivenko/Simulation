package animals;

import mapManager.Coordinates;
import mapManager.EntityManager;
import entity.Grass;
import mapManager.GridManager;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, String typeOfAnimal, int animalSpeed, EntityManager entityManager, GridManager gridManager) {
        super(coordinates, "Rabbit", 2, Grass.class, entityManager, gridManager);
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
        new Herbivore(getCoordinates(), "Rabbit", 2, entityManager, gridManager);
    }

}


