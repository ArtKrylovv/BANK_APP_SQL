package com.solvd.bankapp.model;

public class Address {
    private int id;
    private int houseNumber;
    private String streetName;
    private int aptNumber;
    private String city;
    private State state;

    public Address(int id, int houseNumber, String streetName, int aptNumber, String city, State state) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.aptNumber = aptNumber;
        this.city = city;
        this.state = state;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getAptNumber() {
        return aptNumber;
    }

    public String getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setAptNumber(int aptNumber) {
        this.aptNumber = aptNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", houseNumber=" + houseNumber +
                ", streetName='" + streetName + '\'' +
                ", aptNumber=" + aptNumber +
                ", city='" + city + '\'' +
                ", state=" + state +
                '}';
    }
}