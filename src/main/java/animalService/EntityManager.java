package animalService;

import entityService.Entity;
import coordinatesService.Coordinates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityManager {
    private final Map<Coordinates, Entity> locationOfEntity = new HashMap<>();
    private final Set<Coordinates> occupiedCells = new HashSet<>();

    public EntityManager() {
        super();
    }

    public void installEntity(Coordinates coordinates, Entity entity) {
        setEntity(coordinates, entity);
    }

    private void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        locationOfEntity.put(coordinates, entity);
        occupiedCells.add(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return returningEntity(coordinates);
    }

    private Entity returningEntity(Coordinates coordinates) {
        if (coordinates == null) {
            System.out.println("Entity null");
        }
        return locationOfEntity.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates, Entity entity) {
        deletingEntity(coordinates, entity);
    }

    private void deletingEntity(Coordinates coordinates, Entity entity) {
        if (locationOfEntity.containsKey(coordinates) && locationOfEntity.get(coordinates).equals(entity)) {
            locationOfEntity.remove(coordinates);
        }
    }

    public Set<Coordinates> getOccupiedCells() {
        return occupiedCells;
    }

    public Map<Coordinates, Entity> getLocationOfEntity() {
        return locationOfEntity;
    }

}
