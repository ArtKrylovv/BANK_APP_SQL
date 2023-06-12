package com.solvd.bankapp.bin;


import jakarta.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class State {
    @XmlAttribute
    private int id;
    @XmlElement(name = "abbreviation")
    private String abbreviation;
    @XmlElement(name="name")
    private String name;

    public State(int id, String abbreviation, String name) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public State() {
    }

    public int getId() {
        return id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
