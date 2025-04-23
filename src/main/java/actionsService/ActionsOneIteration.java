package actionsService;

import gameService.SimulationController;

public class ActionsOneIteration implements Action {
    private final SimulationController simulationController;

    public ActionsOneIteration(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    @Override
    public void execute() {
        simulationController.oneIteration();
    }
}
