package com.pluralsight.dealership;

public abstract class Contract {
    // Create variables, as private.
    private int ID;
    private String contractDate;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    // Create the constructor.
    public Contract(int ID, String contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.ID = ID;
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Create the getters.
    public int getID() {
        return ID;
    }

    public String getContractDate() {
        return contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public abstract boolean isFinanceOption();
}