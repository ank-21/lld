package elevatorsystem.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import elevatorsystem.strategy.ElevatorControlStrategy;

public class ElevatorController {
    private int id;

    private Elevator elevator;

    PriorityQueue<Integer> upMinPQ;
    PriorityQueue<Integer> downMaxPQ;
    Queue<Integer> pendingUpOperations;
    Queue<Integer> pendingDownOperations;

    public ElevatorController(int id){
        this.id = id;
        elevator = new Elevator(id);

        upMinPQ = new PriorityQueue<>();
        downMaxPQ = new PriorityQueue<>(Collections.reverseOrder());
        pendingUpOperations = new LinkedList<>();
        pendingDownOperations = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void acceptNewInternalRequest(int destFloor){
        if(destFloor > elevator.currentFloor)
            elevator.move(destFloor, Direction.Up);
        else
            elevator.move(destFloor, Direction.Down);
    }

    public void acceptNewExternalRequest(int floor, Direction direction){
        //User wants to go in the called direction from the called floor
        //update the data structure to accept new request from both iDispatcher and eDispatcher

        if(elevator.currentDir == Direction.Up || elevator.currentDir == Direction.None){

            if(direction == Direction.Up){
                if(floor >= elevator.currentFloor)
                    upMinPQ.offer(floor);
                else
                    pendingUpOperations.offer(floor);
            }else if(direction == Direction.Down){
                pendingDownOperations.offer(floor);
            }
        }else if(elevator.currentDir == Direction.Down){
            if(direction == Direction.Down){
                if(floor <= elevator.currentFloor)
                    downMaxPQ.offer(floor);
                else
                    pendingDownOperations.offer(floor);
            }else if(direction == Direction.Up){
                pendingUpOperations.offer(floor);
            }
        }
        //calls control car to control the car to start the elevator car if it is not running
        if(elevator.currentDir == Direction.None){
            elevator.currentDir = Direction.Up;
            controlCar();
        }
    }

    public void controlCar(){
        ElevatorControlStrategy elevatorControlStrategy = ElevatorSystem.INSTANCE.getElevatorControlStrategy();
        //should be added in the strategy itself
        while(!upMinPQ.isEmpty() || !downMaxPQ.isEmpty() || !pendingDownOperations.isEmpty() || !pendingUpOperations.isEmpty()){

            if(elevator.currentDir == Direction.Up){
                elevatorControlStrategy.runOperations(elevator, Direction.Up, upMinPQ);
                //since all the floors are visited in this movement so changing the direction
                elevator.currentDir = Direction.Down;
                
                //Adding floors to priority queue if some requests are present in the pending queue
                while(!pendingUpOperations.isEmpty()){
                    upMinPQ.offer(pendingUpOperations.poll());
                }
            } else if(elevator.currentDir == Direction.Down){
                elevatorControlStrategy.runOperations(elevator, Direction.Down, downMaxPQ);
                elevator.currentDir = Direction.Up;
    
                while(!pendingDownOperations.isEmpty()){
                    downMaxPQ.offer(pendingDownOperations.poll());
                }
            }
        }
        elevator.currentDir = Direction.None;
    }
}
