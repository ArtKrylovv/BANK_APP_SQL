package com.solvd.bankapp.model;

public class Branch {
    private int id;
    private String name;
    private int addressesId;

    public Branch(String name, int addressesId) {
        this.name = name;
        this.addressesId = addressesId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAddressesId() {
        return addressesId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddressesId(int addressesId) {
        this.addressesId = addressesId;
    }


}
