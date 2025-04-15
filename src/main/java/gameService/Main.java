package gameService;

import animalService.EntityManager;
import coordinatesService.Coordinates;
import coordinatesService.MapService;
import creationService.CreateEntityOnMap;
import creationService.DrawMap;
import entityService.Creature;
import entityService.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        CreateEntityOnMap game = new CreateEntityOnMap(9, 9);
        game.fillTheMapWithObjects();

        MapService mapService = game.getGridManager();
        EntityManager entityManager = game.getEntityManager();

        DrawMap drawMap = new DrawMap(mapService, entityManager);

        List<Creature> animal = new ArrayList<>();


        for (Map.Entry<Coordinates, Entity> entry : game.getEntityManager().getLocationOfObject().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                Creature creature = (Creature) entry.getValue();
                animal.add(creature);
            }
        }
        drawMap.drawingMap();
        for (int i = 0; i < 11; i++) {
            for (Creature creature : animal) {
                creature.makeMove();
                drawMap.drawingMap();
            }
        }

    }
}
