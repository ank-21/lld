package elevatorsystem.strategy;

import java.util.PriorityQueue;

import elevatorsystem.model.Elevator;
import elevatorsystem.model.Direction;

public class LookControlStrategy extends ElevatorControlStrategy {

    @Override
    public void runOperations(Elevator elevator, Direction direction, PriorityQueue<Integer> operations){
        while(!operations.isEmpty()){
            int destFloor = operations.poll();
            elevator.move(destFloor, direction);
        }   
    }
}
