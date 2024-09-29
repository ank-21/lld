package factories;

import models.Admin;
import models.Customer;
import models.User;

public class UserFactory {
    public static User getUser(String userName, String name, Boolean isAdmin){
        if(isAdmin){
            return new Admin(userName, name);
        }else{
            return new Customer(userName, name);
        }
    }
}
