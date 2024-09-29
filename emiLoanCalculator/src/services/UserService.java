package services;

import factories.UserFactory;
import models.User;

import java.util.HashMap;

public class UserService {
    private HashMap<String, User> users;
    private static volatile UserService instance = null;

    private UserService(){
        this.users = new HashMap<>();
    }

    public static UserService getInstance(){
        if(instance == null){
            synchronized (UserService.class){
                if(instance == null){
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public void addUser(String userName, String name, boolean isAdmin){
        User user = UserFactory.getUser(userName, name, isAdmin);
        users.put(userName, user);
    }

    public User getUser(String userName){
        return users.get(userName);
    }

}
