package product;

public class Bike extends Vehicle{

    public Bike(){
        setDailyRentalCost(600);
        setHourlyRentalCost(250);
        setStatus(Status.Active);
        setVehicleType(VehicleType.Bike);
        setPetrolLeft(1.0);
        setKmDriven(0);
    }
}
