package animals;

import java.util.*;

import createMap.Coordinates;
import createMap.GameMap;
import createMap.SimulationMap;
import objects.Grass;

public abstract class Creature extends Entity {
    protected String typeOfAnimal;
    protected int animalHealthLevel;
    public int attackPowerOfAnimal;
    protected int animalSpeed;

//	public Creature(Coordinates coordinates) {
//		super(coordinates);
//
//	}

    public Creature(Coordinates coordinates, String typeOfAnimal, int animalHealthLevel, int animalAttackPower, int animalSpeed) {
        super(coordinates);
        this.typeOfAnimal = typeOfAnimal;
        this.animalHealthLevel = animalHealthLevel;
        this.attackPowerOfAnimal = animalAttackPower;
        this.animalSpeed = animalSpeed;
    }


    public void makeMove(GameMap map) {
        while (true) {
            Coordinates rabbitPosition = findNearestRabbit(map, this.getCoordinates());

            if (rabbitPosition == null) {
                System.out.println("Woln dont find rabbit");
                break;
            }
            System.out.println("Найближчий заєць: " + rabbitPosition);
            List<Coordinates> path = bfs(map, this.getCoordinates(), rabbitPosition);
            if (path.size() > 1) {
                Coordinates oldPosition = this.getCoordinates();
                Coordinates newPosition = path.get(1);

                this.setPosition(newPosition);
                map.getSimulationMap().removeObject(oldPosition, this);  // Видаляємо з попередньої позиції
                map.getSimulationMap().setObject(newPosition, this);     // Додаємо на нову позицію

                Entity entity = map.getSimulationMap().getObject(newPosition);
                if (entity instanceof Herbivore) {
                    makeAttack(map.getSimulationMap(), entity);
                }
            } else {
                System.out.println("Немає можливого руху для " + this.typeOfAnimal);
                break;
            }

        }
    }

    public List<Coordinates> bfs(GameMap map, Coordinates start, Coordinates target) {
        System.out.println("Шукаємо шлях від " + start + " до " + target);
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Set<Coordinates> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            // Если достигли цели, восстанавливаем путь
            if (current.equals(target)) {
                return reconstructPath(cameFrom, start, target);
            }

            // Обрабатываем соседние клетки
            for (Coordinates neighbor : map.getNeighbors(current)) {
                if (!visited.contains(neighbor) && map.isWalkable(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        return Collections.emptyList(); // Если пути нет
    }


    private List<Coordinates> reconstructPath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates target) {
        List<Coordinates> path = new LinkedList<>();
        Coordinates current = target;

        while (!current.equals(start)) {
            path.add(0, current);
            current = cameFrom.get(current);
        }

        path.add(0, start); // Добавляем стартовую позицию
        return path;
    }

    private Coordinates findNearestRabbit(GameMap map, Coordinates predatorPosition) {
        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();

        queue.add(predatorPosition);
        visited.add(predatorPosition);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            // Если на текущей клетке есть заяц
            if (!current.equals(predatorPosition) && map.isRabbitAt(current)) {
                return current; // Знайшли зайця
            }

            // Добавляем соседние клетки в очередь
            for (Coordinates neighbor : map.getNeighbors(current)) {
                if (!visited.contains(neighbor) && map.isWalkable(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return null; // Если зайца не нашли
    }


    public abstract void createAnimal();

    public void setPosition(Coordinates newPosition) {
        super.setCoordinates(newPosition);


    }

    public Coordinates getPredatorPosition() {
        return getCoordinates();
    }

    public void makeAttack(SimulationMap map, Entity entity) {
        if (entity instanceof Predator && entity instanceof Herbivore) {
            map.removeObject(entity.getCoordinates(), entity);
            System.out.println(this.typeOfAnimal + " з'їв " + entity.getClass().getSimpleName() + " на клітинці " + entity.getCoordinates());
        }
    }


}
