import java.util.List;

public class RentalSystemAdmin {
    private static volatile RentalSystemAdmin instance = null;
    private VehicleManager manager;
    // Can use a list of managers later on

    private RentalSystemAdmin(){
        manager = new CarManager();
    }

    public static RentalSystemAdmin getInstance(){
        if(instance == null){
            synchronized (RentalSystem.class){
                if (instance == null){
                    instance = new RentalSystemAdmin();
                }
            }
        }
        return instance;
    }

    public void addVehicle(String id, String brand, String model, String year, String licensePlateNumber, int pricePerDay, VehicleType vehicleType){
        // Can add a factory pattern to invoke vehicle object based on vehicleType
        // For now, simply invoking car class
        // For now, not using vehicleType
        Vehicle vehicle = new Car(id, brand, model, year, licensePlateNumber, pricePerDay);
        manager.getVehicleList().add(vehicle);
        manager.getVehicleDetailsById().put(id, vehicle);
    }

    public void showAllVehicles(){
        List<Vehicle> vehicles = manager.getVehicleList();

        System.out.println("Vehicles Details are : ");
        for(Vehicle vehicle : vehicles){
            System.out.print("ID: " + vehicle.getId());
            System.out.print(" Brand: " + vehicle.getBrand());
            System.out.print(" Model: " + vehicle.getModel());
            System.out.print(" Year: " + vehicle.getYear());
            System.out.print(" Price Per day: " + vehicle.getPricePerDay());
            System.out.print(" Booking Status: " + vehicle.getStatus());
            System.out.println();
        }
    }

    public Vehicle getVehicleById(String vehicleId) throws IllegalArgumentException, IllegalStateException{
        Vehicle vehicle = manager.getVehicle(vehicleId);
            if (vehicle == null){
                throw new IllegalArgumentException("The vehicle id is incorrect");
            } else if (vehicle.getStatus() != VehicleStatus.Available) {
                throw new IllegalStateException("The vehicle is already booked");
            }
            return vehicle;
    }
}
