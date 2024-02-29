package lld.Parking_Lot.model.parkingspot;

public class FourWheelerParkingSpot extends ParkingSpot {

    public FourWheelerParkingSpot(){
        super();
    }
    
    @Override
    public void getPrice(){
        price = 100;
    }
}
