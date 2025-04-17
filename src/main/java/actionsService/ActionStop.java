package actionsService;

import gameService.SimulationController;

public class ActionStop implements Action {
    private final SimulationController simulationController;

    public ActionStop(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    @Override
    public void execute() {
        simulationController.stop();
    }
}
