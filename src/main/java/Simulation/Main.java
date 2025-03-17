package Simulation;

import animals.Creature;
import animals.Entity;
import animals.Herbivore;
import animals.Predator;
import createMap.Coordinates;
import createMap.GameMap;
import createMap.RenderMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        GameMap game = new GameMap(6, 9);
        game.FillTheMapWithObjects();
        RenderMap render = new RenderMap();

        List<Creature> animal = new ArrayList<>();
        Iterator<Map.Entry<Coordinates, Entity>> iterator = game.getSimulationMap().getLocationOfObject().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Coordinates, Entity> ent = iterator.next();
            if (ent.getValue() instanceof Herbivore) {
                animal.add((Creature) ent.getValue());
            } else if (ent.getValue() instanceof Predator) {
                animal.add((Creature) ent.getValue());
            }
        }
        render.renderMap(game.getSimulationMap());
        for (Creature creature : animal) {

            if (game.getSimulationMap().getLocationOfObject().containsValue(creature)) {
                System.out.println("Тварина рухається: " + creature.getClass().getSimpleName() + " на позиції " + creature.getCoordinates());
                creature.makeMove(game);

            }
            render.renderMap(game.getSimulationMap());
        }


        render.renderMap(game.getSimulationMap());

        //	Iterator iterator =  gam.iterator();

        int num = 123;
    }

}
