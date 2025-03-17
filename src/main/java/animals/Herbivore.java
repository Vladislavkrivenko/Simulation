package animals;

import createMap.Coordinates;
import createMap.SimulationMap;
import interf.EntityImage;

public class Herbivore extends Creature implements EntityImage {

    public Herbivore(Coordinates coordinates, String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
        super(coordinates, typeOfAnimal, animalHealthLevel, animalAttackPower, animalSpeed);
//        System.out.println("Створено заєць на клітинці: " + coordinates);
    }

//	public Herbivore(Coordinates coordinates) {
//		super(coordinates);
//
//	}


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
        new Herbivore(getCoordinates(), "Rabbit", 100, 50, 2);
    }


    @Override
    public void makeAttack(SimulationMap map, Entity entity) {
    }


    @Override
    public String getSprite() {
        return "🐰";
    }
}
