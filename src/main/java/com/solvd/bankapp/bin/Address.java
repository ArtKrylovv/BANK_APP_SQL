package com.solvd.bankapp.bin;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlAttribute
    private int id;
    @XmlElement(name="houseNumber")
    private int houseNumber;
    @XmlElement(name="streetName")
    private String streetName;
    @XmlElement(name="aptNumber")
    private int aptNumber;
    @XmlElement(name="city")
    private String city;
    @XmlElement(name="state")
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