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

    public void eatVictim(Entity predator, Entity target) {
        if (target == null) {
            System.out.println("Жертва отсутствует ");
            return;
        }

        if (!targetClassifier.isFood(target)) {
            System.out.println("Это не еда.");
            return;
        }
        if (!coordinateUtils.isAdjacent(predator.getCoordinates(), target.getCoordinates())) {
            System.out.println("Жертва не поруч, не можна з'їсти: " + target);
            return;
        }

        Coordinates victimPosition = target.getCoordinates();
        entityManager.removeObject(victimPosition, target);
        System.out.println("Жертва сьедена и удалена с " + victimPosition + ": " + target);

    }

}
