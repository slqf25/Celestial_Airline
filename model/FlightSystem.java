package model;

import display.StringConstant;
import java.util.Scanner;

public class FlightSystem 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        FlightNetwork network = new FlightNetwork();

        while (true) 
        {
            System.out.println(StringConstant.MAIN_MENU);
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) 
            {
                case "1":
                    handleAirportMenu(scanner, network);
                    break;
                case "2":
                    handleFlightMenu(scanner, network);
                    break;
                case "3":
                    handleNetworkMenu(scanner, network);
                    break;
                case "0":
                    System.out.println(StringConstant.EXIT_BANNER);
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
            }

            System.out.println("\nPress Enter to return to menu...");
            scanner.nextLine();
        }
    }

    private static void handleAirportMenu(Scanner scanner, FlightNetwork network) 
    {
        while (true) 
        {
            System.out.println(StringConstant.AIRPORT_MENU);
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) 
            {
                case "1":
                    System.out.print("Enter new airport code: ");
                    String code = scanner.nextLine().toUpperCase();
                    network.addAirport(code);
                    break;
                case "2":
                    System.out.print("Enter airport code to remove: ");
                    String remove = scanner.nextLine().toUpperCase();
                    network.removeAirport(remove);
                    break;
                case "3":
                    System.out.print("Enter airport code to search: ");
                    String search = scanner.nextLine().toUpperCase();
                    network.searchAirport(search);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void handleFlightMenu(Scanner scanner, FlightNetwork network) 
    {
        while (true) 
        {
            System.out.println(StringConstant.FLIGHT_MENU);
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) 
            {
                case "1":
                    System.out.print("Enter source airport: ");
                    String from = scanner.nextLine().toUpperCase();
                    System.out.print("Enter destination airport: ");
                    String to = scanner.nextLine().toUpperCase();
                    network.addFlight(from, to);
                    break;
                case "2":
                    System.out.print("Enter source airport: ");
                    String rFrom = scanner.nextLine().toUpperCase();
                    System.out.print("Enter destination airport: ");
                    String rTo = scanner.nextLine().toUpperCase();
                    network.removeFlight(rFrom, rTo);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void handleNetworkMenu(Scanner scanner, FlightNetwork network) 
    {
        while (true) 
        {
            System.out.println(StringConstant.NETWORK_MENU);
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) 
            {
                case "1":
                    network.displayNetworkAsLineGraph();
                    break;
                case "2":
                    System.out.print("Enter starting airport: ");
                    String start = scanner.nextLine().toUpperCase();
                    network.bfs(start);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
