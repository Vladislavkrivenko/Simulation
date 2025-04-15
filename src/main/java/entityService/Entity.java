package entityService;

import coordinatesService.Coordinates;
import interfacesService.ImageInterface;


public abstract class Entity implements ImageInterface {
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
