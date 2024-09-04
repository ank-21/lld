public class Vehicle {
    private String id;
    private String brand;
    private String model;
    private String year;
    private String licensePlateNumber;
    private int pricePerDay;
    private VehicleType vehicleType;
    private VehicleStatus status;

    public Vehicle(String id, String brand, String model, String year, String licensePlateNumber, int pricePerDay, VehicleType vehicleType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licensePlateNumber = licensePlateNumber;
        this.pricePerDay = pricePerDay;
        this.vehicleType = vehicleType;
        this.status = VehicleStatus.Available;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}
