package coordinatesService;

public class CoordinateUtils {

    private boolean getAdjacent(Coordinates x, Coordinates y) {
        return isAdjacent(x, y);
    }

    public boolean isAdjacent(Coordinates a, Coordinates b) {
        int dx = Math.abs(a.getMapWidth() - b.getMapWidth());
        int dy = Math.abs(a.getMapHeight() - b.getMapHeight());
        return dx + dy == 1;
    }
}
