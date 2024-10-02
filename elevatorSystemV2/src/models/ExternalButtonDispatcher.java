package models;

import controllers.ElevatorController;
import enums.Direction;

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
        int elevatorId = 1;
        System.out.println("The selected elevator is " + elevatorId);

        // Calling the accept External request function from the respective controller
        for(ElevatorController controller : ElevatorSystem.getInstance().getElevatorControllerList()){
            if(controller.getId() == elevatorId){
                controller.acceptExternalRequest(floorNo, dir);
            }
        }
    }
}
