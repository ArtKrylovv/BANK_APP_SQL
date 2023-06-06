package com.solvd.bankapp.model;

public class Branch {
    private int id;
    private String name;
    private Address address;

    public Branch(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Branch(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Branch() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
