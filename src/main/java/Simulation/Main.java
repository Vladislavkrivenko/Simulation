package Simulation;

import animals.Creature;
import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import mapManager.Coordinates;
import mapManager.CreateEntityOnMap;
import mapManager.DrawMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CreateEntityOnMap game = new CreateEntityOnMap(9, 6);
        game.FillTheMapWithObjects();
        DrawMap render = new DrawMap();

        List<Creature> animal = new ArrayList<>();
        Iterator<Map.Entry<Coordinates, Entity>> iterator = game.getEntityManager().getLocationOfObject().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Coordinates, Entity> ent = iterator.next();
            if (ent.getValue() instanceof Herbivore) {
                animal.add((Creature) ent.getValue());
            } else if (ent.getValue() instanceof Predator) {
                animal.add((Creature) ent.getValue());
            }
        }
        render.drawingMap(game.getEntityManager());
        for (Creature creature : animal) {

            if (game.getEntityManager().getLocationOfObject().containsValue(creature)) {
                creature.makeMove(game.getEntityManager(), creature.getCoordinates());
            }
            render.drawingMap(game.getEntityManager());
        }
        render.drawingMap(game.getEntityManager());
        int num = 123;
    }

}
