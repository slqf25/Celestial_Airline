package controller;

import model.FlightNetwork;

public class FlightController {
    private FlightNetwork network;

    public FlightController() {
        this.network = new FlightNetwork();  // Automatically loads data
    }

    // Airport Operations
    public void addAirport(String code) {
        network.addAirport(code);
    }

    public void removeAirport(String code) {
        network.removeAirport(code);
    }

    public void searchAirport(String code) {
        network.searchAirport(code);
    }

    // Flight Route Operations
    public void addFlight(String from, String to) {
        network.addFlight(from, to);
    }

    public void removeFlight(String from, String to) {
        network.removeFlight(from, to);
    }

    // Display
    public void displayNetwork() {
        network.displayNetwork();
    }

    public void displayBoxGraph() {
        network.displayNetworkAsLineGraph();
    }

    // Traversal
    public void bfsTraversal(String startAirport) {
        network.bfs(startAirport);
    }

    // File Handling
    public void saveAll() {
        network.saveData();
    }

    public void loadAll() {
        network.loadData();
    }
}  
