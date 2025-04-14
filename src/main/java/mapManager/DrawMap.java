package mapManager;

import animalManager.EntityManager;
import coordinatesManager.GridManager;
import animalService.Entity;
import coordinatesManager.Coordinates;
import interf.EntityImage;

public class DrawMap {
    private final GridManager gridManager;
    private final EntityManager entityManager;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_SPRITE = "\033[38;5;94m";//
    public static final String SQUARE_UNICODE = "🏿";

    public DrawMap(GridManager gridManager, EntityManager entityManager) {
        this.gridManager = gridManager;
        this.entityManager = entityManager;
    }

    public void drawingMap() {
        for (int colum = 0; colum < gridManager.getTotalColumns(); colum++) {
            StringBuilder line = new StringBuilder();
            for (int rows = 0; rows < gridManager.getTotalRows(); rows++) {
                Coordinates coordinates = new Coordinates(colum, rows);
                if (gridManager.getSquareEmpty(coordinates, entityManager)) {
                    line.append(getSpriteForEmptySquare(coordinates));

                } else {
                    line.append(getEntitySprite(entityManager.getEntity(coordinates)));
                }

            }
            line.append(ANSI_RESET);
            System.out.println(line);
        }
        System.out.println("");
    }


    private String getSpriteForEmptySquare(Coordinates coordinates) {
        return colorizeSprite(EMPTY_SPRITE + SQUARE_UNICODE);
    }

    private String getEntitySprite(Entity entity) {
        if (entity != null) {
            return ((EntityImage) entity).getSprite();
        }
        return "Unknown image";
    }


    private String colorizeSprite(String sprite) {
        return sprite + ANSI_RESET;
    }

}
