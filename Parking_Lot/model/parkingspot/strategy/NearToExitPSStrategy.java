package lld.Parking_Lot.model.parkingspot.strategy;

import lld.Parking_Lot.model.parkingspot.ParkingSpot;

public class NearToExitPSStrategy extends ParkingStyleStrategy{
    
    @Override
    public ParkingSpot findParkingSpace(){
        System.out.println("Parking near exit");
        return null;
    }
}
