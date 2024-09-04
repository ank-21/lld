public class Car extends Vehicle{
    public Car(String id, String brand, String model, String year, String licensePlateNumber, int pricePerDay) {
        super(id, brand, model, year, licensePlateNumber, pricePerDay, VehicleType.Car);
    }
}
