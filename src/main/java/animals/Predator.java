package animals;

import createMap.Coordinates;
import interf.EntityImage;

public class Predator extends Creature implements EntityImage {
    private static final int ANIMAL_SPEED = 2;

    public Predator(Coordinates coordinates, String typeOfAnimal, int animalSpeed) {
        super(coordinates, typeOfAnimal, animalSpeed);

    }


    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }

    public Coordinates getPredatorPosition() {
        return this.getPredatorPosition();
    }

    @Override
    public void createAnimal() {
        createPredator();

    }

    private void createPredator() {
        new Predator(getCoordinates(), "Wolf", ANIMAL_SPEED);
    }


    @Override
    public String getSprite() {
        return "🐺";
    }
}
