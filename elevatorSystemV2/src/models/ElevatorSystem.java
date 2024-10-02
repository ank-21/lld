package models;

import controllers.ElevatorController;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private List<ElevatorController> elevatorControllerList = new ArrayList<ElevatorController>();
    public List<Floor> floors = new ArrayList<>();

    private static volatile ElevatorSystem instance = null;

    private ElevatorSystem(){}

    public static ElevatorSystem getInstance(){
        if(instance == null){
            synchronized (ElevatorSystem.class){
                if (instance == null){
                    return instance = new ElevatorSystem();
                }
            }
        }
        return instance;
    }

    public List<ElevatorController> getElevatorControllerList() {
        return elevatorControllerList;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
