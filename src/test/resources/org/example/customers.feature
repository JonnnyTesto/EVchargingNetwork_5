Feature: E1 Register and Manage Account
  As a Customer
  I want to register and get a Customer Account
  So that I can use the Charging Points of the EV Charging Network and see my Balance

  Background:
    Given a new customer register

  @US-01
  Scenario: AC US-01.1 Customer registers with name and email
    When the customer registers with name "Anna Berger" and email "anna.berger@example.com"
    Then the number of customers is 1
    And the customer with ID "C01" has the name "Anna Berger" and the email "anna.berger@example.com"

