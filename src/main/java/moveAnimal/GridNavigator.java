package moveAnimal;

import mapManager.Coordinates;
import mapManager.EntityManager;
import mapManager.GridManager;

import java.util.ArrayList;
import java.util.List;

public class GridNavigator {
    private final GridManager gridManager;
    private final EntityManager entityManager;
    private final WalkabilityChecker walkabilityChecker;

    public GridNavigator(GridManager gridManager, EntityManager entityManager,WalkabilityChecker walkabilityChecker) {
        this.gridManager = gridManager;
        this.entityManager = entityManager;
        this.walkabilityChecker = walkabilityChecker;

    }

    public List<Coordinates> getNeighbors(Coordinates pos) {
        List<Coordinates> neighbors = new ArrayList<>();
        int x = pos.getMapWidth();
        int y = pos.getMapHeight();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            Coordinates candidate = new Coordinates(nx, ny);

            if (gridManager.getInsideMapBorder(candidate) && walkabilityChecker.getPassCells(candidate)) {
                neighbors.add(candidate);
            }
        }

        return neighbors;
    }
}
