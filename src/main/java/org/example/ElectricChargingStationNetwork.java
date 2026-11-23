package org.example;

public class ElectricChargingStationNetwork {

    public static void main(String[] args) {
        System.out.println("Welcome to the EV Charging Network");

        showCustomers();
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
    }
}