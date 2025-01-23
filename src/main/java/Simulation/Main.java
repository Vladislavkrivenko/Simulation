package Simulation;

import animals.Creature;
import animals.Herbivore;
import createMap.Map;
import createMap.RenderMap;

/**
 * Hello world!
 *
 */
public class Main {
	
	public static void main(String[] args) {
		Map map = new Map(); 
		map.setStartAnimalPosition();
		RenderMap render = new RenderMap();
		try {
			render.renderMap(map);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
	int x = 123;	

	}
}
