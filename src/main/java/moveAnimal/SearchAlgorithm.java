package moveAnimal;

import mapManager.Coordinates;
import mapManager.EntityManager;

import java.util.*;

public class SearchAlgorithm {
    FindsTarget findsTarget;

    public List<Coordinates> getBfs(EntityManager entityManager, Coordinates coordinates) {
        return bfs(entityManager, coordinates);
    }

    private List<Coordinates> bfs(EntityManager entityManager, Coordinates start) {
        Queue<Coordinates> queue = new LinkedList<>();//
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();//
        Set<Coordinates> visited = new HashSet<>();//
        Coordinates targetCoord = null;

        queue.add(start);//
        visited.add(queue.element());

        while (!queue.isEmpty()) {//
            visited.add(queue.element());
            Coordinates current = queue.element();//

            if (!(entityManager.isSquareEmpty(current))
                    && entityManager.getEntity(current).getClass().equals(FindsTarget.victim)) {
                targetCoord = current;
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
        List<Coordinates> path = new LinkedList<>();
        while (targetCoord != null) {
            path.add(targetCoord);
            targetCoord = cameFrom.get(targetCoord);
        }
        Collections.reverse(path);
        return path;
    }

}
