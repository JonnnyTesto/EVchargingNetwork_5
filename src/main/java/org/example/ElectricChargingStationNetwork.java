package org.example;

public class ElectricChargingStationNetwork {

    public static void main(String[] args) {
        System.out.println("Welcome to the EV Charging Network");

        showCustomers();
        System.out.println();
        showLocations();
    }

    // ---------------- Customers (E1) ----------------
    private static void showCustomers() {
        CustomerManager customerManager = new CustomerManager();

        // US-01: register five customers
        customerManager.registerCustomer("Anna Berger", "anna.berger@example.com");
        customerManager.registerCustomer("Ben Huber", "ben.huber@example.com");
        customerManager.registerCustomer("Clara Novak", "clara.novak@example.com");
        customerManager.registerCustomer("David Gruber", "david.gruber@example.com");
        customerManager.registerCustomer("Elif Yilmaz", "elif.yilmaz@example.com");

        // print all customers (uses toString() of CustomerManager)
        System.out.println(customerManager);
        System.out.println("Number of customers: " + customerManager.getAllCustomers().size());

        // US-02: a new account starts with balance 0.00 and an empty history
        Customer anna = customerManager.findCustomer("C01");
        System.out.println();
        System.out.println("New account of " + anna.getName() + ":");
        System.out.println("  Balance: " + anna.getBalance());
        System.out.println("  Transactions: " + anna.getTransactionHistory().size());

        // US-02: registering with an existing email is rejected
        try {
            customerManager.registerCustomer("Anna B.", "anna.berger@example.com");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration of Anna B. rejected: " + e.getMessage());
        }
        System.out.println("Number of customers: " + customerManager.getAllCustomers().size());

        // US-03: a customer views the balance of their own account
        Customer ben = customerManager.findCustomer("C02");
        System.out.println();
        System.out.println(ben.showBalance());
    }

    // ---------------- Locations (E6) ----------------
    private static void showLocations() {
        LocationManager locationManager = new LocationManager();

        // US-29: create ten locations
        locationManager.addLocation(new Location("L01", "Vienna Central", "Stephansplatz 1"));
        locationManager.addLocation(new Location("L02", "Donaustadt", "Wagramer Str. 5"));
        locationManager.addLocation(new Location("L03", "Leopoldstadt", "Praterstern 2"));
        locationManager.addLocation(new Location("L04", "Landstrasse", "Landstrasser Haupt 8"));
        locationManager.addLocation(new Location("L05", "Mariahilf", "Mariahilfer Str. 50"));
        locationManager.addLocation(new Location("L06", "Neubau", "Neubaugasse 12"));
        locationManager.addLocation(new Location("L07", "Favoriten", "Reumannplatz 4"));
        locationManager.addLocation(new Location("L08", "Simmering", "Simmeringer Haupt 20"));
        locationManager.addLocation(new Location("L09", "Meidling", "Meidlinger Haupt 15"));
        locationManager.addLocation(new Location("L10", "Floridsdorf", "Am Spitz 3"));

        // US-28: print all locations (uses toString() of LocationManager)
        System.out.println(locationManager);
        System.out.println("Number of locations: " + locationManager.getAllLocations().size());

        // US-29: adding a duplicate location ID is rejected
        try {
            locationManager.addLocation(new Location("L01", "Duplicate Site", "Ringstrasse 1"));
        } catch (IllegalArgumentException e) {
            System.out.println("Adding duplicate L01 rejected: " + e.getMessage());
        }

        // US-44: edit location details
        Location viennaCentral = locationManager.findLocation("L01");
        viennaCentral.updateDetails("Vienna Hub", "Kaerntner Str. 10");
        System.out.println();
        System.out.println("Updated location: " + viennaCentral);

        // US-45: remove a location
        locationManager.removeLocation("L10");
        System.out.println("Removed location L10. Number of locations: "
                + locationManager.getAllLocations().size());
    }
}