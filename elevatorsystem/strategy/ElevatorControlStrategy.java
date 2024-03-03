package elevatorsystem.strategy;

import java.util.PriorityQueue;

import elevatorsystem.model.Direction;
import elevatorsystem.model.Elevator;

public abstract class ElevatorControlStrategy {

    public abstract void runOperations(Elevator elevator, Direction direction,  PriorityQueue<Integer> operations);
}
