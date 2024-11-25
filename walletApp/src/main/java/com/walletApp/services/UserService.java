package main.java.com.walletApp.services;

import main.java.com.walletApp.models.User;
import main.java.com.walletApp.utils.exceptions.UserNotFound;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private final Map<String, User> users;
    private static volatile UserService INSTANCE;
    private UserService(){
        users = new ConcurrentHashMap<>();
    }

    public static UserService getInstance(){
        if(INSTANCE == null){
            synchronized (UserService.class){
                if(INSTANCE == null){
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public void registerUser(String userId, String name) throws Exception {
        // Handle Auth here
        if(validateUser(userId)){
            throw new Exception("User already present");
        }
        User user = new User(userId, name);
        users.put(userId, user);
    }

    protected boolean validateUser(String userId){
        return users.containsKey(userId);
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
