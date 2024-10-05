package models;

import controllers.ElevatorController;

public class InternalButtonDispatcher {
    public InternalButtonDispatcher(){}

    public void acceptNewRequest(int floorNo, Elevator elevator){
        for(ElevatorController controller : ElevatorSystem.getInstance().getElevatorControllerList()){
            if (controller.getElevator().getId() == elevator.getId()){
                controller.acceptInternalRequest(floorNo);
            }
        }
    }
}
