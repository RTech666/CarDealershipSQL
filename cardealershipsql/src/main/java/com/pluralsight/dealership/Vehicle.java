package com.pluralsight.dealership;

class Vehicle {
    // Create the variables, as private.
    private String vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    // Create the constructor.
    public Vehicle(String vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    // Create the getters.
    public String getVin() {
        return vin;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    // Create the toString override.
    @Override
    public String toString() {
        return "VIN: " + vin + ", Year: " + year + ", Make: " + make + ", Model: " + model + ", Type: " + vehicleType + ", Color: " + color + ", Odometer: " + odometer + ", Price: $" + price;
    }
}