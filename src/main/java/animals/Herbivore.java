package animals;

import mapManager.Coordinates;
import objects.Grass;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, String typeOfAnimal, int animalSpeed) {
        super(coordinates, "Rabbit", 2, Grass.class);
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
        new Herbivore(getCoordinates(), "Rabbit", 2);
    }

}
