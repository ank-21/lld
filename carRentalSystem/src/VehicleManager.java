import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class VehicleManager {
    private List<Vehicle> vehicleList;
    private Map<String, Vehicle> vehicleDetailsById;

    public VehicleManager() {
        this.vehicleList = new ArrayList<>();
        this.vehicleDetailsById = new ConcurrentHashMap<>();
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public Map<String, Vehicle> getVehicleDetailsById() {
        return vehicleDetailsById;
    }

    public Vehicle getVehicle(String vehicleId){
        return vehicleDetailsById.get(vehicleId);
    }
}
