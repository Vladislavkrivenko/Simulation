package movingService;

import entityService.Creature;
import entityService.Entity;
import coordinatesService.Coordinates;
import coordinatesService.MapService;

import java.util.List;

public class FindsTarget {
    private Creature creature;
    protected Class<? extends Entity> victimClass;
    private SearchAlgorithm algorithm;

    public FindsTarget() {
        this.algorithm = new SearchAlgorithm(this);
    }

    public void setVictim(Class<? extends Entity> victim) {
        this.victimClass = victim;
    }

    public Class<? extends Entity> getVictim() {
        return victimClass;
    }

    public void setAlgorithm(SearchAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }

    public List<Coordinates> getTargetForFood(MapService mapService, Coordinates coordinates) {
        return algorithm.getBfs(mapService, coordinates);
    }

}

