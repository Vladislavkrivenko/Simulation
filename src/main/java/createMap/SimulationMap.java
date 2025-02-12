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
    private final HashMap<Coordinates, Entity> locationOfObject = new HashMap<>();
    private final HashSet<Coordinates> occupiedCells = new HashSet<>();//
    //private List<Coordinates> availableCells = new ArrayList<>();//

    public SimulationMap(int totalRows, int totalColumns) {
        super();
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        //initializeAvailableCells();
    }

//    private void initializeAvailableCells() {
//        for (int i = 0; i < totalRows; i++) {
//            for (int j = 0; j < totalColums; j++) {
//                //	availableCells.add(new Coordinates(i, j));
//            }
//        }
//    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setObject(Coordinates coordinates, Entity object) {
        object.coordinates = coordinates;
        locationOfObject.put(coordinates, object);
        occupiedCells.add(coordinates);
        //availableCells.remove(coordinates);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !locationOfObject.containsKey(coordinates);
    }

    public Entity getObject(Coordinates coordinates) {
        return locationOfObject.get(coordinates);
    }

    public void removeObject(Coordinates coordinates) {
        locationOfObject.remove(coordinates);
        occupiedCells.remove(coordinates);
        //availableCells.add(coordinates);
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

}
