package coordinatesService;

public class CoordinateUtils {

    public boolean getAdjacent(Coordinates a, Coordinates b) {
        return isAdjacent(a, b);
    }

    private boolean isAdjacent(Coordinates a, Coordinates b) {
        int dx = Math.abs(a.getMapWidth() - b.getMapWidth());
        int dy = Math.abs(a.getMapHeight() - b.getMapHeight());
        return dx + dy == 1;
    }
}
