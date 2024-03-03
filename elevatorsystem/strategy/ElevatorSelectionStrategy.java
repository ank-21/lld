package elevatorsystem.strategy;

import java.util.List;

import elevatorsystem.model.Direction;
import elevatorsystem.model.ElevatorController;
import elevatorsystem.model.ElevatorSystem;

public abstract class ElevatorSelectionStrategy {
    public List<ElevatorController> elevatorControllerList = ElevatorSystem.INSTANCE.getElevatorControllerList();

    public abstract int selectElevator(int floor, Direction dir);
}
