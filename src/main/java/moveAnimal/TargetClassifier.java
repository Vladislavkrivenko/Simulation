package moveAnimal;

import animals.Creature;
import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import entity.Grass;

public class TargetClassifier {
    private FindsTarget findsTarget;
    private Creature creature;

    public TargetClassifier(FindsTarget findsTarget, Creature creature) {
        this.findsTarget = findsTarget;
        this.creature = creature;
    }

    public boolean isFood(Entity entity) {
        return isTargetFood(entity);
    }

    private boolean isTargetFood(Entity entity) {
        if (findsTarget.victimClass != null && findsTarget.victimClass.isInstance(entity)) {
            return true;
        }
        if (findsTarget.getCreature() instanceof Herbivore) return entity instanceof Grass;
        if (creature instanceof Predator) return entity instanceof Herbivore;
        return false;
    }
}
