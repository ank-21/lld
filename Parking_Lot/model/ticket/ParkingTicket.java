package lld.Parking_Lot.model.ticket;

import java.time.LocalDateTime;

import lld.Parking_Lot.model.parkingspot.ParkingSpot;
import lld.Parking_Lot.model.vehicle.Vehicle;

public class ParkingTicket {
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;

    public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot, LocalDateTime entryTime) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
