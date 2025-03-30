package mapManager;

import animals.Entity;

import java.util.HashMap;
import java.util.HashSet;


public class EntityManager {
    private final int totalRows;
    private final int totalColumns;
    private final HashMap<Coordinates, Entity> locationOfObject = new HashMap<>();//хеш мап который содержит соординаты и обьекты
    private final HashSet<Coordinates> occupiedCells = new HashSet<>();//хеш мапа содержит занятые клетки

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
        if (locationOfObject.containsKey(coordinates) && locationOfObject.get(coordinates) == entity) {
            locationOfObject.remove(coordinates);
            System.out.println("❌ объект удален " + entity.getClass().getSimpleName() + " с " + coordinates);
        } else {
            System.out.println("⚠️ Не удалось удалить " + entity.getClass().getSimpleName() + " с " + coordinates);
        }
    }


    public HashSet<Coordinates> getOccupiedCells() {
        return occupiedCells;
    }

    public HashMap<Coordinates, Entity> getLocationOfObject() {
        return locationOfObject;
    }

    public boolean isInsideMapBorder(Coordinates coordinates) {
        return coordinates.getMapWidth() < getTotalRows() && coordinates.getMapHeight() < getTotalColumns();
    }
}
