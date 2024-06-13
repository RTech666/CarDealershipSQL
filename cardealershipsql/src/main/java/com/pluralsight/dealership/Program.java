package com.pluralsight.dealership;

public class Program {
    public static void main(String[] args) {
        // Initalize the data managers.
        VehicleDataManager vehicleDataManager = new VehicleDataManager();
        ContractDataManager contractDataManager = new ContractDataManager();
        DealershipDataManager dealershipDataManager = new DealershipDataManager();
        
        // Initalize the variable.
        int dealershipId = 1;

        // Retrieve the dealership in the database.
        Dealership dealership = dealershipDataManager.getDealershipByID(dealershipId);
        
        // If no dealership exists, create one.
        if (dealership == null) {
            dealership = new Dealership(dealershipId, "Shed's Dealership", "123 Dealership Street", "123-456-7890");
            dealershipDataManager.saveDealership(dealership);
        }

        // Retrieve all the cars from the database.
        dealership.setInventory(vehicleDataManager.getAllVehicles());

        // Initalize the interfaces.
        AdminUserInterface adminUI = new AdminUserInterface(dealership);
        UserInterface ui = new UserInterface(dealership, adminUI);

        // Print main menu.
        ui.displayMainMenu();
    }
}