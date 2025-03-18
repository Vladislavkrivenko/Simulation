package animals;

import createMap.Coordinates;
import interf.EntityImage;

public class Herbivore extends Creature implements EntityImage {

    public Herbivore(Coordinates coordinates, String typeOfAnimal, int animalSpeed) {
        super(coordinates, typeOfAnimal, animalSpeed);
    }

    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }

    public Coordinates getHerbivorePosition() {
        return getCoordinates();
    }

    @Override
    public void createAnimal() {
        createHerbivore();

    }


    private void createHerbivore() {
        new Herbivore(getCoordinates(), "Rabbit", 2);
    }


    @Override
    public String getSprite() {
        return "🐰";
    }
}
