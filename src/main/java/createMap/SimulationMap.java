package createMap;

import animals.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class SimulationMap {
    private final int totalRows;
    private final int totalColumns;
    private final HashMap<Coordinates, Entity> locationOfObject = new HashMap<>();//хеш мап который содержит соординаты и обьекты
    private final HashSet<Coordinates> occupiedCells = new HashSet<>();//хеш мапа содержит занятые клетки

    public SimulationMap(int totalRows, int totalColumns) {
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

    public void setObject(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        locationOfObject.put(coordinates, entity);
        occupiedCells.add(coordinates);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !locationOfObject.containsKey(coordinates);
    }

    public Entity getObject(Coordinates coordinates) {
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

    public Coordinates getRandomCell() {

        Random random = new Random();

        List<Coordinates> emptyCell = new ArrayList<>();

        for (int i = 0; i < getTotalRows(); i++) {
            for (int j = 0; j < getTotalColumns(); j++) {
                Coordinates coordinates = new Coordinates(i, j);

                if (!occupiedCells.contains(coordinates)) {
                    emptyCell.add(coordinates);
                }
            }
        }

        if (emptyCell.isEmpty()) {
            return null;
        }

        return emptyCell.get(random.nextInt(emptyCell.size()));

    }

    public HashSet<Coordinates> getOccupiedCells() {
        return occupiedCells;
    }

    public HashMap<Coordinates, Entity> getLocationOfObject() {
        return locationOfObject;
    }

}
