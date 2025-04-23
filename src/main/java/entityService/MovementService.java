package entityService;

import coordinatesService.Coordinates;
import animalService.EntityManager;

public class MovementService {
    private final EntityManager entityManager;

    public MovementService(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    public void moveTo(Creature creature, Coordinates position) {
        move(creature, position);
    }

    private void move(Creature creature, Coordinates newPosition) {
        Coordinates oldPosition = creature.getCoordinates();
        entityManager.removeEntity(oldPosition, creature);

        creature.setPosition(newPosition);
        entityManager.installEntity(newPosition, creature);
    }
}
