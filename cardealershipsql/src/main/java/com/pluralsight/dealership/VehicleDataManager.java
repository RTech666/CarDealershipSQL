package com.pluralsight.dealership;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDataManager {
    // Create database login variables.
    private static final String database = "jdbc:mysql://localhost:3306/cardealershipdatabase";
    private static final String databaseUser = "root";
    private static final String databasePass = "TC2%T@ajrGUhcB";

    // Create addVehicle method.
    public void addVehicle(Vehicle vehicle) {
        // Create the query.
        String sql = "INSERT INTO vehicles (VIN, Year, Make, Model, Type, Color, Odometer, Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getVehicleType());
            stmt.setString(6, vehicle.getColor());
            stmt.setInt(7, vehicle.getOdometer());
            stmt.setDouble(8, vehicle.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create getAllVehicles method.
    public List<Vehicle> getAllVehicles() {
        // Initalize the array.
        List<Vehicle> vehicles = new ArrayList<>();

        // Create the query.
        String sql = "SELECT * FROM vehicles";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String vin = rs.getString("VIN");
                int year = rs.getInt("Year");
                String make = rs.getString("Make");
                String model = rs.getString("Model");
                String vehicleType = rs.getString("Type");
                String color = rs.getString("Color");
                int odometer = rs.getInt("Odometer");
                double price = rs.getDouble("Price");
                vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Create getVehicleByVIN method.
    public Vehicle getVehicleByVIN(String vin) {
        // Initalize the variable.
        Vehicle vehicle = null;

        // Create the query.
        String sql = "SELECT * FROM vehicles WHERE vin = ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vin);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int year = rs.getInt("Year");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    String vehicleType = rs.getString("Type");
                    String color = rs.getString("Color");
                    int odometer = rs.getInt("Odometer");
                    double price = rs.getDouble("Price");
                    vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    // Create updateVehicle method.
    public void updateVehicle(Vehicle vehicle) {
        // Create the query.
        String sql = "UPDATE vehicles SET Year = ?, Make = ?, Model = ?, Type = ?, Color = ?, Odometer = ?, Price = ? WHERE VIN = ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicle.getYear());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getVehicleType());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getOdometer());
            stmt.setDouble(7, vehicle.getPrice());
            stmt.setString(8, vehicle.getVin());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create deleteVehicle method.
    public void deleteVehicle(String vin) {
        // Create the query.
        String sql = "DELETE FROM vehicles WHERE VIN = ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vin);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create getVehiclesByMakeModel method.
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        // Initalize the array.
        List<Vehicle> vehicles = new ArrayList<>();

        // Create the query.
        String sql = "SELECT * FROM vehicles WHERE Make = ? AND Model = ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, make);
            stmt.setString(2, model);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String vin = rs.getString("VIN");
                    int year = rs.getInt("Year");
                    String vehicleType = rs.getString("VehicleType");
                    String color = rs.getString("Color");
                    int odometer = rs.getInt("Odometer");
                    double price = rs.getDouble("Price");
                    vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Create getVehiclesByYearRange method.
    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) {
        // Initalize the array.
        List<Vehicle> vehicles = new ArrayList<>();

        // Create the query.
        String sql = "SELECT * FROM vehicles WHERE Year BETWEEN ? AND ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, minYear);
            stmt.setInt(2, maxYear);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String vin = rs.getString("VIN");
                    int year = rs.getInt("Year");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    String vehicleType = rs.getString("Type");
                    String color = rs.getString("Color");
                    int odometer = rs.getInt("Odometer");
                    double price = rs.getDouble("Price");
                    vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Create getVehiclesByPriceRange method.
    public List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice) {
        // Initalize the array.
        List<Vehicle> vehicles = new ArrayList<>();

        // Create the query.
        String sql = "SELECT * FROM vehicles WHERE Price BETWEEN ? AND ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String vin = rs.getString("VIN");
                    int year = rs.getInt("Year");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    String vehicleType = rs.getString("Type");
                    String color = rs.getString("Color");
                    int odometer = rs.getInt("Odometer");
                    double price = rs.getDouble("Price");
                    vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Create getVehiclesByColor method.
    public List<Vehicle> getVehiclesByColor(String color) {
        // Initalize the array.
        List<Vehicle> vehicles = new ArrayList<>();

        // Create the query.
        String sql = "SELECT * FROM vehicles WHERE Color = ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, color);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String vin = rs.getString("VIN");
                    int year = rs.getInt("Year");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    String vehicleType = rs.getString("Type");
                    String vehicleColor = rs.getString("Color");
                    int odometer = rs.getInt("Odometer");
                    double price = rs.getDouble("Price");
                    vehicles.add(new Vehicle(vin, year, make, model, vehicleType, vehicleColor, odometer, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Create getVehiclesByMileageRange method.
    public List<Vehicle> getVehiclesByMileageRange(int minMileage, int maxMileage) {
        // Initalize the array.
        List<Vehicle> vehicles = new ArrayList<>();

        // Create the query.
        String sql = "SELECT * FROM vehicles WHERE Odometer BETWEEN ? AND ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, minMileage);
            stmt.setInt(2, maxMileage);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String vin = rs.getString("VIN");
                    int year = rs.getInt("Year");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    String vehicleType = rs.getString("Type");
                    String color = rs.getString("Color");
                    int odometer = rs.getInt("Odometer");
                    double price = rs.getDouble("Price");
                    vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Create getVehiclesByType method.
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        // Initalize the array.
        List<Vehicle> vehicles = new ArrayList<>();

        // Create the query.
        String sql = "SELECT * FROM vehicles WHERE Type = ?";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleType);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String vin = rs.getString("VIN");
                    int year = rs.getInt("Year");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    String type = rs.getString("Type");
                    String color = rs.getString("Color");
                    int odometer = rs.getInt("Odometer");
                    double price = rs.getDouble("Price");
                    vehicles.add(new Vehicle(vin, year, make, model, type, color, odometer, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}