package createMap;

import java.awt.Color;
import java.util.HashMap;

import animals.Entity;
import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import objects.ColorObject;
import objects.Grass;
import objects.Rock;
import objects.Tree;

public class Map {
	HashMap<Coordinates, Entity> locationOfObject = new HashMap<>();

	public void setObject(Coordinates coordinates, Entity object) {
		object.coordinates = coordinates;
		locationOfObject.put(coordinates, object);
	}

	public boolean isSquareEmpty(Coordinates coordinates) {
		return !locationOfObject.containsKey(coordinates);
	}

	public Entity getObject(Coordinates coordinates) {
		return locationOfObject.get(coordinates);
	}

	public void setStartAnimalPosition() {
		setObject(new Coordinates(0, 2), new Herbivore(new Coordinates(0, 2), ColorObject.WHITE));
		setObject(new Coordinates(1, 2), new Predator(new Coordinates(1, 2), ColorObject.GREY));
		setObject(new Coordinates(4, 1), new Grass(new Coordinates(4, 1), ColorObject.GREEN));
		setObject(new Coordinates(2, 1), new Rock(new Coordinates(2, 1), ColorObject.BLACK));
		setObject(new Coordinates(5, 2), new Tree(new Coordinates(5, 2), ColorObject.GREEN1));

	}
}
