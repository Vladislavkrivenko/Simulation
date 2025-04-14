package animalService;

import coordinatesManager.Coordinates;
import interf.EntityImage;


public abstract class Entity implements EntityImage {
    private Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getSprite() {
        return "";
    }
}
