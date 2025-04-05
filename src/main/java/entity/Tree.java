package entity;

import animals.Entity;
import mapManager.Coordinates;
import interf.EntityImage;

public class Tree extends Entity implements EntityImage {
    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "🌳";
    }
}
