package strategy;

import enums.Direction;
import models.Elevator;

import java.util.PriorityQueue;

public interface ElevatorControlStrategy {
    void runOperations(Elevator elevator, Direction direction, PriorityQueue<Integer> operations);
}
