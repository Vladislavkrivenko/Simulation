package entity;

import entityService.Entity;
import coordinatesService.Coordinates;
import interfacesService.ImageInterface;

public class Tree extends Entity implements ImageInterface {
    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getSprite() {
        return "🌳";
    }
}
