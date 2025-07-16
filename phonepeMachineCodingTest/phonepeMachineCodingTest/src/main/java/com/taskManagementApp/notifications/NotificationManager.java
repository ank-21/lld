package main.java.com.taskManagementApp.notifications;

import main.java.com.taskManagementApp.models.Observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// instead create an interface and then implement this class based on let's say push notification
public class NotificationManager {
    private static volatile NotificationManager INSTANCE;
    private final List<Observer> observers;

    private NotificationManager() {
        this.observers = new CopyOnWriteArrayList<>();
    }

    public static NotificationManager getInstance(){
        if(INSTANCE == null){
            synchronized (NotificationManager.class){
                if(INSTANCE == null){
                    INSTANCE = new NotificationManager();
                }
            }
        }
        return INSTANCE;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
