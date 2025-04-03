package moveAnimal;

import mapManager.Coordinates;
import mapManager.EntityManager;

import java.util.*;

public class SearchAlgorithm {
    private final FindsTarget findsTarget;
    private Coordinates targetCoordinates = null;
    private final Map<Coordinates, Coordinates> cameFrom = new HashMap<>();

    public SearchAlgorithm(FindsTarget findsTarget) {
        this.findsTarget = findsTarget;
    }

    public List<Coordinates> getBfs(EntityManager entityManager, Coordinates coordinates) {
        return bfs(entityManager, coordinates);
    }

    private List<Coordinates> bfs(EntityManager entityManager, Coordinates start) {
        if (entityManager == null) {
            throw new IllegalStateException("entityManager is null in SearchAlgorithm.bfs");
        }
        Queue<Coordinates> queue = new LinkedList<>();//
        Set<Coordinates> visited = new HashSet<>();
        queue.add(start);
        visited.add(queue.element());

        while (!queue.isEmpty()) {
            visited.add(queue.element());
            Coordinates current = queue.poll();

            if (!(entityManager.isSquareEmpty(current))
                    && entityManager.getEntity(current).getClass().equals(FindsTarget.victim)) {
                targetCoordinates = current;
                System.out.println("validation on target");
                break;
            }
            List<Coordinates> neighbours = findsTarget.getNeighbors(current, entityManager);
            for (Coordinates square : neighbours) {
                if (!visited.contains(square) && entityManager.isInsideMapBorder(square)) {
                    queue.add(square);
                    visited.add(square);
                    cameFrom.put(square, current);
                }
            }
        }

        return getPath();
    }

    private List<Coordinates> getPath() {
        List<Coordinates> path = new LinkedList<>();
        while (targetCoordinates != null) {
            path.add(targetCoordinates);
            targetCoordinates = cameFrom.get(targetCoordinates);
        }
        Collections.reverse(path);
        System.out.println("return path");
        return path;
    }
}
