package Simulation;

import java.util.Scanner;

public class inputCoordinates {
    private static final int START_GAME = 1;
    private static final int STOP_GAME = 2;
    private static final int PAUSE = 3;
    private static final int ITERATE = 4;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        startGameLoop();
    }

    private int getUserChoice() {
        int num;
        while (true) {
            System.out.print("Enter a number (1-START, 2-STOP, 3-PAUSE, 4-ITERATION): ");
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                if (num >= 1 && num <= 4) {
                    return num;
                } else {
                    System.out.println("Incorrect choice! Enter a number from 1 to 4.");
                }
            } else {
                System.out.println("Incorrect choice! Enter a number from 1 to 4.");
                scanner.next();
            }
        }
    }

    private void startGameLoop() {
        try {
            int choice = getUserChoice();
            switch (choice) {
                case START_GAME:
                    System.out.println("The game has begun!");
                    break;
                case STOP_GAME:
                    System.out.println("The game is stopped!");
                    break;
                case PAUSE:
                    System.out.println("Гру призупинено!");
                    break;
                case ITERATE:
                    System.out.println("Iterating...");
                    break;
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Scanner is closed.");
        }


    }
}
