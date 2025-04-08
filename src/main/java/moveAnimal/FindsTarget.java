package moveAnimal;

import animals.Creature;
import animals.Entity;
import mapManager.Coordinates;
import mapManager.EntityManager;
import mapManager.GridManager;

import java.util.List;

public class FindsTarget {
    private EntityManager entityManager;
    private GridManager gridManager;
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

    public void configureDependencies(EntityManager em, GridNavigator nav) {
        this.entityManager = em;
        this.algorithm.setEntityManager(em);
        this.algorithm.setGridNavigator(nav);
    }

    public List<Coordinates> getTargetForFood(GridManager gridManager, Coordinates coordinates, int maxDistance) {//+
        return algorithm.getBfs(gridManager, coordinates, maxDistance);
    }

}

