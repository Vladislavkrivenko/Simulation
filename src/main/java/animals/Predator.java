package animals;

import entityService.Creature;
import coordinatesService.Coordinates;
import animalService.EntityManager;
import coordinatesService.MapService;
import movingService.ChecksNeighbors;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, String typeOfAnimal, int animalSpeed, EntityManager entityManager, MapService mapService, ChecksNeighbors checksNeighbors) {
        super(coordinates, "Wolf", 2, Herbivore.class, entityManager, mapService, checksNeighbors);

    }

    @Override
    public void setPosition(Coordinates newPosition) {
        super.setPosition(newPosition);
    }

    @Override
    public String getSprite() {
        return "🐺";
    }

    @Override
    public void createAnimal() {
        createPredator();

    }

    private void createPredator() {
        new Predator(getCoordinates(), "Wolf", 2, entityManager, mapService, checksNeighbors);
    }

}
