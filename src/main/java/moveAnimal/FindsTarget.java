package moveAnimal;

import animals.Creature;
import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import mapManager.Coordinates;
import mapManager.EntityManager;
import objects.Grass;
import objects.Rock;
import objects.Tree;

import java.util.ArrayList;
import java.util.List;

public class FindsTarget {
    private EntityManager entityManager;
    private Creature creature;
    public static Class<? extends Entity> victim;
    SearchAlgorithm algorithm;

    public List<Coordinates> findNearestFood(EntityManager entityManager, Coordinates coordinates,
                                             Class<? extends Entity> victim) {//метод оболочка
        return findTargetFood(entityManager, coordinates, victim);

    }

    private List<Coordinates> findTargetFood(EntityManager entityManager, Coordinates predatorPosition,
                                             Class<? extends Entity> victim) {//ищет жертву
        FindsTarget.victim = victim;
        return algorithm.getBfs(entityManager, predatorPosition);

    }

    public boolean ischeckingAvailableCells(int w, int h, EntityManager entityManager) {
        return isWalkable(w, h, entityManager);
    }

    private boolean isWalkable(int w, int h, EntityManager entityManager) {//метод проверяет проходимость клетки
        Entity entity = entityManager.getEntity(new Coordinates(w, h));
        if (victim == Herbivore.class)
            return !(entity instanceof Predator || entity instanceof Rock || entity instanceof Tree);
        return !(entity instanceof Creature || entity instanceof Rock || entity instanceof Tree);
    }

    public List<Coordinates> getNeighbors(Coordinates coordinatesNeighbors, EntityManager entityManager) {
        return neighbors(coordinatesNeighbors, entityManager);
    }

    private List<Coordinates> neighbors(Coordinates pos, EntityManager entityManager) {//возвращяет доступные соседние клетки
        List<Coordinates> neighbors = new ArrayList<>();
        int cordH = pos.getMapHeight();
        int cordW = pos.getMapWidth();

        if (!(cordW - 1 < 0) && ischeckingAvailableCells(cordW - 1, cordH, entityManager))
            neighbors.add(new Coordinates(cordW - 1, cordH));

        if (!(cordW + 1 > entityManager.getTotalRows()) && ischeckingAvailableCells(cordW, cordH + 1, entityManager))
            neighbors.add(new Coordinates(cordW, cordH + 1));

        if (!(cordW + 1 > entityManager.getTotalColumns()) && ischeckingAvailableCells(cordW + 1, cordH, entityManager))
            neighbors.add(new Coordinates(cordW + 1, cordW));

        if (!(cordH - 1 < 0) && isWalkable(cordW, cordH - 1, entityManager))
            neighbors.add(new Coordinates(cordW, cordH - 1));

        return neighbors;
    }

    public boolean isFood(Coordinates pos) {
        return isTargetFood(pos);
    }

    private boolean isTargetFood(Coordinates pos) {//проверяет еда ли это
        Entity entity = entityManager.getLocationOfObject().get(pos);
        if (creature instanceof Herbivore) {
            if (entity instanceof Grass) {
                return true;
            }
            if (entity instanceof Herbivore && entity != creature) {
                return false;
            }
        }
        if (creature instanceof Predator) {
            if (entity instanceof Herbivore) {
                return true;
            }
        }
        return false;
    }

}
