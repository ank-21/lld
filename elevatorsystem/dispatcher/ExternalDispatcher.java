package elevatorsystem.dispatcher;

import elevatorsystem.model.Direction;
import elevatorsystem.model.ElevatorController;
import elevatorsystem.model.ElevatorSystem;

public class ExternalDispatcher {
    public static ExternalDispatcher INSTANCE = new ExternalDispatcher();

    private ExternalDispatcher()
    {

    }

    public void submitRequest(int floor, Direction dir){
        //Implemented various strategies to select the elevator
        int elevatorId = ElevatorSystem.INSTANCE.getElevatorSelectionStrategy().selectElevator(floor, dir);
        System.out.println("Selected Elevator No. is " + elevatorId);

        //Selecting the controller which will call the elevator car

        for(ElevatorController eController: ElevatorSystem.INSTANCE.getElevatorControllerList()){
            if(eController.getId() == elevatorId){
                eController.acceptNewExternalRequest(floor, dir);
            }
        }

    }
}
