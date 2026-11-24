package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customer {

    private String id;
    private String name;
    private String email;
    private double balance = 0.00;
    private List<Transaction> transactionHistory = new ArrayList<>();

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    // US-03: text the customer sees, e.g. "Balance of Anna Berger (C01): 0.00 EUR"
    public String showBalance() {
        return "Balance of " + name + " (" + id + "): "
                + String.format(Locale.US, "%.2f", balance) + " EUR";
    }

    @Override
    public String toString() {
        return id + ": " + name + " (" + email + ")";
    }
}