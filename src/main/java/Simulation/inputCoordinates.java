package Simulation;

import java.util.Scanner;

import createMap.Coordinates;

public class inputCoordinates {
	private static final String START_GAME = "S";
	private static final String PAUSE_GAME = "P";
	private static final String FINISH_GAME = "F";

	private static final Scanner scanner = new Scanner(System.in);

	public static Coordinates input() {
		while (true) {
			System.out.println("Enter S for Start or P for Pause or E for finish the gamee");
			String line = scanner.nextLine();
			if (line.length() > 1) {
				System.out.println("Invalid format");
				continue;
			}
			if(line.equalsIgnoreCase("s")) {
				System.out.println("Start game");
			}
			if(line.equalsIgnoreCase("p")) {
				System.out.println("Game in pause");
			}
			if(line.equalsIgnoreCase("e")) {
				System.out.println("Finish game");
			}
			char txt = line.charAt(0);
			if (!Character.isLetter(txt)) {
				System.out.println("Invalid format");
				continue;
			}
			return new Coordinates(txt, txt);

		}

	}

	public static void main(String[] args) {
		Coordinates coordinates = input();
		System.out.println("coord " + coordinates);
	}
}
