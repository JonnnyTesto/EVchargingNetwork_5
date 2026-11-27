package org.example;

public class Location {

    private String id;
    private String name;
    private String address;

    public Location(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    // US-44: edit the name and address of a location
    public void updateDetails(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return id + ": " + name + " (" + address + ")";
    }
}