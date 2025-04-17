package movingService;

import animalService.EntityManager;
import entityService.Entity;
import coordinatesService.Coordinates;
import coordinatesService.MapService;

import java.util.*;

public class SearchAlgorithm {
    private final FindsTarget findsTarget;
    private Coordinates targetCoordinates = null;
    private final Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
    private EntityManager entityManager;
    private ChecksNeighbors checksNeighbors;

    public SearchAlgorithm(FindsTarget findsTarget) {
        this.findsTarget = findsTarget;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void ChecksNeighbors(ChecksNeighbors checksNeighbors) {
        this.checksNeighbors = checksNeighbors;
    }

    public List<Coordinates> getBfs(MapService mapService, Coordinates start) {
        if (mapService == null) {
            throw new IllegalStateException("entityManager is null in SearchAlgorithm.bfs");
        }

        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();
        Class<? extends Entity> victimClass = findsTarget.getVictim();

        cameFrom.clear();
        targetCoordinates = null;

        queue.add(start);
        visited.add(start);


        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            Entity currentEntity = entityManager.getEntity(current);
            if (victimClass != null && victimClass.isInstance(currentEntity)) {
                targetCoordinates = current;
                break;
            }

            for (Coordinates neighbor : checksNeighbors.getAllNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }
        if (targetCoordinates == null) {
            return new ArrayList<>();
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


