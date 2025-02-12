package createMap;

import animals.Entity;

public class RenderMap {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_SPRITE = "\033[38;5;94m";//
    //public static final String ANSI_SQUARE_BACKGROUND = "\033[48;5;187m";

    public static final String ANSI_COLOR_FOR_RABBIT = "\u001B[97m";
    public static final String ANSI_COLOR_FOR_WOLF = "\u001B[90m";
    public static final String ANSI_COLOR_FOR_ROCK = "\u001B[30m";
    public static final String ANSI_COLOR_FOR_GRASS = "\u001B[32m";
    public static final String ANSI_COLOR_FOR_TREE = "\u001B[32m";

    public static final String SQUARE_UNICODE = "🏿";
    public static final String GRASS_UNICODE = "🍀";
    public static final String ROCK_UNICODE = "🗻";
    public static final String TREE_UNICODE = "🌳";
    public static final String RABBIT_UNICODE = "🐰";
    public static final String WOLF_UNICODE = "🐺";

    public void renderMap(SimulationMap map) {
        for (int vert = 0; vert < map.getTotalRows(); vert++) {
            String line = "";
            for (int hor = 0; hor < map.getTotalColumns(); hor++) {
                Coordinates coordinates = new Coordinates(hor, vert);
                if (map.isSquareEmpty(coordinates)) {
                    line += getSpriteForEmptySquare(coordinates);

                } else {
                    line += getEntitySprite(map.getObject(coordinates));
                }

            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    private String getUnicodeForObjects(Entity entity) {
        return selectUnicodeSpriteForObject(entity, GRASS_UNICODE, RABBIT_UNICODE, WOLF_UNICODE, ROCK_UNICODE,
                TREE_UNICODE);
    }

    private String getColorsForObjectsUnicode(Entity entity) {
        return selectUnicodeSpriteForObject(entity, ANSI_COLOR_FOR_GRASS, ANSI_COLOR_FOR_RABBIT, ANSI_COLOR_FOR_WOLF,
                ANSI_COLOR_FOR_ROCK, ANSI_COLOR_FOR_TREE);
    }

    private String selectUnicodeSpriteForObject(Entity entity, String grass, String herbivore,
                                                String predator, String rock, String tree) {
        switch (entity.getClass().getSimpleName()) {
            case "Grass":
                return grass;
            case "Herbivore":
                return herbivore;
            case "Predator":
                return predator;
            case "Rock":
                return rock;
            case "Tree":
                return tree;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + entity.getClass().getSimpleName());
        }
    }

    private String getSpriteForEmptySquare(Coordinates coordinates) {
        return colorizeSprite(EMPTY_SPRITE + SQUARE_UNICODE);
    }

    private String getEntitySprite(Entity entity) {
        String objectUnicode = getUnicodeForObjects(entity);
        String ansiColors = getColorsForObjectsUnicode(entity);
        return colorizeSprite(ansiColors + objectUnicode);
    }

    private String colorizeSprite(String sprite) {
        return sprite + ANSI_RESET;//ANSI_SQUARE_BACKGROUND +
    }

}
