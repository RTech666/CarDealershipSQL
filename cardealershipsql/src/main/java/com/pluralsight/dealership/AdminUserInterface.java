package com.pluralsight.dealership;
import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {
    // Create the variables, as private.
    private VehicleDataManager vehicleDataManager = new VehicleDataManager();
    private ContractDataManager contractDataManager = new ContractDataManager();
    private Dealership dealership;

    // Create the constructor.
    public AdminUserInterface(Dealership dealership) {
        this.dealership = dealership;
    }

    public void displayAdminMenu() {
        // Initalize the scanner.
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Print admin menu.
            System.out.println("Admin Menu:");
            System.out.println("1. View all vehicles");
            System.out.println("2. Add a vehicle");
            System.out.println("3. Remove a vehicle");
            System.out.println("4. View all contracts");
            System.out.println("5. View last 10 contracts");
            System.out.println("0. Exit");

            // Ask the user for their choice.
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Read the user input and execute the appropriate method.
            switch (choice) {
                case 1:
                    viewAllVehicles();
                    break;
                case 2:
                    addVehicle(scanner);
                    break;
                case 3:
                    removeVehicle(scanner);
                    break;
                case 4:
                    viewAllContracts();
                    break;
                case 5:
                    viewLastTenContracts();
                    break;
                case 0:
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Create viewAllVehicles method.
    private void viewAllVehicles() {
        List<Vehicle> vehicles = vehicleDataManager.getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Create addVehicle method.
    private void addVehicle(Scanner scanner) {
        // Ask the user for the VIN.
        System.out.print("Enter VIN: ");
        String vin = scanner.nextLine();
        scanner.nextLine(); 

        // Ask the user for the year.
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        // Ask the user for the make.
        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        // Ask the user for the model.
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        // Ask the user for the vehicle type.
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        // Ask user for the color.
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        // Ask user for the odometer.
        System.out.print("Enter odometer: ");
        int odometer = scanner.nextInt();
        scanner.nextLine();

        // Ask user for the price.
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        // Add vehicle to the database.
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        vehicleDataManager.addVehicle(vehicle);
        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    // Create removeVehicle method.
    private void removeVehicle(Scanner scanner) {
        // Ask user for the VIN.
        System.out.print("Enter VIN of the vehicle to remove: ");
        String vin = scanner.nextLine();
        scanner.nextLine();

        // Remove the vehicle if a mmatching VIn was found in the database.
        Vehicle vehicle = vehicleDataManager.getVehicleByVIN(vin);
        if (vehicle != null) {
            dealership.removeVehicle(vehicle);
            vehicleDataManager.deleteVehicle(vin);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    // Create viewAllContracts method.
    private void viewAllContracts() {
        List<Contract> contracts = contractDataManager.getAllContracts();
        for (Contract contract : contracts) {
            System.out.println(contract);
        }
    }

    // Create viewLastTenContracts method.
    private void viewLastTenContracts() {
        List<Contract> contracts = contractDataManager.getLastTenContracts();
        for (Contract contract : contracts) {
            System.out.println(contract);
        }
    }
}