package model;

public class User {
    private String name;
    private String Id;
    private String email;
    private UserExpenseBalanceSheet balanceSheet;

    public User(String name, String id, String email) {
        this.name = name;
        Id = id;
        this.email = email;
        balanceSheet = new UserExpenseBalanceSheet();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public UserExpenseBalanceSheet getBalanceSheet() {
        return balanceSheet;
    }
}
