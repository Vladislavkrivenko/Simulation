package animals;

import mapManager.Coordinates;
import mapManager.EntityManager;
import moveAnimal.TargetClassifier;

public class EatingService {
    private final EntityManager entityManager;
    private final TargetClassifier targetClassifier;

    public EatingService(EntityManager entityManager, TargetClassifier targetClassifier) {
        this.entityManager = entityManager;
        this.targetClassifier = targetClassifier;
    }

    public void eatVictim(Entity target) {
        if (!targetClassifier.isFood(target)) {
            System.out.println("Цей об'єкт не є їжею.");
        } else {
            Coordinates victimPosition = target.getCoordinates();
            entityManager.removeObject(victimPosition, target);
            System.out.println("Жертва з'їдена та видалена з " + victimPosition + ": " + target);
        }
    }

}
