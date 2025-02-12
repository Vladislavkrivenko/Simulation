package Simulation;

import createMap.RenderMap;
import createMap.SimulationMap;

public class Game {
	private final SimulationMap mapGame;
	private final RenderMap render = new RenderMap();
	private final inputCoordinates input = new inputCoordinates();

	public Game(SimulationMap mapGame) {
		this.mapGame = mapGame;
	}

	public void gameLoop() {
		boolean isHerbivore = true;
		try {
			while (true) {
				render.renderMap(mapGame);
				input.input();

				isHerbivore = !isHerbivore;
			}

		} catch (Exception ignored) {
			System.out.println("Unkonow what write");
		}

	}
}