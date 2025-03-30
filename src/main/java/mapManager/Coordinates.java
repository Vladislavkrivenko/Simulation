package mapManager;

import java.util.Objects;

public class Coordinates {
    private final Integer mapWidth;
    private final Integer mapHeight;

    public Integer getMapWidth() {
        return mapWidth;
    }


    public Integer getMapHeight() {
        return mapHeight;
    }

    public Coordinates(int mapWidth, int mapHeight) {
        super();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public boolean possibleMovementsOfTheAnimal(int mapWidth, int mapHeight) {

        int w = getMapWidth();
        int h = getMapHeight();
        if ((w < 0) || (w >= mapWidth))
            return false;
        if ((h < 0) || (h >= mapHeight))
            return false;

        return true;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinates other = (Coordinates) obj;
        return mapWidth == other.mapWidth && mapHeight == other.mapHeight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapWidth, mapHeight);
    }
}
