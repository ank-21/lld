package strategy;

import controllers.ElevatorController;
import enums.Direction;
import models.Elevator;

import java.util.List;

public interface ElevatorSelectionStrategy {
    public Elevator selectElevator(int floorNo, Direction dir);
}
