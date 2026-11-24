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

  @US-01
  Scenario: AC US-01.2 Five customers register
    When the following customers register:
      | Name         | Email                    |
      | Anna Berger  | anna.berger@example.com  |
      | Ben Huber    | ben.huber@example.com    |
      | Clara Novak  | clara.novak@example.com  |
      | David Gruber | david.gruber@example.com |
      | Elif Yilmaz  | elif.yilmaz@example.com  |
    Then the number of customers is 5
    And reading the customer list shows the following output:
      """
      Customers:
        C01: Anna Berger (anna.berger@example.com)
        C02: Ben Huber (ben.huber@example.com)
        C03: Clara Novak (clara.novak@example.com)
        C04: David Gruber (david.gruber@example.com)
        C05: Elif Yilmaz (elif.yilmaz@example.com)
      """


  @US-02
  Scenario: AC US-02.1 New customer account starts with balance 0.00 and empty history
    When the customer registers with name "Ben Huber" and email "ben.huber@example.com"
    Then the balance of customer "C01" is 0.00
    And the transaction history of customer "C01" is empty