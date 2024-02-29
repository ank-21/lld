package lld.Parking_Lot.model.parkingspot;

import java.util.ArrayList;
import java.util.List;

import lld.Parking_Lot.model.parkingspot.strategy.NearToElevatorPSStrategy;

public class TwoWheelerParkingSpotManager extends ParkingSpotManager {

    static List<ParkingSpot> parkingspotsList = new ArrayList<>();
    //private int numberOfSpots = 20;
    //define list with near to entrance or exit separately here


    public TwoWheelerParkingSpotManager() {
       
        super(parkingspotsList);
        //need to check if that's needed
        parkingspotsList.add(new TwoWheelerParkingSpot());
    }

    public ParkingSpot findParkingSpace(){
        return new NearToElevatorPSStrategy().findParkingSpace();
    }
}
