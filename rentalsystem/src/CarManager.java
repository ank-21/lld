import product.Car;

import java.util.ArrayList;
import java.util.List;

public class CarManager implements VehicleManager {
    private List<Car> cars = new ArrayList<>();

    public CarManager(){
        //use different id for diff store
        addVehicle(1, 1100);
        addVehicle(2, 1101);
        addVehicle(3, 1102);
        addVehicle(4, 1103);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addVehicle(int vehicleId, int vehicleNumber){

        Car car = new Car();
        car.setVehicleId(vehicleId);
        car.setVehicleNumber(vehicleNumber);
        cars.add(car);
    }
}
