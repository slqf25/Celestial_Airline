import display.FlightSystemUtils;
import java.io.*;
import java.util.*;

public class FlightNetwork 
{
    private static final String AIRPORT_FILE = "data/airports.csv";
    private static final String FLIGHT_FILE = "data/flights.csv";

    private Map<String, List<String>> flightMap;

    public FlightNetwork() 
    {
        flightMap = new HashMap<>();
        loadData();
    }

    /**
     * Add a new airport to the flight network.
     * Time Complexity: O(1)
     */
    public void addAirport(String code) 
    {
        if (flightMap.containsKey(code)) 
        {
            FlightSystemUtils.printBoxed("Airport already exists: " + code);
            return;
        }

        if (code.trim().isEmpty()) 
        {
            FlightSystemUtils.printBoxed("Airport code cannot be empty.");
            return;
        }

        flightMap.put(code, new ArrayList<>());
        saveData();
        FlightSystemUtils.printBoxed("Airport " + code + " added successfully.");
    }

    /**
     * Add a bidirectional flight route between two airports.
     * Time Complexity: O(1)
     */
    public void addFlight(String from, String to) 
    {
        if (from.equals(to)) {
            FlightSystemUtils.printBoxed("Cannot create a route to the same airport.");
            return;
        }

        if (!flightMap.containsKey(from) || !flightMap.containsKey(to)) 
        {
            FlightSystemUtils.printBoxed("One or both airports do not exist.");
            return;
        }

        if (flightMap.get(from).contains(to)) 
        {
            FlightSystemUtils.printBoxed("Route already exists between " + from + " and " + to + ".");
            return;
        }

        flightMap.get(from).add(to);
        flightMap.get(to).add(from);
        saveData();
        FlightSystemUtils.printBoxed("Route added between " + from + " and " + to + ".");
    }

    /**
     * Display full network in key-value format.
     * Time Complexity: O(V + E)
     */
    public void displayNetwork() 
    {
        System.out.println("Flight Network:");
        for (String airport : flightMap.keySet()) 
        {
            System.out.println(airport + " → " + flightMap.get(airport));
        }
    }

    /**
     * Perform BFS (Breadth-First Search) traversal from a given airport.
     * Time Complexity: O(V + E)
     */
    public void bfs(String startAirport) 
    {
        if (!flightMap.containsKey(startAirport)) 
        {
            FlightSystemUtils.printBoxed("Airport not found: " + startAirport);
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startAirport);
        queue.add(startAirport);

        System.out.println("\n  BFS Traversal from: " + startAirport);
        while (!queue.isEmpty()) 
        {
            String current = queue.poll();
            System.out.println("  ➤ " + current);

            for (String neighbor : flightMap.get(current)) 
            {
                if (!visited.contains(neighbor)) 
                {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    /**
     * Display the flight graph visually using boxed layout.
     * Time Complexity: O(V + E)
     */
    public void displayNetworkAsLineGraph() 
    {
        System.out.println("\nFlight Network (Box View):\n");

        for (String airport : flightMap.keySet()) 
        {
            List<String> connections = flightMap.get(airport);

            if (connections.isEmpty()) continue;

            // TOP BOX LINE
            System.out.print("┌────────────┐     ");
            for (int i = 0; i < connections.size(); i++) 
            {
                System.out.print("┌────────────┐     ");
            }
            System.out.println();

            // NAME LINE
            System.out.printf("│  %-10s│ ──▶ ", airport);
            for (String dest : connections) {
                System.out.printf("│  %-10s│     ", dest);
            }
            System.out.println();

            // BOTTOM BOX LINE
            System.out.print("└────────────┘     ");
            for (int i = 0; i < connections.size(); i++) 
            {
                System.out.print("└────────────┘     ");
            }
            System.out.println("\n");
        }
    }

    /**
     * Load airport and flight data from CSV files.
     * Time Complexity: O(V + E)
     */
    public void loadData() {
        // Load airports
        try (BufferedReader br = new BufferedReader(new FileReader(AIRPORT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) 
            {
                line = line.trim();
                if (!line.isEmpty() && !flightMap.containsKey(line)) 
                {
                    flightMap.put(line, new ArrayList<>());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading airport file: " + e.getMessage());
        }

        // Load flight connections
        try (BufferedReader br = new BufferedReader(new FileReader(FLIGHT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 2) 
                {
                    String from = parts[0].trim();
                    String to = parts[1].trim();
                    if (!from.equals(to) && flightMap.containsKey(from) && flightMap.containsKey(to) && !flightMap.get(from).contains(to)) 
                    {
                        flightMap.get(from).add(to);
                        flightMap.get(to).add(from);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading flight file: " + e.getMessage());
        }
    }

    /**
     * Save airport and flight data into CSV files.
     * Time Complexity: O(V + E)
     */
    public void saveData() 
    {
        // Save airports (sorted)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AIRPORT_FILE))) 
        {
            List<String> sortedAirports = new ArrayList<>(flightMap.keySet());
            Collections.sort(sortedAirports);
            
            for (String airport : sortedAirports) 
            {
                bw.write(airport);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing airport file: " + e.getMessage());
        }

        // Save flights (avoid duplicate bidirectional routes)
        Set<String> written = new HashSet<>();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FLIGHT_FILE))) 
        {
            for (String from : flightMap.keySet()) 
            {
                for (String to : flightMap.get(from)) 
                {
                    String pair = from.compareTo(to) < 0 ? from + "," + to : to + "," + from;
                    
                    if (!written.contains(pair)) 
                    {
                        written.add(pair);
                        bw.write(from + "," + to);
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing flight file: " + e.getMessage());
        }
    }
}
