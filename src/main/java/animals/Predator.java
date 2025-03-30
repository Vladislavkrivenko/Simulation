package animals;

import mapManager.Coordinates;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, String typeOfAnimal, int animalSpeed) {
        super(coordinates, "Wolf", 2, Herbivore.class);

    }


    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }

    public Coordinates getPredatorPosition() {
        return getCoordinates();
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
        new Predator(getCoordinates(), "Wolf", 2);
    }

}
