package product;

public class Vehicle {
    int VehicleId;
    int VehicleNumber;
    VehicleType vehicleType;
    double kmDriven;
    double petrolLeft;
    int dailyRentalCost;
    int hourlyRentalCost;
    Status status;

    public int getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(int vehicleId) {
        VehicleId = vehicleId;
    }

    public int getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }

    public double getPetrolLeft() {
        return petrolLeft;
    }

    public void setPetrolLeft(double petrolLeft) {
        this.petrolLeft = petrolLeft;
    }

    public int getDailyRentalCost() {
        return dailyRentalCost;
    }

    public void setDailyRentalCost(int dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }

    public int getHourlyRentalCost() {
        return hourlyRentalCost;
    }

    public void setHourlyRentalCost(int hourlyRentalCost) {
        this.hourlyRentalCost = hourlyRentalCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
