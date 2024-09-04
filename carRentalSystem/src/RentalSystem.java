import java.time.LocalDateTime;

public class RentalSystem {
    private static volatile RentalSystem instance = null;
    private RentalSystemAdmin admin;
    private RentalSystem(){
        admin = RentalSystemAdmin.getInstance();
        initiateSetup();
    }

    public static RentalSystem getInstance(){
        if(instance == null){
            synchronized (RentalSystem.class){
                if (instance == null){
                    instance = new RentalSystem();
                }
            }
        }
        return instance;
    }

    public void initiateSetup(){
        addVehicle("1001", "Hyundai", "i20", "2023", "BR061213", 700, VehicleType.Car);
        addVehicle("1002", "Hyundai", "i20", "2024", "BR061344", 700, VehicleType.Car);
        addVehicle("1003", "Maruti Suzuki", "Ertiga", "2022", "BR099113", 1200, VehicleType.Car);
        addVehicle("1004", "Hyundai", "Venue", "2024", "BR230013", 900, VehicleType.Car);
        addVehicle("1005", "Tata Motors", "Punch", "2024", "BR340442", 500, VehicleType.Car);
        addVehicle("1006", "Tata Motors", "Nexon", "2023", "BR030985", 850, VehicleType.Car);
        addVehicle("1007", "Maruti Suzuki", "Swift", "2024", "BR123003", 700, VehicleType.Car);
    }

    public void addVehicle(String id, String brand, String model, String year, String licensePlateNumber, int pricePerDay, VehicleType vehicleType){
        admin.addVehicle(id, brand, model, year, licensePlateNumber, pricePerDay, vehicleType);
    }

    public void showAllVehicles(){
        admin.showAllVehicles();
    }

    public void addReservation(String vehicleId, Customer customer){
        try {
            Vehicle vehicle = admin.getVehicleById(vehicleId);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime endTime = now.plusDays(2).withHour(9).withMinute(0).withSecond(0);
            int totalPrice = vehicle.getPricePerDay() * 2;
            Reservation reservation = new Reservation("2001", customer, vehicle, now, endTime, totalPrice, ReservationStatus.Ongoing);
            vehicle.setStatus(VehicleStatus.Booked);
            System.out.println("Your reservation is completed with the following details");
            System.out.print("Reservation ID: " + reservation.getReservationId());
            System.out.print(" Customer Name: " + reservation.getCustomer().getId());
            System.out.print(" Vehicle Id: " + reservation.getVehicle().getId());
            System.out.print(" Brand: " + reservation.getVehicle().getBrand());
            System.out.print(" Model: " + reservation.getVehicle().getModel());
            System.out.print(" Start Date: " + reservation.getBookingFrom());
            System.out.print(" End Date: " + reservation.getBookingTill());
            System.out.print(" Price: " + reservation.getTotalPrice());
            System.out.print(" Booking Status: " + reservation.getReservationStatus());
            System.out.println();
        }catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
