package com.pluralsight.dealership;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDataManager {
    // Create database login variables.
    private static final String database = "jdbc:mysql://localhost:3306/cardealershipdatabase";
    private static final String databaseUser = "root";
    private static final String databasePass = "TC2%T@ajrGUhcB";

    // Create getAllContracts method.
    public List<Contract> getAllContracts() {
        // Initalize the array.
        List<Contract> contracts = new ArrayList<>();

        // Create the query.
        String sql = "SELECT * FROM sale_contracts";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contract contract = createContractFromResultSet(rs);
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    // Create getLastTenContracts method.
    public List<Contract> getLastTenContracts() {
        // Initalize the array.
        List<Contract> contracts = new ArrayList<>();
        
        // Create the query.
        String sql = "SELECT * FROM sale_contracts ORDER BY id DESC LIMIT 10";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contract contract = createContractFromResultSet(rs);
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    // Create saveContract method.
    public void saveContract(Contract contract) {
        // Create the query.
        String sql = "INSERT INTO sale_contracts (ID, ContractType, Date, CustomerName, CustomerEmail, VehicleVIN, VehicleYear, VehicleMake, VehicleModel, VehicleType, VehicleColor, VehicleOdometer, VehiclePrice, TotalPrice, SalesTax, RecordingFee, ProcessingFee, LeaseFee, FinanceOption, MonthlyPayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contract.getID());
            stmt.setString(2, (contract instanceof SalesContract) ? "SALE" : "LEASE");
            stmt.setString(3, contract.getContractDate());
            stmt.setString(4, contract.getCustomerName());
            stmt.setString(5, contract.getCustomerEmail());
            stmt.setString(6, contract.getVehicleSold().getVin());
            stmt.setInt(7, contract.getVehicleSold().getYear());
            stmt.setString(8, contract.getVehicleSold().getMake());
            stmt.setString(9, contract.getVehicleSold().getModel());
            stmt.setString(10, contract.getVehicleSold().getVehicleType());
            stmt.setString(11, contract.getVehicleSold().getColor());
            stmt.setInt(12, contract.getVehicleSold().getOdometer());
            stmt.setDouble(13, contract.getVehicleSold().getPrice());
            stmt.setDouble(14, contract.getTotalPrice());
            stmt.setDouble(15, (contract instanceof SalesContract) ? ((SalesContract) contract).getSalesTax() : 0.0);
            stmt.setDouble(16, (contract instanceof SalesContract) ? ((SalesContract) contract).getRecordingFee() : 0.0);
            stmt.setDouble(17, (contract instanceof SalesContract) ? ((SalesContract) contract).getProcessingFee() : 0.0);
            stmt.setDouble(18, (contract instanceof LeaseContract) ? ((LeaseContract) contract).getLeaseFee() : 0.0);
            stmt.setBoolean(19, (contract instanceof SalesContract) && ((SalesContract) contract).isFinanceOption());
            stmt.setDouble(20, (contract instanceof LeaseContract) ? ((LeaseContract) contract).getMonthlyPayment() : 0.0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create createContractFromResultSet method.
    private Contract createContractFromResultSet(ResultSet rs) throws SQLException {
        // Get all the entries in the database.
        int ID = rs.getInt("ID");
        String contractType = rs.getString("ContractType");
        String date = rs.getString("Date");
        String customerName = rs.getString("CustomerName");
        String customerEmail = rs.getString("CustomerEmail");
        String vehicleVin = rs.getString("VehicleVIN");
        int vehicleYear = rs.getInt("VehicleYear");
        String vehicleMake = rs.getString("VehicleMake");
        String vehicleModel = rs.getString("VehicleModel");
        String vehicleType = rs.getString("VehicleType");
        String vehicleColor = rs.getString("VehicleColor");
        int vehicleOdometer = rs.getInt("VehicleOdometer");
        double vehiclePrice = rs.getDouble("VehiclePrice");
        double totalPrice = rs.getDouble("TotalPrice");

        // Create the vehicle.
        Vehicle vehicle = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleOdometer, vehiclePrice);

        // If its a sale contract, add the salesTax, recordingFee, processingFee, and financeOptions to the vehicle entry.
        if ("SALE".equals(contractType)) {
            double salesTax = rs.getDouble("SalesTax");
            double recordingFee = rs.getDouble("RecordingFee");
            double processingFee = rs.getDouble("ProcessingFee");
            boolean financeOption = rs.getBoolean("FinanceOption");
            SalesContract salesContract = new SalesContract(ID, date, customerName, customerEmail, vehicle, salesTax, recordingFee, processingFee);
            salesContract.setFinanceOption(financeOption);
            return salesContract;
        // If its a lease contract, add the leaseFee and monthlyPayment to the vehicle entry.
        } else {
            double leaseFee = rs.getDouble("LeaseFee");
            double monthlyPayment = rs.getDouble("MonthlyPayment");
            LeaseContract leaseContract = new LeaseContract(ID, date, customerName, customerEmail, vehicle, leaseFee, monthlyPayment);
            return leaseContract;
        }
    }
}