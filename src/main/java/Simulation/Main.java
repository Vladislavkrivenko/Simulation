package Simulation;

import animals.Creature;
import animals.Herbivore;
import createMap.GameMap;
import createMap.RenderMap;

/**
 * Hello world!
 *
 */
public class Main {

	public static void main(String[] args) {
		GameMap game = new GameMap(15, 50);
		game.IFillTheMapWithObjects();

		RenderMap render = new RenderMap();
		render.renderMap(game.getSimulationMap());
	}
}
