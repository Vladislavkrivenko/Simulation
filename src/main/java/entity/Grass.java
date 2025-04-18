package entity;

import entityService.Entity;
import coordinatesService.Coordinates;
import interfacesService.ImageInterface;


public class Grass extends Entity implements ImageInterface {
    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "🍀";
    }
}
