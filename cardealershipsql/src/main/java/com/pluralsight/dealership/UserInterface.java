package com.pluralsight.dealership;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    // Create variables, as private.
    private VehicleDataManager vehicleDataManager = new VehicleDataManager();
    private ContractDataManager contractDataManager = new ContractDataManager();
    private Dealership dealership;
    private AdminUserInterface adminUI;

    // Create the constructor.
    public UserInterface(Dealership dealership, AdminUserInterface adminUI) {
        this.dealership = dealership;
        this.adminUI = adminUI;
    }

    public void displayMainMenu() {
        // Initalize scanner.
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Print main menu.
            System.out.println("Main Menu:");
            System.out.println("1. View all vehicles");
            System.out.println("2. Search vehicles by make and model");
            System.out.println("3. Search vehicles by price range");
            System.out.println("4. Search vehicles by year range");
            System.out.println("5. Search vehicles by color");
            System.out.println("6. Search vehicles by mileage range");
            System.out.println("7. Search vehicles by type");
            System.out.println("8. Admin menu.");
            System.out.println("0. Exit");

            // Ask user to enter their choice.
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Read the user input and execute the appropriate method.
            switch (choice) {
                case 1:
                    viewAllVehicles();
                    break;
                case 2:
                    searchVehiclesByMakeModel(scanner);
                    break;
                case 3:
                    searchVehiclesByPriceRange(scanner);
                    break;
                case 4:
                    searchVehiclesByYearRange(scanner);
                    break;
                case 5:
                    searchVehiclesByColor(scanner);
                    break;
                case 6:
                    searchVehiclesByMileageRange(scanner);
                    break;
                case 7:
                    searchVehiclesByType(scanner);
                    break;
                case 8:
                    openAdminMenu(scanner);
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

    // Create searchVehiclesByMakeModel method.
    private void searchVehiclesByMakeModel(Scanner scanner) {
        // Ask user for the make.
        System.out.println("Enter make:");
        String make = scanner.nextLine();

        // Ask user for the model.
        System.out.println("Enter model:");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDataManager.getVehiclesByMakeModel(make, model);
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Create searchVehiclesByPriceRange method.
    private void searchVehiclesByPriceRange(Scanner scanner) {
        // Ask user for the minimum price.
        System.out.println("Enter minimum price:");
        double minPrice = scanner.nextDouble();
        scanner.nextLine();

        // Ask user for the maximum price.
        System.out.println("Enter maximum price:");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();

        List<Vehicle> vehicles = vehicleDataManager.getVehiclesByPriceRange(minPrice, maxPrice);
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Create searchVehiclesByYearRange method.
    private void searchVehiclesByYearRange(Scanner scanner) {
        // Ask user for the minimum year.
        System.out.println("Enter minimum year:");
        int minYear = scanner.nextInt();
        scanner.nextLine();

        // Ask user for the maximum year.
        System.out.println("Enter maximum year:");
        int maxYear = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = vehicleDataManager.getVehiclesByYearRange(minYear, maxYear);
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Create searchVehiclesByColor method.
    private void searchVehiclesByColor(Scanner scanner) {
        // Ask user for the color.
        System.out.println("Enter color:");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDataManager.getVehiclesByColor(color);
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Create searchVehiclesByMilageRange method.
    private void searchVehiclesByMileageRange(Scanner scanner) {
        // Ask user for the minimum mileage.
        System.out.println("Enter minimum mileage:");
        int minMileage = scanner.nextInt();
        scanner.nextLine();

        // Ask user for the maximum mileage.
        System.out.println("Enter maximum mileage:");
        int maxMileage = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = vehicleDataManager.getVehiclesByMileageRange(minMileage, maxMileage);
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Create searchVehiclesByType method.
    private void searchVehiclesByType(Scanner scanner) {
        // Ask user for the vehicle type.
        System.out.println("Enter vehicle type:");
        String vehicleType = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDataManager.getVehiclesByType(vehicleType);
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Create openAdminMenu method.
    private void openAdminMenu(Scanner scanner) {
        // Ask user for the admin password.
        System.out.println("Enter password to access admin menu:");
        String password = scanner.nextLine();

        // If it isn't admin123, print incorrect error messsage.
        if ("admin123".equals(password)) {
            adminUI.displayAdminMenu();
        } else {
            System.out.println("Incorrect password. Access denied.");
        }
    }
}