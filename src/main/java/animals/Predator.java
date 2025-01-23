package animals;

import createMap.Coordinates;
import createMap.Map;
import objects.ColorObject;

public class Predator extends Creature {

	public Predator(String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
		super(typeOfAnimal, animalHealthLevel, animalAttackPower, animalSpeed);

	}

	public Predator(Coordinates coordinates, ColorObject color) {
		super(coordinates, color);

	}

	@Override
	public void createAnimal() {
		createPredator();

	}

	private Predator createPredator() {
		Predator predator = new Predator("Wolf", 100, 50, 75);
		return predator;
	}

	@Override
	public void makeMove(Map map) {
	}

	@Override
	public void IMeasureTheAttackRadius(Map map, Herbivore herbivore, Predator predator) {

	}

	@Override
	public void makeAttack(Herbivore herbivore, Predator predator) {
	}

}
