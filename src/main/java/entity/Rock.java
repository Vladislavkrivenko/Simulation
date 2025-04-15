package entity;

import entityService.Entity;
import coordinatesService.Coordinates;
import interfacesService.ImageInterface;

public class Rock extends Entity implements ImageInterface {


    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "🗻";
    }
}
