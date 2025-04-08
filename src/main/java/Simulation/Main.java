package Simulation;

import animals.Creature;
import animals.Entity;
import mapManager.*;
import moveAnimal.FindsTarget;
import moveAnimal.GridNavigator;
import moveAnimal.WalkabilityChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Створюємо всі необхідні об'єкти
        FindsTarget findsTarget = new FindsTarget();
        CreateEntityOnMap game = new CreateEntityOnMap(6, 6);
        game.fillTheMapWithObjects();
        GridManager gridManager = game.getGridManager();
        EntityManager entityManager = game.getEntityManager();

        // Створюємо об'єкт WalkabilityChecker для ініціалізації GridNavigator
        WalkabilityChecker walkabilityChecker = new WalkabilityChecker(entityManager);
        GridNavigator gridNavigator = new GridNavigator(gridManager, entityManager, walkabilityChecker);

        // Створюємо візуалізатор
        DrawMap drawMap = new DrawMap(gridManager, entityManager);

        List<Creature> animal = new ArrayList<>();

        for (Map.Entry<Coordinates, Entity> entry : game.getEntityManager().getLocationOfObject().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                Creature creature = (Creature) entry.getValue();
                findsTarget.configureDependencies(entityManager, gridNavigator); // Налаштовуємо залежності
                animal.add(creature);
            }
        }
        drawMap.drawingMap();

        for (Creature creature : animal) {
            creature.makeMove();
            drawMap.drawingMap();
        }

        drawMap.drawingMap();
    }
}
