package lld.Parking_Lot.model.parkingspot;

import java.util.concurrent.atomic.AtomicInteger;

import lld.Parking_Lot.model.vehicle.Vehicle;

public abstract class ParkingSpot {

    private int id;

    private boolean isEmpty;

    private Vehicle vehicle;

    protected int price;

    private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);

    public ParkingSpot() {
        // Assign a unique ID to each instance
        this.id = ID_COUNTER.incrementAndGet();
        this.isEmpty = true;
        this.vehicle = null;
    }

    public abstract void getPrice();

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isEmpty = false;
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicle = null;
        this.isEmpty = true;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
