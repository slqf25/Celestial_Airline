import display.FlightSystemUtils;
import display.StringConstant;
import java.util.Scanner;

public class FlightSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightNetwork network = new FlightNetwork();

        while (true) {
            System.out.println(StringConstant.MAIN_MENU);
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter new airport code (e.g., KUL): ");
                    String code = scanner.nextLine().toUpperCase();
                    network.addAirport(code);
                    FlightSystemUtils.printBoxed("Airport " + code + " added successfully!");
                    break;

                case "2":
                    System.out.print("Enter source airport code: ");
                    String from = scanner.nextLine().toUpperCase();
                    System.out.print("Enter destination airport code: ");
                    String to = scanner.nextLine().toUpperCase();
                    network.addFlight(from, to);
                    FlightSystemUtils.printBoxed("Route from " + from + " to " + to + " added.");
                    break;

                case "3":
                    network.displayNetworkAsLineGraph();
                    break;

                case "4":
                    System.out.println(StringConstant.TRAVERSE_MENU);
                    System.out.print("Enter source airport: ");
                    String start = scanner.nextLine().toUpperCase();
                    network.bfs(start);
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
}
