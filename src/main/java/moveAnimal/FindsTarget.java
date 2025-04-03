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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindsTarget {
    private EntityManager entityManager;
    private Creature creature;
    public static Class<? extends Entity> victim;
    private SearchAlgorithm algorithm;
    private Set<Coordinates> visited = new HashSet<>();

    public FindsTarget() {

        this.algorithm = new SearchAlgorithm(this);
    }

    public List<Coordinates> getTargetForFood(EntityManager entityManager, Coordinates coordinates,
                                              Class<? extends Entity> victim) {
        System.out.println("Find victim " + victim.getSimpleName());
        this.entityManager = entityManager;
        return findTargetForFood(entityManager, coordinates, victim);

    }

    private List<Coordinates> findTargetForFood(EntityManager entityManager, Coordinates predatorPosition,
                                                Class<? extends Entity> victim) {
        FindsTarget.victim = victim;
        return algorithm.getBfs(entityManager, predatorPosition);

    }


    private boolean isWalkable(int w, int h) {
        Entity entity = entityManager.getEntity(new Coordinates(w, h));
        if (entity == null) {
            return true;
        }
        if (victim == Herbivore.class)
            return !(entity instanceof Predator || entity instanceof Rock || entity instanceof Tree);
        return !(entity instanceof Creature || entity instanceof Rock || entity instanceof Tree);
    }

    public List<Coordinates> getNeighbors(Coordinates coordinatesNeighbors, EntityManager entityManager) {
        return neighbors(coordinatesNeighbors, entityManager);
    }

    private int recursiveCallCounter = 0;

    private List<Coordinates> neighbors(Coordinates pos, EntityManager entityManager) {
        recursiveCallCounter++;
        System.out.println("Виклик neighbors номер: " + recursiveCallCounter + " для координат: " + pos);
        List<Coordinates> neighbors = new ArrayList<>();
        int cordH = pos.getMapHeight();
        int cordW = pos.getMapWidth();
        //
        if (visited.contains(pos)) {
            return neighbors;
        }

        visited.add(pos);
//
        if (cordW - 1 >= 0 && isWalkable(cordW - 1, cordH))
            neighbors.add(new Coordinates(cordW - 1, cordH));

        if (cordH + 1 < entityManager.getTotalColumns() && isWalkable(cordW, cordH + 1))
            neighbors.add(new Coordinates(cordW + 1, cordH));

        if (cordW + 1 < entityManager.getTotalRows() && isWalkable(cordW + 1, cordH))
            neighbors.add(new Coordinates(cordW + 1, cordH));

        if (cordH - 1 >= 0 && isWalkable(cordW, cordH - 1))
            neighbors.add(new Coordinates(cordW, cordH - 1));

        System.out.println("Return neighbors" + neighbors);
        return neighbors;
    }

    public boolean isFood(Coordinates pos) {
        return isTargetFood(pos);
    }

    private boolean isTargetFood(Coordinates pos) {
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

    public void setAlgorithm(SearchAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

}
