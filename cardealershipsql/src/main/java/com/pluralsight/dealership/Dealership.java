package com.pluralsight.dealership;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    // Create the variables, as private.
    private int dealershipID;
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    // Initalize data manager.
    private VehicleDataManager vehicleDataManager = new VehicleDataManager();

    // Create the constructor.
    public Dealership(int dealershipID, String name, String address, String phone) {
        this.dealershipID = dealershipID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Create the getters and setters.
    public int getdealershipID() {
        return dealershipID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Vehicle> getInventory() {
        return inventory;
    }

    public List<Vehicle> getVehiclesByPriceFromDatabase(double min, double max) {
        return vehicleDataManager.getVehiclesByPriceRange(min, max);
    }

    public List<Vehicle> getVehiclesByMakeModelFromDatabase(String make, String model) {
        return vehicleDataManager.getVehiclesByMakeModel(make, model);
    }

    public List<Vehicle> getVehiclesByYearRangeFromDatabase(int minYear, int maxYear) {
        return vehicleDataManager.getVehiclesByYearRange(minYear, maxYear);
    }

    public List<Vehicle> getVehiclesByColorFromDatabase(String color) {
        return vehicleDataManager.getVehiclesByColor(color);
    }

    public List<Vehicle> getVehiclesByMileageRangeFromDatabase(int minMileage, int maxMileage) {
        return vehicleDataManager.getVehiclesByMileageRange(minMileage, maxMileage);
    }

    public List<Vehicle> getVehiclesByTypeFromDatabase(String vehicleType) {
        return vehicleDataManager.getVehiclesByType(vehicleType);
    }

    public Vehicle getVehicleByVINFromDatabase(String vin) {
        return vehicleDataManager.getVehicleByVIN(vin);
    }

    public void setdealershipID(int dealershipID) {
        this.dealershipID = dealershipID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setInventory(List<Vehicle> inventory) {
        this.inventory = inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
        vehicleDataManager.addVehicle(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
        vehicleDataManager.deleteVehicle(vehicle.getVin());
    }
}