package lld.Parking_Lot.model.vehicle;

public class Vehicle {

    private String vehicleNumber;
    private VehicleType vehicleType;
    //private int id; - implement this later
    // define getter for id


    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
