package entity;

import animals.Entity;
import mapManager.Coordinates;
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
