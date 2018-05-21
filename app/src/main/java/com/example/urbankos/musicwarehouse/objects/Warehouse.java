package com.example.urbankos.musicwarehouse.objects;

public class Warehouse {
    private int id;
    private String name;
    private String address;
    private String town;
    private String country;
    private int capacity;

    public Warehouse(){}

    public Warehouse(int id, String name, String address, String town, String country, int capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.town = town;
        this.country = country;
        this.capacity = capacity;
    }

    public Warehouse(String name, String address, String town, String country, int capacity) {
        this.name = name;
        this.address = address;
        this.town = town;
        this.country = country;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
