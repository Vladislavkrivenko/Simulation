package actionsService;

import gameService.SimulationController;

public class ActionOneIteration implements Action {
    private final SimulationController simulationController;

    public ActionOneIteration(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    @Override
    public void execute() {
        simulationController.oneIteration();
    }
}
