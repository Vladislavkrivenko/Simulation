package objects;

import animals.Entity;
import createMap.Coordinates;
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
