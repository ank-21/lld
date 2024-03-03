package elevatorsystem.strategy;

import java.util.Random;

import elevatorsystem.model.Direction;
import elevatorsystem.model.ElevatorController;
import elevatorsystem.model.Status;

public class OddEvenSelectionStrategy extends ElevatorSelectionStrategy {

    @Override
    public int selectElevator(int floor, Direction dir) {
        for (ElevatorController elevatorController : elevatorControllerList) {
            if(elevatorController.getElevator().status == Status.Idle){
                if (elevatorController.getId() % 2 == 1 && floor % 2 == 1) {
                    return elevatorController.getId();
                }else if(elevatorController.getId() % 2 == 0 && floor % 2 == 0){
                    return elevatorController.getId();
                }
            }
        }
        int maxSize = elevatorControllerList.size();
        Random rand = new Random();
        int randomElevator;
        
        if(floor % 2 == 0){
            do {
                randomElevator = rand.nextInt(maxSize + 1);
            } while (randomElevator % 2 != 0);
        }else{
            do {
                randomElevator = rand.nextInt(maxSize + 1);
            } while (randomElevator % 2 == 0);
        }
        return randomElevator;
    }
}
