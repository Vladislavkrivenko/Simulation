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

        for (Map.Entry<Coordinates, Entity> entry : game.getEntityManager().getLocationOfObject().entrySet()) {//цикл который витягивает только животных
            if (entry.getValue() instanceof Creature) {
                animal.add((Creature) entry.getValue());
            }
        }
        render.drawingMap(game.getEntityManager());
        for (Creature creature : animal) {
            creature.makeMove();
            if (game.getEntityManager().getLocationOfObject().containsValue(creature)) {
            }
            render.drawingMap(game.getEntityManager());
        }

        render.drawingMap(game.getEntityManager());
    }

}
