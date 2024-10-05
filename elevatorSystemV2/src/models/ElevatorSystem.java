package models;

import controllers.ElevatorController;
import strategy.ElevatorControlStrategy;
import strategy.ElevatorSelectionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ElevatorSystem {
    private List<ElevatorController> elevatorControllerList = new ArrayList<ElevatorController>();
    public List<Floor> floors = new ArrayList<>();

    public ElevatorControlStrategy elevatorControlStrategy;
    public ElevatorSelectionStrategy elevatorSelectionStrategy;

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

    public ElevatorControlStrategy getElevatorControlStrategy() {
        return elevatorControlStrategy;
    }

    public void setElevatorControlStrategy(ElevatorControlStrategy elevatorControlStrategy) {
        this.elevatorControlStrategy = elevatorControlStrategy;
    }

    public ElevatorSelectionStrategy getElevatorSelectionStrategy() {
        return elevatorSelectionStrategy;
    }

    public void setElevatorSelectionStrategy(ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }

    public void processRequests() {
        for (ElevatorController controller : elevatorControllerList) {
            PriorityQueue<Integer> operations = controller.getOperations();
            if (operations != null && !operations.isEmpty()) {
                System.out.println("Processing requests for elevator " + controller.getElevator().getId() +
                        " in direction " + controller.getElevator().getDir());
                elevatorControlStrategy.runOperations(controller.getElevator(), controller.getElevator().getDir(), operations);
            }
        }
    }
}
