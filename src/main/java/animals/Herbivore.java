package animals;

import java.util.Set;

import createMap.Coordinates;
import createMap.GameMap;


public class Herbivore extends Creature {
	public Herbivore(String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
		super(typeOfAnimal, animalHealthLevel, animalAttackPower, animalSpeed);

	}

	public Herbivore(Coordinates coordinates) {
		super(coordinates);

	}

	public Herbivore() {
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
	public void makeMove(GameMap map) {
	}

	@Override
	public void IMeasureTheAttackRadius(GameMap map, Herbivore herbivore, Predator predator) {

	}

	@Override
	public void makeAttack(Herbivore herbivore, Predator predator) {

	}

	@Override
	protected Set<CoordinatesShift> getAnimalMoves() {
		return null;
	}

}
