package strategy;

import enums.Direction;
import models.Elevator;

import java.util.PriorityQueue;

public class LookControlStrategy implements ElevatorControlStrategy{
    @Override
    public void runOperations(Elevator elevator, Direction direction, PriorityQueue<Integer> operations) {
        if (operations == null || operations.isEmpty()) {
            System.out.println("No operations to process for elevator " + elevator.getId());
            return;
        }

        while (!operations.isEmpty()){
            int destFloor = operations.poll();
            elevator.move(destFloor, direction);
        }
    }
}
