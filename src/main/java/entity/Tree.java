package entity;

import animalService.Entity;
import coordinatesManager.Coordinates;
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
