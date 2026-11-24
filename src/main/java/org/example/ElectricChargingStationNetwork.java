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
}