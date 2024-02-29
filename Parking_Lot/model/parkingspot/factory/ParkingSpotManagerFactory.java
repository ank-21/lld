package lld.Parking_Lot.model.parkingspot.factory;

import lld.Parking_Lot.model.parkingspot.FourWheelerParkingSpotManager;
import lld.Parking_Lot.model.parkingspot.ParkingSpotManager;
import lld.Parking_Lot.model.parkingspot.TwoWheelerParkingSpotManager;
import lld.Parking_Lot.model.vehicle.VehicleType;
import lld.Parking_Lot.model.vehicle.Vehicle;

public class ParkingSpotManagerFactory {

    public ParkingSpotManager getParkingSpotManager(Vehicle vehicle){
        
        //can also send only vehicleType
        //not needed now
        //ParkingStyleStrategy parkingStyleStrategyObj = selectParkingStyleStrategy(parkingStyleType);
        
        if(vehicle.getVehicleType() == VehicleType.Two_wheeler){
            return new TwoWheelerParkingSpotManager();
        } else if(vehicle.getVehicleType() == VehicleType.Four_Wheeler){
            return new FourWheelerParkingSpotManager();
        }
        return null;
    }

    // private ParkingStyleStrategy selectParkingStyleStrategy(ParkingStyleType parkingStyleType){

    //     if(parkingStyleType == ParkingStyleType.NearToElevatorPSStrategy){
    //         return new NearToElevatorPSStrategy();
    //     } else if(parkingStyleType == ParkingStyleType.NearToEntrancePSStrategy){
    //         return new NearToEntrancePSStrategy();
    //     } else if(parkingStyleType == ParkingStyleType.NearToExitPSStrategy){
    //         return new NearToExitPSStrategy();
    //     } else{
    //         System.out.println("Invalid parking style type");
    //         throw new IllegalArgumentException("Invalid parking style type");
    //     }
    // }
}
