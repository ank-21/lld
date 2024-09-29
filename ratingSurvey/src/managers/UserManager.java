package managers;

import models.User;
import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users;

    private UserManager() {
        this.users = new HashMap<>();
    }

    public static volatile UserManager instance = null;

    public static UserManager getInstance(){
        if(instance == null){
            synchronized (UserManager.class){
                if(instance == null){
                    return instance = new UserManager();
                }
            }
        }
        return instance;
    }

    public void addUser(String userId, String userName){
        if (validateUser(userId)){
            User user = new User(userId, userName);
            users.put(userId, user);
        }
    }

    private boolean validateUser(String userId){
        User user = users.get(userId);
        return user == null;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }
}
