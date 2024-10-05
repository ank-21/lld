package strategy;

import controllers.ElevatorController;
import enums.Direction;
import models.Elevator;
import models.ElevatorSystem;

import java.util.List;

public class OddEvenSelectionStrategy implements ElevatorSelectionStrategy{
    @Override
    public Elevator selectElevator(int floorNo, Direction dir) {
        for(ElevatorController elevatorController : ElevatorSystem.getInstance().getElevatorControllerList()){
            // old elevator for odd floors and even elevators for even floors
            // select elevator which is moving in same direction which is requested or IDLE elevator
            if(floorNo % 2 == elevatorController.getElevator().getId() % 2)
            {
                int currFloor = elevatorController.getElevator().getCurrentFloorNo();
                Direction currDir = elevatorController.getElevator().getDir();

                if(floorNo > currFloor && currDir == Direction.UP)
                    return elevatorController.getElevator();
                else if(floorNo < currFloor && currDir == Direction.DOWN)
                    return elevatorController.getElevator();
                else if(currDir == Direction.NONE)
                    return elevatorController.getElevator();
            }
        }
        return null;
    }
}
