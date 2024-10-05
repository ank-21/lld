package models;

import controllers.ElevatorController;

public class InternalButtonDispatcher {
    private InternalButtonDispatcher(){}
    private static volatile InternalButtonDispatcher instance = null;

    public static InternalButtonDispatcher getInstance(){
        if(instance == null){
            synchronized (InternalButtonDispatcher.class){
                if(instance == null){
                    return instance = new InternalButtonDispatcher();
                }
            }
        }
        return instance;
    }

    public void acceptNewRequest(int floorNo, Elevator elevator){
        for(ElevatorController controller : ElevatorSystem.getInstance().getElevatorControllerList()){
            if (controller.getElevator().getId() == elevator.getId()){
                controller.acceptInternalRequest(floorNo);
            }
        }
    }
}
