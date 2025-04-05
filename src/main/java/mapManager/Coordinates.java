package mapManager;

import java.util.Objects;

public class Coordinates {
    private final int mapWidth;
    private final int mapHeight;

    public int getMapWidth() {
        return mapWidth;
    }


    public int getMapHeight() {
        return mapHeight;
    }

    public Coordinates(int mapWidth, int mapHeight) {
        super();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates other = (Coordinates) obj;
        return mapWidth == other.mapWidth && mapHeight == other.mapHeight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapWidth, mapHeight);
    }

    @Override
    public String toString() {
        return "(" + mapWidth + ", " + mapHeight + ")";
    }
}
