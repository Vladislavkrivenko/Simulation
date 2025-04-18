package animalService;

import entityService.Entity;
import coordinatesService.Coordinates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityManager {
    private final Map<Coordinates, Entity> locationOfObject = new HashMap<>();
    private final Set<Coordinates> occupiedCells = new HashSet<>();

    public EntityManager() {
        super();
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        locationOfObject.put(coordinates, entity);
        occupiedCells.add(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        if (coordinates == null) {
            System.out.println("Entity null");
        }
        return locationOfObject.get(coordinates);
    }

    public void removeObject(Coordinates coordinates, Entity entity) {
        if (locationOfObject.containsKey(coordinates) && locationOfObject.get(coordinates).equals(entity)) {
            locationOfObject.remove(coordinates);
        }
    }

    public Set<Coordinates> getOccupiedCells() {
        return occupiedCells;
    }

    public Map<Coordinates, Entity> getLocationOfObject() {
        return locationOfObject;
    }

}
