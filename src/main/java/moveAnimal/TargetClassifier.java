package moveAnimal;

import animalService.Creature;
import animalService.Entity;
import animals.Herbivore;
import animals.Predator;
import entity.Grass;

public class TargetClassifier {
    private final FindsTarget findsTarget;
    private final Creature creature;

    public TargetClassifier(FindsTarget findsTarget, Creature creature) {
        this.findsTarget = findsTarget;
        this.creature = creature;
    }

    public boolean isFood(Entity entity) {
        return isTargetFood(entity);
    }

    private boolean isTargetFood(Entity entity) {
        Class<? extends Entity> victim = findsTarget.getVictim();
        System.out.println("Жертва: " + victim);
        if (victim != null) {
            return victim.isInstance(entity);
        }

        if (findsTarget.getCreature() instanceof Herbivore) {
            return entity instanceof Grass;
        }
        if (creature instanceof Predator) {
            return entity instanceof Herbivore;
        }
        return false;
    }
}
