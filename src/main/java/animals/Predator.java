package animals;

import createMap.Coordinates;
import createMap.SimulationMap;
import interf.EntityImage;

public class Predator extends Creature implements EntityImage {


    public Predator(Coordinates coordinates, String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
        super(coordinates, typeOfAnimal, animalHealthLevel, animalAttackPower, animalSpeed);

    }

//	public Predator(Coordinates coordinates) {
//		super(coordinates);
//
//	}

    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }

    public Coordinates getPredatorPosition() {
        return super.getPredatorPosition();
    }

    @Override
    public void createAnimal() {
        createPredator();

    }

    private void createPredator() {
        new Predator(getCoordinates(), "Wolf", 100, 50, 2);
    }

    @Override
    public void makeAttack(SimulationMap map, Entity entity) {
    }


    @Override
    public String getSprite() {
        return "🐺";
    }
}
