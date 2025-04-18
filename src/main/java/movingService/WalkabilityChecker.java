package movingService;

import entityService.Creature;
import entityService.Entity;
import entity.Rock;
import entity.Tree;
import coordinatesService.Coordinates;
import animalService.EntityManager;

public class WalkabilityChecker {
    private final EntityManager entityManager;

    public WalkabilityChecker(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean getPassCells(Coordinates coordinatesCells) {
        return isWalkable(coordinatesCells);
    }

    private boolean isWalkable(Coordinates coordinates) {
        Entity entity = entityManager.getEntity(coordinates);
        if (entity == null) return true;
        return !(entity instanceof Rock || entity instanceof Tree || entity instanceof Creature);
    }

}
