package model;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    List<User> userList;

    public UserController() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user){
        this.userList.add(user);
    }

    public User getUser(String userId){
        for(User user : userList){
            if(userId.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

    public List<User> getUserList() {
        return userList;
    }
}
