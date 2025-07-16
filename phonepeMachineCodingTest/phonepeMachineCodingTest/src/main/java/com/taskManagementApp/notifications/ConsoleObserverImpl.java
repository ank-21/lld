package main.java.com.taskManagementApp.notifications;

import main.java.com.taskManagementApp.models.Observer;

public class ConsoleObserverImpl implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Console Notification: " + message);
    }
}
