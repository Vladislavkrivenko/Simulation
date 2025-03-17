package objects;

import animals.Entity;
import createMap.Coordinates;
import interf.EntityImage;


public class Grass extends Entity implements EntityImage {
    public Grass(Coordinates coordinates, int h) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "🍀";
    }
}
