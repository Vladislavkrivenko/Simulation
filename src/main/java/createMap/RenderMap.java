package createMap;

import animals.Entity;
import interf.EntityImage;

public class RenderMap {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_SPRITE = "\033[38;5;94m";//
    public static final String SQUARE_UNICODE = "🏿";

    public void renderMap(SimulationMap map) {
        for (int colum = 0; colum < map.getTotalRows(); colum++) {
            StringBuilder line = new StringBuilder();
            for (int rows = 0; rows < map.getTotalColumns(); rows++) {
                Coordinates coordinates = new Coordinates(colum, rows);
                if (map.isSquareEmpty(coordinates)) {
                    line.append(getSpriteForEmptySquare(coordinates));

                } else {
                    line.append(getEntitySprite(map.getObject(coordinates)));
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
