package mapManager;

import animals.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class EntityManager {
    private final int totalRows;
    private final int totalColumns;
    private final Map<Coordinates, Entity> locationOfObject = new HashMap<>();
    private final Set<Coordinates> occupiedCells = new HashSet<>();
    private final Map<Coordinates, Entity> eatenWhole = new HashMap<>();

    public EntityManager(int totalRows, int totalColumns) {
        super();
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        locationOfObject.put(coordinates, entity);
        occupiedCells.add(coordinates);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !locationOfObject.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return locationOfObject.get(coordinates);
    }

    public void removeObject(Coordinates coordinates, Entity entity) {
        if (locationOfObject.containsKey(coordinates) && locationOfObject.get(coordinates).equals(entity)) {
            locationOfObject.remove(coordinates);
            System.out.println("❌ объект удален " + entity.getClass().getSimpleName() + " с " + coordinates);
        } else {
            System.out.println("⚠️ Не удалось удалить " + entity.getClass().getSimpleName() + " с " + coordinates);
        }
    }


    public Set<Coordinates> getOccupiedCells() {
        return occupiedCells;
    }

    public Map<Coordinates, Entity> getLocationOfObject() {
        return locationOfObject;
    }

    public boolean isInsideMapBorder(Coordinates coordinates) {
        return coordinates.getMapWidth() >= 0 && coordinates.getMapWidth() < totalRows
                && coordinates.getMapHeight() >= 0 && coordinates.getMapHeight() < totalColumns;
    }
}
