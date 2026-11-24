package org.example;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {

    // list of all registered customers
    private List<Customer> customers = new ArrayList<>();

    // number used for the next customer ID
    private int nextNumber = 1;

    public Customer registerCustomer(String name, String email) {
        // US-02: an email can only be registered once
        for (Customer existing : customers) {
            if (existing.getEmail().equals(email)) {
                throw new IllegalArgumentException("Email already registered");
            }
        }

        // creates IDs like C01, C02, ..., C10
        String id = String.format("C%02d", nextNumber);
        nextNumber++;

        Customer customer = new Customer(id, name, email);
        customers.add(customer);
        return customer;
    }

    public Customer findCustomer(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null; // no customer with this ID
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        String result = "Customers:";
        for (Customer customer : customers) {
            result = result + "\n  " + customer.toString();
        }
        return result;
    }
}