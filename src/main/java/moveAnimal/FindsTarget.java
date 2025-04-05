package moveAnimal;

import animals.Creature;
import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import mapManager.Coordinates;
import mapManager.EntityManager;
import entity.Grass;
import entity.Rock;
import entity.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindsTarget {
    private EntityManager entityManager;
    private Creature creature;
    public static Class<? extends Entity> victim;
    private SearchAlgorithm algorithm;
    private final Set<Coordinates> visited = new HashSet<>();

    public FindsTarget() {

        this.algorithm = new SearchAlgorithm(this);
    }

    public List<Coordinates> getTargetForFood(EntityManager entityManager, Coordinates coordinates,
                                              Class<? extends Entity> victim) {
        System.out.println("Find victim " + victim.getSimpleName());
        this.entityManager = entityManager;
        this.visited.clear();
        return findTargetForFood(entityManager, coordinates, victim);

    }

    public List<Coordinates> getNeighbors(Coordinates coordinatesNeighbors, EntityManager entityManager) {
        return neighbors(coordinatesNeighbors, entityManager);
    }

    public boolean isFood(Entity entity) {
        return isTargetFood(entity);
    }

    public void setAlgorithm(SearchAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    private List<Coordinates> findTargetForFood(EntityManager entityManager, Coordinates predatorPosition,
                                                Class<? extends Entity> victim) {
        FindsTarget.victim = victim;
        return algorithm.getBfs(entityManager, predatorPosition);

    }


    private int recursiveCallCounter = 0;//remove

    private List<Coordinates> neighbors(Coordinates pos, EntityManager entityManager) {
        recursiveCallCounter++;//remove
        System.out.println("Виклик neighbors номер: " + recursiveCallCounter + " для координат: " + pos);
        List<Coordinates> neighbors = new ArrayList<>();
        int x = pos.getMapWidth();
        int y = pos.getMapHeight();

        if (visited.contains(pos)) return neighbors;
        visited.add(pos);

        if (y - 1 >= 0 && isWalkable(x, y - 1))
            neighbors.add(new Coordinates(x, y - 1));

        if (y + 1 < entityManager.getTotalRows() && isWalkable(x, y + 1))
            neighbors.add(new Coordinates(x, y + 1));

        if (x - 1 >= 0 && isWalkable(x - 1, y))
            neighbors.add(new Coordinates(x - 1, y));

        if (x + 1 < entityManager.getTotalColumns() && isWalkable(x + 1, y))
            neighbors.add(new Coordinates(x + 1, y));


        System.out.println("Return neighbors" + neighbors);
        return neighbors;
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


    private boolean isTargetFood(Entity entity) {
        if (victim != null && victim.isInstance(entity)) {
            System.out.println(victim + " = null");
        }
        if (creature instanceof Herbivore) {
            return entity instanceof Grass;

        }
        if (creature instanceof Predator) {
            return entity instanceof Herbivore;
        }

        return false;
    }


}
