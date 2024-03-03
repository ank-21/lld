package elevatorsystem.model;

import java.util.ArrayList;
import java.util.List;

import elevatorsystem.strategy.ElevatorControlStrategy;
import elevatorsystem.strategy.ElevatorSelectionStrategy;

public class ElevatorSystem {
    private List<ElevatorController> elevatorControllerList = new ArrayList<ElevatorController>();

    private static ElevatorSelectionStrategy elevatorSelectionStrategy;
    private static ElevatorControlStrategy elevatorControlStrategy;

    //singelton class
    public static ElevatorSystem INSTANCE = new ElevatorSystem();

    private ElevatorSystem(){
        
    }

    public void addElevator(ElevatorController e){
        elevatorControllerList.add(e);
    }

    public void removeElevator(ElevatorController e){
        elevatorControllerList.remove(e);
    }

    public void setElevationControlStrategy(ElevatorControlStrategy elevatorControlStrategy){
        ElevatorSystem.elevatorControlStrategy = elevatorControlStrategy;
    }

    public void setElevationSelectionStrategy(ElevatorSelectionStrategy elevatorSelectionStrategy){
        ElevatorSystem.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }

    public ElevatorSelectionStrategy getElevatorSelectionStrategy() {
        return elevatorSelectionStrategy;
    }

    public ElevatorControlStrategy getElevatorControlStrategy() {
        return elevatorControlStrategy;
    }

    public List<ElevatorController> getElevatorControllerList() {
        return elevatorControllerList;
    }
}
