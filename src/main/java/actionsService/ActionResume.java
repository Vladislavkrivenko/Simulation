package actionsService;

import gameService.SimulationController;

public class ActionResume implements Action {
    private final SimulationController simulationController;

    public ActionResume(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    @Override
    public void execute() {
        simulationController.resume();
    }
}
