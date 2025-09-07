package model;

import controller.FlightController;
import display.StringConstant;

import java.util.Scanner;

public class FlightSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightController controller = new FlightController();

        while (true) {
            System.out.println(StringConstant.MAIN_MENU);
            System.out.print("Enter your option: ");
            String mainChoice = scanner.nextLine();

            switch (mainChoice) {
                case "1":
                    // Airport Management Menu
                    while (true) {
                        System.out.println(StringConstant.AIRPORT_MENU);
                        System.out.print("Enter your option: ");
                        String airportChoice = scanner.nextLine();

                        switch (airportChoice) {
                            case "1":
                                System.out.print("Enter new airport code: ");
                                controller.addAirport(scanner.nextLine().toUpperCase());
                                break;
                            case "2":
                                System.out.print("Enter airport code to remove: ");
                                controller.removeAirport(scanner.nextLine().toUpperCase());
                                break;
                            case "3":
                                System.out.print("Enter airport code to search: ");
                                controller.searchAirport(scanner.nextLine().toUpperCase());
                                break;
                            case "0":
                                break;
                            default:
                                System.out.println("Invalid input. Try again.");
                        }

                        if (airportChoice.equals("0")) break;
                        System.out.println("\nPress Enter to return...");
                        scanner.nextLine();
                    }
                    break;

                case "2":
                    // Flight Route Management Menu
                    while (true) {
                        System.out.println(StringConstant.FLIGHT_MENU);
                        System.out.print("Enter your option: ");
                        String flightChoice = scanner.nextLine();

                        switch (flightChoice) {
                            case "1":
                                System.out.print("Enter source airport: ");
                                String from = scanner.nextLine().toUpperCase();
                                System.out.print("Enter destination airport: ");
                                String to = scanner.nextLine().toUpperCase();
                                controller.addFlight(from, to);
                                break;
                            case "2":
                                System.out.print("Enter source airport: ");
                                String f1 = scanner.nextLine().toUpperCase();
                                System.out.print("Enter destination airport: ");
                                String f2 = scanner.nextLine().toUpperCase();
                                controller.removeFlight(f1, f2);
                                break;
                            case "0":
                                break;
                            default:
                                System.out.println("Invalid input. Try again.");
                        }

                        if (flightChoice.equals("0")) break;
                        System.out.println("\nPress Enter to return...");
                        scanner.nextLine();
                    }
                    break;

                case "3":
                    // Network View & Traversal Menu
                    while (true) {
                        System.out.println(StringConstant.NETWORK_MENU);
                        System.out.print("Enter your option: ");
                        String networkChoice = scanner.nextLine();

                        switch (networkChoice) {
                            case "1":
                                controller.displayNetwork();
                                break;
                            case "2":
                                System.out.print("Enter starting airport code for BFS: ");
                                controller.bfsTraversal(scanner.nextLine().toUpperCase());
                                break;
                            case "0":
                                break;
                            default:
                                System.out.println("Invalid input. Try again.");
                        }

                        if (networkChoice.equals("0")) break;
                        System.out.println("\nPress Enter to return...");
                        scanner.nextLine();
                    }
                    break;

                case "0":
                    System.out.println(StringConstant.EXIT_BANNER);
                    return;

                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
