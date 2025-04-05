package moveAnimal;

import animals.Entity;
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
        cameFrom.clear();
        targetCoordinates = null;

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            visited.add(queue.element());
            Coordinates current = queue.poll();

            Entity currentEntity = entityManager.getEntity(current);
            if (FindsTarget.victim.isInstance(currentEntity)) {
                targetCoordinates = current;
                System.out.println("Найдена цель на: " + current);
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

        Coordinates step = targetCoordinates;
        while (step != null && cameFrom.containsKey(step)) {
            path.add(step);
            step = cameFrom.get(step);
        }
        Collections.reverse(path);

        if (path.isEmpty()) {
            System.out.println("Путь не найден!");
        } else {
            System.out.println("Путь найден: " + path);
        }

        return path;
    }
}
