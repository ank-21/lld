package product;

public class Car extends Vehicle{

    public Car(){
        setDailyRentalCost(1000);
        setHourlyRentalCost(400);
        setStatus(Status.Active);
        setVehicleType(VehicleType.Car);
        setPetrolLeft(2.0);
        setKmDriven(0);
    }
}
