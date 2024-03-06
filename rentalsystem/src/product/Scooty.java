package product;

public class Scooty extends Vehicle{

    public Scooty(){
        setDailyRentalCost(300);
        setHourlyRentalCost(125);
        setVehicleType(VehicleType.Scooty);
        setStatus(Status.Active);
        setPetrolLeft(0.5);
        setKmDriven(0);
    }
}
