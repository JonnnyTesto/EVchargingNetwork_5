package org.example;

import java.util.ArrayList;
import java.util.List;

public class LocationManager {

    // list of all locations in the EV Charging Network
    private List<Location> locations = new ArrayList<>();

    // US-29: add a new location (each location ID can only exist once)
    public void addLocation(Location location) {
        if (findLocation(location.getId()) != null) {
            throw new IllegalArgumentException("Location ID already exists");
        }
        locations.add(location);
    }

    // US-28: find a location by its ID
    public Location findLocation(String id) {
        for (Location location : locations) {
            if (location.getId().equals(id)) {
                return location;
            }
        }
        return null; // no location with this ID
    }

    // US-45: remove an existing location by its ID
    public void removeLocation(String id) {
        Location existing = findLocation(id);
        if (existing == null) {
            throw new IllegalArgumentException("Location not found");
        }
        locations.remove(existing);
    }

    public List<Location> getAllLocations() {
        return locations;
    }

    @Override
    public String toString() {
        String result = "Locations:";
        for (Location location : locations) {
            result = result + "\n  " + location.toString();
        }
        return result;
    }
}