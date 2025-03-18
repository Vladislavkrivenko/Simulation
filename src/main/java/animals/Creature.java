package animals;

import java.util.*;

import createMap.Coordinates;
import createMap.GameMap;
import createMap.SimulationMap;


public abstract class Creature extends Entity {
    protected String typeOfAnimal;
    protected int animalSpeed;

    public Creature(Coordinates coordinates, String typeOfAnimal, int animalSpeed) {
        super(coordinates);
        this.typeOfAnimal = typeOfAnimal;
        this.animalSpeed = animalSpeed;
    }

    public void makeMove(GameMap map) {
        while (true) {
            Coordinates rabbitPosition = findNearestRabbit(map, this.getCoordinates());
            if (rabbitPosition == null) {
                System.out.println("Волк не нашел зайца");
                moveRandomly(map);
                break;
            }

            List<Coordinates> path = bfs(map, this.getCoordinates(), rabbitPosition);
            if (path.size() > 1) {
                Coordinates oldPosition = this.getCoordinates();
                Coordinates newPosition = path.get(1);

                this.setPosition(newPosition);
                map.getSimulationMap().removeObject(oldPosition, this);
                System.out.println("❌ Удалено с " + oldPosition + ": " + map.getSimulationMap().getObject(oldPosition));// Видаляємо з попередньої позиції
                map.getSimulationMap().setObject(newPosition, this);
                System.out.println("✅ Добавлен на " + newPosition + ": " + map.getSimulationMap().getObject(newPosition));// Додаємо на нову позицію

                Entity entity = map.getSimulationMap().getObject(newPosition);
                if (entity instanceof Herbivore) {
                    System.out.println("🔥 Волк ест зайца " + newPosition);
                    makeAttack(map.getSimulationMap(), entity);
                }
            } else {
                System.out.println("Нету возможного движения для " + this.typeOfAnimal);
                break;
            }

        }
    }

    private void moveRandomly(GameMap map) {
        Random random = new Random();
        List<Coordinates> possibleMoves = new ArrayList<>();
        for (int rows = -animalSpeed; rows <= animalSpeed; rows++) {
            for (int colum = -animalSpeed; colum <= animalSpeed; colum++) {
                if (rows == 0 && colum == 0) continue;

                Coordinates newCoords = new Coordinates(
                        this.getCoordinates().getHorizontal() + rows,
                        this.getCoordinates().getVertical() + colum
                );

                if (map.isWalkable(newCoords)) {
                    possibleMoves.add(newCoords);
                }
            }
        }
        if (!possibleMoves.isEmpty()) {
            Coordinates newPosition = possibleMoves.get(random.nextInt(possibleMoves.size()));

            Coordinates oldPosition = this.getCoordinates();
            this.setPosition(newPosition);
            map.getSimulationMap().removeObject(oldPosition, this);
            map.getSimulationMap().setObject(newPosition, this);
        } else {

        }
    }

    public List<Coordinates> bfs(GameMap map, Coordinates start, Coordinates target) {
        Queue<Coordinates> queue = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Set<Coordinates> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();


            if (current.equals(target)) {
                return reconstructPath(cameFrom, start, target);
            }


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
            if (!current.equals(predatorPosition) && map.isRabbitAt(current)) {
                return current;
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

    private void makeAttack(SimulationMap map, Entity entity) {
        if (entity instanceof Herbivore) {
            map.removeObject(entity.getCoordinates(), entity);
        }
    }


}
