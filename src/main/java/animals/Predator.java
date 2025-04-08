package animals;

import mapManager.Coordinates;
import mapManager.EntityManager;
import mapManager.GridManager;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, String typeOfAnimal, int animalSpeed, EntityManager entityManager, GridManager gridManager) {
        super(coordinates, "Wolf", 2, Herbivore.class, entityManager, gridManager);

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
        new Predator(getCoordinates(), "Wolf", 2, entityManager, gridManager);
    }

}
