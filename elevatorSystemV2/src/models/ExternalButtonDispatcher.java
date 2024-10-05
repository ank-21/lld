package models;

import controllers.ElevatorController;
import enums.Direction;
import strategy.ElevatorSelectionStrategy;

public class ExternalButtonDispatcher {

    private ExternalButtonDispatcher(){}
    private static volatile ExternalButtonDispatcher instance = null;

    public static ExternalButtonDispatcher getInstance(){
        if(instance == null){
            synchronized (ExternalButtonDispatcher.class){
                if(instance == null){
                    return instance = new ExternalButtonDispatcher();
                }
            }
        }
        return instance;
    }

    public void acceptNewRequest(int floorNo, Direction dir){
        // Implement various selection strategies to select the elevator
        ElevatorSelectionStrategy elevatorSelectionStrategy = ElevatorSystem.getInstance().getElevatorSelectionStrategy();
        Elevator elevator = elevatorSelectionStrategy.selectElevator(floorNo, dir);
        System.out.println("The selected elevator is " + elevator.getId());

        // Calling the accept External request function from the respective controller
        for(ElevatorController controller : ElevatorSystem.getInstance().getElevatorControllerList()){
            if(controller.getElevator().getId() == elevator.getId()){
                controller.acceptExternalRequest(floorNo, dir);
            }
        }
    }
}
