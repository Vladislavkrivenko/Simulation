package entityService;

import coordinatesService.CoordinateUtils;
import coordinatesService.Coordinates;
import animalService.EntityManager;
import movingService.TargetClassifier;

public class EatingService {
    private final EntityManager entityManager;
    private final TargetClassifier targetClassifier;
    private final CoordinateUtils coordinateUtils;

    public EatingService(EntityManager entityManager, TargetClassifier targetClassifier) {
        this.entityManager = entityManager;
        this.targetClassifier = targetClassifier;
        this.coordinateUtils = new CoordinateUtils();
    }

    public void eatingFood(Entity predator, Entity target) {
        eatVictim(predator, target);
    }

    private void eatVictim(Entity predator, Entity target) {
        if (target == null) {
            return;
        }

        if (!targetClassifier.isFood(target)) {
            return;
        }
        if (!coordinateUtils.getAdjacent(predator.getCoordinates(), target.getCoordinates())) {
            return;
        }

        Coordinates victimPosition = target.getCoordinates();
        entityManager.removeEntity(victimPosition, target);

    }

}
