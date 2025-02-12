package animals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import createMap.Coordinates;
import createMap.GameMap;

public class Predator extends Creature {

	public Predator(String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
		super(typeOfAnimal, animalHealthLevel, animalAttackPower, animalSpeed);

	}

	public Predator(Coordinates coordinates) {
		super(coordinates);

	}

	public Predator() {
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
		return new HashSet<>(Arrays.asList(new CoordinatesShift(1, 2), new CoordinatesShift(2, 2)));
	}

}
