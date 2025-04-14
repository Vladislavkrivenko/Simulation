package moveAnimal;

import animalManager.EntityManager;
import animalService.Entity;
import coordinatesManager.Coordinates;
import coordinatesManager.GridManager;

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

    public List<Coordinates> getBfs(GridManager gridManager, Coordinates start) {
        if (gridManager == null) {
            throw new IllegalStateException("entityManager is null in SearchAlgorithm.bfs");
        }

        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();


        cameFrom.clear();
        targetCoordinates = null;

        queue.add(start);
        visited.add(start);


        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();


            Entity currentEntity = entityManager.getEntity(current);
            Class<? extends Entity> victimClass = findsTarget.getVictim();

            if (victimClass != null && victimClass.isInstance(currentEntity)) {
                targetCoordinates = current;
                System.out.println("Їжа знайдена в " + targetCoordinates);
                break;
            }


            for (Coordinates neighbor : gridNavigator.getNeighbors(current)) {
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


