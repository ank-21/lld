public class User {
    private String userId;
    private String name;
    private int drivingLicense;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrivingLicense(int drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getDrivingLicense() {
        return drivingLicense;
    }
}
