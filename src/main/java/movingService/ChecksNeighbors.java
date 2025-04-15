package movingService;

import coordinatesService.Coordinates;
import animalService.EntityManager;
import coordinatesService.MapService;

import java.util.ArrayList;
import java.util.List;

public class ChecksNeighbors {
    private final MapService mapService;
    private final EntityManager entityManager;
    private final WalkabilityChecker walkabilityChecker;

    public ChecksNeighbors(MapService mapService, EntityManager entityManager, WalkabilityChecker walkabilityChecker) {
        this.mapService = mapService;
        this.entityManager = entityManager;
        this.walkabilityChecker = walkabilityChecker;

    }

    public List<Coordinates> getWalkableNeighbors(Coordinates pos) {
        return getNeighborsFiltered(pos, true);
    }

    public List<Coordinates> getAllNeighbors(Coordinates pos) {
        return getNeighborsFiltered(pos, false);
    }

    private List<Coordinates> getNeighborsFiltered(Coordinates pos, boolean checkWalkable) {
        List<Coordinates> neighbors = new ArrayList<>();
        int x = pos.getMapWidth();
        int y = pos.getMapHeight();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            Coordinates candidate = new Coordinates(nx, ny);

            if (mapService.getInsideMapBorder(candidate)) {
                if (!checkWalkable || walkabilityChecker.getPassCells(candidate)) {
                    neighbors.add(candidate);
                }
            }
        }

        return neighbors;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
