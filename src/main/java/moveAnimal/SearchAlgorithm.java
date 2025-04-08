package moveAnimal;

import animals.Entity;
import mapManager.Coordinates;
import mapManager.EntityManager;
import mapManager.GridManager;

import java.util.*;

public class SearchAlgorithm {
    private final FindsTarget findsTarget;
    private Coordinates targetCoordinates = null;
    private final Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
    private EntityManager entityManager;
    private GridNavigator gridNavigator;

    public SearchAlgorithm(FindsTarget findsTarget) {
        this.findsTarget = findsTarget;
    }
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setGridNavigator(GridNavigator gridNavigator) {
        this.gridNavigator = gridNavigator;
    }

    public List<Coordinates> getBfs(GridManager gridManager, Coordinates start, int maxDistance) {
        if (gridManager == null) {
            throw new IllegalStateException("entityManager is null in SearchAlgorithm.bfs");
        }

        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Integer> distance = new HashMap<>();//?

        cameFrom.clear();
        targetCoordinates = null;

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            int dist = distance.get(current);

            if (dist > maxDistance) continue;

            Entity currentEntity = entityManager.getEntity(current);
            Class<? extends Entity> victimClass = findsTarget.getVictim();

            if (victimClass != null && victimClass.isInstance(currentEntity)) {
                targetCoordinates = current;
                break;
            }


            for (Coordinates neighbor : gridNavigator.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                    distance.put(neighbor, dist + 1);
                }
            }
        }

        return getPath();
    }

    private List<Coordinates> getPath() {
        List<Coordinates> path = new LinkedList<>();
        if (targetCoordinates == null) return path;

        Coordinates step = targetCoordinates;
        while (step != null && cameFrom.containsKey(step)) {
            path.add(step);
            step = cameFrom.get(step);
        }
        Collections.reverse(path);
        return path;
    }
}


