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

    @Given("a new customer register")
    public void a_new_customer_register() {
        customerManager = new CustomerManager();
    }

    @When("the customer registers with name {string} and email {string}")
    public void the_customer_registers_with_name_and_email(String name, String email) {
        customerManager.registerCustomer(name, email);
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
}