public class Customer {
    String name;
    String id;
    String drivingLicense;
    String emailId;

    public Customer(String name, String id, String drivingLicense, String emailId) {
        this.name = name;
        this.id = id;
        this.drivingLicense = drivingLicense;
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public String getEmailId() {
        return emailId;
    }
}
