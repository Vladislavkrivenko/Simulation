package entity;

import animalService.Entity;
import coordinatesManager.Coordinates;
import interf.EntityImage;

public class Rock extends Entity implements EntityImage {


    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "🗻";
    }
}
