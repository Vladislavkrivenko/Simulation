package animals;

import createMap.Coordinates;
import createMap.Map;
import objects.ColorObject;

public class Herbivore extends Creature {

	public Herbivore(String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
		super(typeOfAnimal, animalHealthLevel, animalAttackPower, animalSpeed);

	}

	public Herbivore(Coordinates coordinates, ColorObject color) {
		super(coordinates, color);

	}

	@Override
	public void createAnimal() {
		createHerbivore();

	}

	private Herbivore createHerbivore() {
		Herbivore herbivore = new Herbivore("Rabbit", 100, 25, 100);
		return herbivore;
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
