package lld.Parking_Lot.model.parkingspot;

public class TwoWheelerParkingSpot extends ParkingSpot {

    public TwoWheelerParkingSpot() {
        super(); // Explicitly calling the superclass constructor
    }
    
    @Override
    public void getPrice(){
        price = 50;
    }
}
