package lld.Parking_Lot.model.entrancegate;

import java.time.LocalDateTime;

import lld.Parking_Lot.model.parkingspot.ParkingSpot;
import lld.Parking_Lot.model.parkingspot.ParkingSpotManager;
import lld.Parking_Lot.model.parkingspot.factory.ParkingSpotManagerFactory;
import lld.Parking_Lot.model.ticket.ParkingTicket;
import lld.Parking_Lot.model.vehicle.Vehicle;

public class EntranceGate {
    private int entranceGateNo;
    ParkingSpotManagerFactory parkingSpotManagerFactory;
    public ParkingSpotManager parkingSpotManager;

    EntranceGate(int entranceGateNo, ParkingSpotManagerFactory parkingSpotManagerFactory){
        this.entranceGateNo = entranceGateNo;
        this.parkingSpotManagerFactory = parkingSpotManagerFactory;
    }

    public int getEntranceGateNo() {
        return entranceGateNo;
    }
    
    public ParkingTicket findAndBookSpace(Vehicle vehicle){
        
        parkingSpotManager = parkingSpotManagerFactory.getParkingSpotManager(vehicle);
        //Till now only printing parking spot
        //we will use entranceGateNo then

        //should I use this id as space and then use it for booking spot
        ParkingSpot spot = parkingSpotManager.parkVehicle(vehicle);
        LocalDateTime currentDateTime = LocalDateTime.now();
        ParkingTicket ticket = new ParkingTicket(vehicle, spot, currentDateTime);
        return ticket;
    }

}
