import product.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Store {
    int storeId;
    String name;
    List<VehicleManager> vehicleManagerList;
    Location location;
    List <Reservation> reservationList;

    public int getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public List<VehicleManager> getVehicleManagerList() {
        return vehicleManagerList;
    }

    public Location getLocation() {
        return location;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
    //VehicleManager vehicleManager;

    public Store(int storeId, String name, Location location) {
        this.storeId = storeId;
        this.name = name;
        this.location = location;
        this.reservationList = new ArrayList<>();
    }

    public void addReservationList(Reservation reservation) {
        this.reservationList.add(reservation);
    }

    public void setVehicleManagerList(List<VehicleManager> vehicleManagerList) {
        this.vehicleManagerList = vehicleManagerList;
    }

    public VehicleManager getVehicleManager(String vehicle){
        for(VehicleManager vehicleManager : vehicleManagerList){
            if(vehicleManager instanceof BikeManager && vehicle.equalsIgnoreCase(VehicleType.Bike.toString())
                || (vehicleManager instanceof ScootyManager && vehicle.equalsIgnoreCase(VehicleType.Scooty.toString()))
                || (vehicleManager instanceof CarManager && vehicle.equalsIgnoreCase(VehicleType.Car.toString())))
                return vehicleManager;
        }
        return null;
    }
}
