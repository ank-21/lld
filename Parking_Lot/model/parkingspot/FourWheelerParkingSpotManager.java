package lld.Parking_Lot.model.parkingspot;

import java.util.ArrayList;
import java.util.List;

import lld.Parking_Lot.model.parkingspot.strategy.NearToEntrancePSStrategy;

public class FourWheelerParkingSpotManager extends ParkingSpotManager {

    static List<ParkingSpot> parkingspotsList = new ArrayList<>();
    //private int numberOfSpots = 20;

    public FourWheelerParkingSpotManager() {
        // user will decide the parking strategy type
        super(parkingspotsList);
        //parkingspotsList.add(new FourWheelerParkingSpot());
    }

    public ParkingSpot findParkingSpace(){
        return new NearToEntrancePSStrategy().findParkingSpace();
    }

}
