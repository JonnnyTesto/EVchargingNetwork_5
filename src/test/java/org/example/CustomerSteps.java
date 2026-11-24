package org.example;

import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerSteps {

    // the customer manager we test in each scenario
    private CustomerManager customerManager;

    // stores the message if a registration is rejected (US-02)
    private String errorMessage;

    @Given("a new customer register")
    public void a_new_customer_register() {
        customerManager = new CustomerManager();
    }

    @When("the customer registers with name {string} and email {string}")
    public void the_customer_registers_with_name_and_email(String name, String email) {
        try {
            customerManager.registerCustomer(name, email);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the number of customers is {int}")
    public void the_number_of_customers_is(int expectedNumber) {
        assertThat(customerManager.getAllCustomers().size()).isEqualTo(expectedNumber);
    }

    @Then("the customer with ID {string} has the name {string} and the email {string}")
    public void the_customer_with_id_has_the_name_and_the_email(String id, String expectedName, String expectedEmail) {
        Customer customer = customerManager.findCustomer(id);
        assertThat(customer.getName()).isEqualTo(expectedName);
        assertThat(customer.getEmail()).isEqualTo(expectedEmail);
    }

    @When("the following customers register:")
    public void the_following_customers_register(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            customerManager.registerCustomer(row.get("Name"), row.get("Email"));
        }
    }

    @Then("reading the customer list shows the following output:")
    public void reading_the_customer_list_shows_the_following_output(String expectedOutput) {
        assertThat(customerManager.toString()).isEqualTo(expectedOutput);
    }

    // US-02: a new account starts with balance 0.00
    @Then("the balance of customer {string} is {double}")
    public void theBalanceOfCustomerIs(String customerId, Double expectedBalance) {
        Customer customer = customerManager.findCustomer(customerId);
        assertThat(customer.getBalance()).isEqualTo(expectedBalance);
    }

    // US-02: a new account has no transactions yet
    @Then("the transaction history of customer {string} is empty")
    public void theTransactionHistoryOfCustomerIsEmpty(String customerId) {
        Customer customer = customerManager.findCustomer(customerId);
        assertThat(customer.getTransactionHistory()).isEmpty();
    }

    // US-02: the customer is informed that the email already exists
    @Then("the registration is rejected with the message {string}")
    public void theRegistrationIsRejectedWithTheMessage(String expectedMessage) {
        assertThat(errorMessage).isEqualTo(expectedMessage);
    }

    // US-02: the duplicate registration was not saved
    @Then("no customer with the name {string} exists")
    public void noCustomerWithTheNameExists(String name) {
        for (Customer customer : customerManager.getAllCustomers()) {
            assertThat(customer.getName()).isNotEqualTo(name);
        }
    }
}