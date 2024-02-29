package lld.Parking_Lot.model.parkingspot;

import java.util.ArrayList;
import java.util.List;

import lld.Parking_Lot.model.parkingspot.strategy.ParkingStyleStrategy;
import lld.Parking_Lot.model.vehicle.Vehicle;

public abstract class ParkingSpotManager {

    List<ParkingSpot> parkingspotsList = new ArrayList<>();
    ParkingStyleStrategy psStrategyObj;

    public ParkingSpotManager(List<ParkingSpot> parkingspotsList) {
        this.parkingspotsList = parkingspotsList;
    }

    public abstract ParkingSpot findParkingSpace();

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        
        ParkingSpot spot = findParkingSpace();
        
        if(spot.isEmpty()){
            spot.parkVehicle(vehicle);
            System.out.println("Vehicle added to the parking lot");
            return spot;
        }
        System.out.println("Parking Spot already in use");
        return null;
    }

    public void removeVehicle(Vehicle vehicle) {
        
        for (ParkingSpot spot : parkingspotsList) {
            if (spot.getVehicle() != null && spot.getVehicle().equals(vehicle)) {
                spot.removeVehicle(vehicle);
                System.out.println("Vehicle removed from the parking lot");
            }
        }
    }
}
