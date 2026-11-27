Feature: E6 Manage Locations
  As an Owner
  I want to view, create, edit and remove Locations
  So that I can manage all sites of the EV Charging Network

  Background:
    Given a new location manager

  @US-29
  Scenario: AC US-29.1 Owner creates a single location
    When the owner creates a location with ID "L01", name "Vienna Central" and address "Stephansplatz 1"
    Then the number of locations is 1
    And the location with ID "L01" has the name "Vienna Central" and the address "Stephansplatz 1"

  @US-29
  Scenario: AC US-29.2 Owner creates ten locations
    When the owner creates the following locations:
      | ID  | Name           | Address              |
      | L01 | Vienna Central | Stephansplatz 1      |
      | L02 | Donaustadt     | Wagramer Str. 5      |
      | L03 | Leopoldstadt   | Praterstern 2        |
      | L04 | Landstrasse    | Landstrasser Haupt 8 |
      | L05 | Mariahilf      | Mariahilfer Str. 50  |
      | L06 | Neubau         | Neubaugasse 12       |
      | L07 | Favoriten      | Reumannplatz 4       |
      | L08 | Simmering      | Simmeringer Haupt 20 |
      | L09 | Meidling       | Meidlinger Haupt 15  |
      | L10 | Floridsdorf    | Am Spitz 3           |
    Then the number of locations is 10
    And reading the location list shows the following output:
      """
      Locations:
        L01: Vienna Central (Stephansplatz 1)
        L02: Donaustadt (Wagramer Str. 5)
        L03: Leopoldstadt (Praterstern 2)
        L04: Landstrasse (Landstrasser Haupt 8)
        L05: Mariahilf (Mariahilfer Str. 50)
        L06: Neubau (Neubaugasse 12)
        L07: Favoriten (Reumannplatz 4)
        L08: Simmering (Simmeringer Haupt 20)
        L09: Meidling (Meidlinger Haupt 15)
        L10: Floridsdorf (Am Spitz 3)
      """

  @US-29
  Scenario: AC US-29.3 Creating a location with an existing ID is rejected
    Given the owner creates a location with ID "L01", name "Vienna Central" and address "Stephansplatz 1"
    When the owner creates a location with ID "L01", name "Duplicate Site" and address "Ringstrasse 1"
    Then the location action is rejected with the message "Location ID already exists"
    And the number of locations is 1

  @US-28
  Scenario: AC US-28.1 Owner views the list of all locations
    Given the owner creates the following locations:
      | ID  | Name           | Address         |
      | L01 | Vienna Central | Stephansplatz 1 |
      | L02 | Donaustadt     | Wagramer Str. 5 |
      | L03 | Leopoldstadt   | Praterstern 2   |
    Then reading the location list shows the following output:
      """
      Locations:
        L01: Vienna Central (Stephansplatz 1)
        L02: Donaustadt (Wagramer Str. 5)
        L03: Leopoldstadt (Praterstern 2)
      """

  @US-28
  Scenario: AC US-28.2 Owner finds a specific location by ID
    Given the owner creates the following locations:
      | ID  | Name           | Address         |
      | L01 | Vienna Central | Stephansplatz 1 |
      | L02 | Donaustadt     | Wagramer Str. 5 |
    When the owner searches for the location with ID "L02"
    Then the found location has the name "Donaustadt" and the address "Wagramer Str. 5"

  @US-44
  Scenario: AC US-44.1 Owner edits name and address of an existing location
    Given the owner creates a location with ID "L01", name "Vienna Central" and address "Stephansplatz 1"
    When the owner updates location "L01" to name "Vienna Hub" and address "Kaerntner Str. 10"
    Then the location with ID "L01" has the name "Vienna Hub" and the address "Kaerntner Str. 10"

  @US-44
  Scenario: AC US-44.2 Editing one location leaves other locations unchanged
    Given the owner creates the following locations:
      | ID  | Name           | Address         |
      | L01 | Vienna Central | Stephansplatz 1 |
      | L02 | Donaustadt     | Wagramer Str. 5 |
    When the owner updates location "L01" to name "Vienna Hub" and address "Kaerntner Str. 10"
    Then the location with ID "L02" has the name "Donaustadt" and the address "Wagramer Str. 5"
    And the number of locations is 2

  @US-45
  Scenario: AC US-45.1 Owner removes an existing location
    Given the owner creates the following locations:
      | ID  | Name           | Address         |
      | L01 | Vienna Central | Stephansplatz 1 |
      | L02 | Donaustadt     | Wagramer Str. 5 |
    When the owner removes the location with ID "L01"
    Then the number of locations is 1
    And no location with ID "L01" exists

  @US-45
  Scenario: AC US-45.2 Removing a non-existent location is rejected
    Given the owner creates a location with ID "L01", name "Vienna Central" and address "Stephansplatz 1"
    When the owner removes the location with ID "L99"
    Then the location action is rejected with the message "Location not found"
    And the number of locations is 1