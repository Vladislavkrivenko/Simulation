package animals;

import entityService.Creature;
import coordinatesService.Coordinates;
import animalService.EntityManager;
import entity.Grass;
import coordinatesService.MapService;
import movingService.ChecksNeighbors;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, String typeOfAnimal, int animalSpeed, EntityManager entityManager, MapService mapService, ChecksNeighbors checksNeighbors) {
        super(coordinates, "Rabbit", 2, Grass.class, entityManager, mapService, checksNeighbors);
    }

    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }

    @Override
    public String getSprite() {
        return "🐰";
    }

    @Override
    public void createAnimal() {
        createHerbivore();

    }

    private void createHerbivore() {
        new Herbivore(getCoordinates(), "Rabbit", 2, entityManager, mapService, checksNeighbors);
    }

}


