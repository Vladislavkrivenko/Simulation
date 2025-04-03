package Simulation;

import animals.Creature;
import animals.Entity;
import mapManager.Coordinates;
import mapManager.CreateEntityOnMap;
import mapManager.DrawMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CreateEntityOnMap game = new CreateEntityOnMap(6, 6);
        game.FillTheMapWithObjects();
        DrawMap render = new DrawMap();

        List<Creature> animal = new ArrayList<>();
//
        for (Map.Entry<Coordinates, Entity> entry : game.getEntityManager().getLocationOfObject().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                animal.add((Creature) entry.getValue());
            }
        }
        render.drawingMap(game.getEntityManager());
        for (Creature creature : animal) {
            Coordinates oldPosition = creature.getCoordinates();

            if (game.getEntityManager().getLocationOfObject().containsValue(creature)) {
                creature.makeMove();
                Coordinates newPosition = creature.getCoordinates();

                if(!oldPosition.equals(newPosition)){
                    game.getEntityManager().removeObject(oldPosition,creature);
                    game.getEntityManager().setEntity(newPosition,creature);
                }
            }
            render.drawingMap(game.getEntityManager());
        }
        //
        // Iterator<Map.Entry<Coordinates, Entity>> iterator = game.getEntityManager().getLocationOfObject().entrySet().iterator();

        //        while (iterator.hasNext()) {
//            Map.Entry<Coordinates, Entity> ent = iterator.next();
//            if (ent.getValue() instanceof Herbivore) {
//                animal.add((Creature) ent.getValue());
//            } else if (ent.getValue() instanceof Predator) {
//                animal.add((Creature) ent.getValue());
//            }
//        }
//        render.drawingMap(game.getEntityManager());
//        for (Creature creature : animal) {
//
//            if (game.getEntityManager().getLocationOfObject().containsValue(creature)) {
////                if (creature instanceof Herbivore) {
////                    continue;
////                }
//                creature.makeMove();
//            }
//            render.drawingMap(game.getEntityManager());
//        }
        render.drawingMap(game.getEntityManager());
        int num = 123;
    }

}
