package createMap;

import java.util.Objects;

public class Coordinates {
    private final Integer horizontal;
    private final Integer vertical;

    public Integer getHorizontal() {
        return horizontal;
    }

    public Integer getVertical() {
        return vertical;
    }

    public Coordinates(int horizontal, int vertical) {
        super();
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public boolean possibleMovementsOfTheAnimal(int mapWidth, int mapHeight) {

        int h = getHorizontal();
        int v = getVertical();
        if ((h < 0) || (h >= mapWidth))
            return false;
        if ((v < 0) || (v >= mapHeight))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
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
        return horizontal == other.horizontal && vertical == other.vertical;
    }

}
