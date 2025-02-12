package animals;

import java.util.HashSet;
import java.util.Set;

import createMap.Coordinates;
import createMap.GameMap;
import createMap.SimulationMap;



public abstract class Creature extends Entity {
    protected String typeOfAnimal;
    protected int animalHealthLevel;
    protected int attackPowerOfAnimal;
    protected int animalSpeed;
    public Coordinates coordinates;
//	public Grass grass;
//	public Rock rock;
//	public Tree tree;

    public Creature() {

    }

    public Creature(Coordinates coordinates) {
        super();
        this.coordinates = coordinates;
    }


    public Creature(String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
        super();
        this.typeOfAnimal = typeOfAnimal;
        this.animalHealthLevel = animalHealthLevel;
        this.attackPowerOfAnimal = animalAttackPower;
        this.animalSpeed = animalSpeed;
    }


    public Set<Coordinates> getMoveAnimals(SimulationMap map) {
        Set<Coordinates> result = new HashSet<Coordinates>();

        for (CoordinatesShift shift : getAnimalMoves()) {
            if (coordinates.possibleMovementsOfTheAnimal(shift, map.getTotalColumns(), map.getTotalRows())) {
                Coordinates newCoordinates = coordinates.ShiftAnimal(shift);

                if (isCellAvailableForMove(newCoordinates, map)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }


    protected abstract Set<CoordinatesShift> getAnimalMoves();

    public void makeMove(GameMap map) {

    }

    public void IMeasureTheAttackRadius(GameMap map, Herbivore herbivore, Predator predator) {
    }

    public void createAnimal() {
    }

    public void makeAttack(Herbivore herbivore, Predator predator) {
    }

    private boolean isCellAvailableForMove(Coordinates coordinates, SimulationMap map) {
        return map.isSquareEmpty(coordinates);
    }

}
