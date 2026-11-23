package org.example;

public class ElectricChargingStationNetwork {

    public static void main(String[] args) {
        System.out.println("Welcome to the EV Charging Network");

        showCustomers();
    }

    // ---------------- Customers (E1) ----------------
    private static void showCustomers() {
        CustomerManager customerManager = new CustomerManager();

        // US-01: register a customer
        Customer anna = customerManager.registerCustomer(
                "Anna Berger",
                "anna.berger@example.com"
        );

        System.out.println("Registered customer: " +
                anna.getId() + " " +
                anna.getName() + " (" +
                anna.getEmail() + ")");

        System.out.println("Number of customers: " +
                customerManager.getAllCustomers().size());
    }
}