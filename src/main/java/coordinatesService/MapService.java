package coordinatesService;

import animalService.EntityManager;

public class MapService {
    private final int totalRows;
    private final int totalColumns;

    public MapService(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public boolean getInsideMapBorder(Coordinates coordinatesMap) {
        return isInsideMapBorder(coordinatesMap);
    }

    private boolean isInsideMapBorder(Coordinates coordinates) {
        return coordinates.getMapWidth() >= 0 && coordinates.getMapWidth() < totalRows
                && coordinates.getMapHeight() >= 0 && coordinates.getMapHeight() < totalColumns;
    }

    public boolean getSquareEmpty(Coordinates coordinatesEmpty, EntityManager entityManager) {
        return isSquareEmpty(coordinatesEmpty, entityManager);
    }

    private boolean isSquareEmpty(Coordinates coordinates, EntityManager entityManager) {
        return !entityManager.getLocationOfObject().containsKey(coordinates);
    }

}
