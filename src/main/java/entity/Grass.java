package entity;

import animals.Entity;
import mapManager.Coordinates;
import interf.EntityImage;


public class Grass extends Entity implements EntityImage {
    public Grass(Coordinates coordinates, int health) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "🍀";
    }
}
