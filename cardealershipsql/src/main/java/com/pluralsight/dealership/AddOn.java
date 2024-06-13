package com.pluralsight.dealership;

public class AddOn {
    // Create variables, as private.
    private String name;
    private double price;

    // Create the constructor.
    public AddOn(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Create the getters.
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Create toString override.
    @Override
    public String toString() {
        return "AddOn{name='" + name + "', price=" + price + "}";
    }
}