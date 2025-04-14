package Simulation;

import animalManager.EntityManager;
import animalService.Creature;
import animalService.Entity;
import coordinatesManager.Coordinates;
import coordinatesManager.GridManager;
import mapManager.CreateEntityOnMap;
import mapManager.DrawMap;
import moveAnimal.GridNavigator;
import moveAnimal.WalkabilityChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        CreateEntityOnMap game = new CreateEntityOnMap(6, 6);
        game.fillTheMapWithObjects();

        GridManager gridManager = game.getGridManager();
        EntityManager entityManager = game.getEntityManager();

        WalkabilityChecker walkabilityChecker = new WalkabilityChecker(entityManager);
        GridNavigator gridNavigator = new GridNavigator(gridManager, entityManager, walkabilityChecker);

        DrawMap drawMap = new DrawMap(gridManager, entityManager);

        List<Creature> animal = new ArrayList<>();


        for (Map.Entry<Coordinates, Entity> entry : game.getEntityManager().getLocationOfObject().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                Creature creature = (Creature) entry.getValue();
                animal.add(creature);
            }
        }
        drawMap.drawingMap();
for(int i = 0; i < 8; i++) {
    for (Creature creature : animal) {
        creature.makeMove();
        drawMap.drawingMap();
    }
}


        //drawMap.drawingMap();
    }
}
