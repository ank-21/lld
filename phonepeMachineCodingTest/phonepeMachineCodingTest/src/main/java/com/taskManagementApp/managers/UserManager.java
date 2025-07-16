package main.java.com.taskManagementApp.managers;

import main.java.com.taskManagementApp.models.TaskEntity;
import main.java.com.taskManagementApp.models.User;
import main.java.com.taskManagementApp.exceptions.UserIdAlreadyPresentException;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private static volatile UserManager INSTANCE;
    private final Map<String, User> users;

    private UserManager(){
        users = new ConcurrentHashMap<>();
    }

    public static UserManager getInstance(){
        if(INSTANCE == null){
            synchronized (TaskManager.class){
                if(INSTANCE == null){
                    return INSTANCE = new UserManager();
                }
            }
        }
        return INSTANCE;
    }

    public void addUser(String userId, String userName, String email) {
        try{
            if(users.putIfAbsent(userId, new User(userId, userName, email)) == null){
                System.out.println("User " + userName + " added successfully!");
            }else{
                throw new UserIdAlreadyPresentException("User Id " + userId + " already present!");
            }
        } catch (UserIdAlreadyPresentException e) {
            // Add logger interface - functionality
            System.err.println(e.getMessage());
        }
    }

    protected synchronized void addTaskToUser(User user, TaskEntity task){
        Objects.requireNonNull(task, "Task cannot be null");
        user.addTask(task);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void listUsers() {
        users.values().forEach(user -> System.out.println(user));
    }
}
