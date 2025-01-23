package animals;

import createMap.Coordinates;
import objects.ColorObject;

public abstract class Entity {
	public Coordinates coordinates;
	public ColorObject color;

	public Entity(Coordinates coordinates, ColorObject color) {
		super();
		this.coordinates = coordinates;
		this.color = color;
	}

	public Entity() {
	}

}
