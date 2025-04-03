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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinates other = (Coordinates) obj;
        return mapWidth.equals(other.mapWidth) && mapHeight.equals(other.mapHeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapWidth, mapHeight);
    }
}
