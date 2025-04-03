package animals;

import mapManager.Coordinates;
import mapManager.EntityManager;
import objects.Grass;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, String typeOfAnimal, int animalSpeed, EntityManager entityManager) {
        super(coordinates, "Rabbit", 2, Grass.class, entityManager);
    }

    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }

    public Coordinates getHerbivorePosition() {
        return getCoordinates();
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
        new Herbivore(getCoordinates(), "Rabbit", 2, entityManager);
    }

}
