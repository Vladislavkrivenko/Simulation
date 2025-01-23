package animals;

import createMap.Coordinates;
import createMap.Map;
import objects.ColorObject;
import objects.Grass;
import objects.Rock;
import objects.Tree;

public abstract class Creature extends Entity {
	protected String typeOfAnimal;
	protected int animalHealthLevel;
	protected int animalAttackPower;
	protected int animalSpeed;
	public Coordinates coordinates;
	public ColorObject colorObject;
	public Grass grass;
	public Rock rock;
	public Tree tree;

	public Creature(String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
		super();
		this.typeOfAnimal = typeOfAnimal;
		this.animalHealthLevel = animalHealthLevel;
		this.animalAttackPower = animalAttackPower;
		this.animalSpeed = animalSpeed;
	}

	public Creature(Coordinates coordinates, ColorObject colorObject) {
		super();
		this.coordinates = coordinates;
		this.colorObject = colorObject;
	}

	public void makeMove(Map map) {
	}

	public void IMeasureTheAttackRadius(Map map, Herbivore herbivore, Predator predator) {
	}

	public void createAnimal() {
	}

	public void makeAttack(Herbivore herbivore, Predator predator) {
	}

}
