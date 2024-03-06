import product.Scooty;
import product.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ScootyManager implements VehicleManager{

    List<Scooty> scooties = new ArrayList<>();
    public ScootyManager(){
        //Adding 4 scooty from the start
        addVehicle(1, 1001);
        addVehicle(2, 1002);
        addVehicle(3, 1003);
        addVehicle(4, 1004);
    }

    public List<Scooty> getScooties() {
        return scooties;
    }

    public void addVehicle(int vehicleId, int vehicleNumber){
        Scooty scooty = new Scooty();
        scooty.setVehicleId(vehicleId);
        scooty.setVehicleNumber(vehicleNumber);
        scooties.add(scooty);
    }
}
