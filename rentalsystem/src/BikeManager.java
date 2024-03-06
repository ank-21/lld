import product.Bike;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BikeManager implements VehicleManager {
    private List<Bike> bikes = new ArrayList<>();

    public BikeManager(){
        //add random here

        addVehicle(1, 1001);
        addVehicle(2, 1002);
        addVehicle(3, 1003);
        addVehicle(4, 1004);
    }


    public List<Bike> getBikes() {
        return bikes;
    }

    public void addVehicle(int vehicleId, int vehicleNumber){
        Bike bike = new Bike();
        bike.setVehicleId(vehicleId);
        bike.setVehicleNumber(vehicleNumber);
        bikes.add(bike);
    }
}
