package animalService;

import coordinatesManager.Coordinates;
import animalManager.EntityManager;
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
            System.out.println("Это не еда.");
        } else {
            Coordinates victimPosition = target.getCoordinates();
            entityManager.removeObject(victimPosition, target);
            System.out.println("Жертва сьедена и удалена с " + victimPosition + ": " + target);
        }
    }

}
