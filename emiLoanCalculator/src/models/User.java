package models;

public class User {
    private String userName;
    private String name;

    public User(String userName, String name) {
        this.userName = userName;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }
}
