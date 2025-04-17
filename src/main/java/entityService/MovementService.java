package entityService;

import coordinatesService.Coordinates;
import animalService.EntityManager;

public class MovementService {
    private final EntityManager entityManager;

    public MovementService(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    public void moveTo(Creature creature, Coordinates newPosition) {
        Coordinates oldPosition = creature.getCoordinates();
        entityManager.removeObject(oldPosition, creature);

        creature.setPosition(newPosition);
        entityManager.setEntity(newPosition, creature);
    }
}
