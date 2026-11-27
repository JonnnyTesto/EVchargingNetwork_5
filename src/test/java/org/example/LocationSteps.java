package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationSteps {

    private LocationManager locationManager;
    private Location foundLocation;
    private String errorMessage;

    @Given("a new location manager")
    public void aNewLocationManager() {
        locationManager = new LocationManager();
        foundLocation = null;
        errorMessage = null;
    }

    @When("the owner creates a location with ID {string}, name {string} and address {string}")
    public void theOwnerCreatesALocation(String id, String name, String address) {
        try {
            locationManager.addLocation(new Location(id, name, address));
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the owner creates the following locations:")
    public void theOwnerCreatesTheFollowingLocations(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps()) {
            locationManager.addLocation(
                    new Location(row.get("ID"), row.get("Name"), row.get("Address"))
            );
        }
    }

    @When("the owner searches for the location with ID {string}")
    public void theOwnerSearchesForTheLocationWithId(String id) {
        foundLocation = locationManager.findLocation(id);
    }

    @When("the owner updates location {string} to name {string} and address {string}")
    public void theOwnerUpdatesLocation(String id, String newName, String newAddress) {
        Location location = locationManager.findLocation(id);
        assertThat(location).isNotNull();
        location.updateDetails(newName, newAddress);
    }

    @When("the owner removes the location with ID {string}")
    public void theOwnerRemovesTheLocationWithId(String id) {
        try {
            locationManager.removeLocation(id);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the number of locations is {int}")
    public void theNumberOfLocationsIs(int expectedNumber) {
        assertThat(locationManager.getAllLocations().size()).isEqualTo(expectedNumber);
    }

    @Then("the location with ID {string} has the name {string} and the address {string}")
    public void theLocationWithIdHasNameAndAddress(String id, String expectedName, String expectedAddress) {
        Location location = locationManager.findLocation(id);
        assertThat(location).isNotNull();
        assertThat(location.getName()).isEqualTo(expectedName);
        assertThat(location.getAddress()).isEqualTo(expectedAddress);
    }

    @Then("reading the location list shows the following output:")
    public void readingTheLocationListShowsTheFollowingOutput(String expectedOutput) {
        assertThat(locationManager.toString()).isEqualToNormalizingNewlines(expectedOutput);
    }

    @Then("the location action is rejected with the message {string}")
    public void theLocationActionIsRejectedWithTheMessage(String expectedMessage) {
        assertThat(errorMessage).isEqualTo(expectedMessage);
    }

    @Then("the found location has the name {string} and the address {string}")
    public void theFoundLocationHasNameAndAddress(String expectedName, String expectedAddress) {
        assertThat(foundLocation).isNotNull();
        assertThat(foundLocation.getName()).isEqualTo(expectedName);
        assertThat(foundLocation.getAddress()).isEqualTo(expectedAddress);
    }

    @Then("no location with ID {string} exists")
    public void noLocationWithIdExists(String id) {
        assertThat(locationManager.findLocation(id)).isNull();
    }
}