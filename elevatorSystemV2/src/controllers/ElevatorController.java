package controllers;

import enums.Direction;
import enums.Status;
import models.Elevator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ElevatorController {
    private Elevator elevator;

    PriorityQueue<Integer> upMinPQ;
    PriorityQueue<Integer> downMaxPQ;
    Queue<Integer> pendingUpOperations;
    Queue<Integer> pendingDownOperations;

    public ElevatorController(int id, int maxCapacity) {
        this.elevator = new Elevator(id, maxCapacity);

        upMinPQ = new PriorityQueue<>();
        downMaxPQ = new PriorityQueue<>(Collections.reverseOrder());
        pendingUpOperations = new LinkedList<>();
        pendingDownOperations = new LinkedList<>();
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void acceptExternalRequest(int floorNo, Direction dir){
        elevator.setStatus(Status.MOVING);
        if(elevator.getDir() == Direction.NONE) {
            if(dir == Direction.UP){
                if(elevator.getCurrentFloorNo() <= floorNo){
                    elevator.setDir(Direction.UP);
                    upMinPQ.offer(floorNo);
                }else{
                    downMaxPQ.offer(floorNo);
                    elevator.setDir(Direction.DOWN);
                }
            } else if (dir == Direction.DOWN) {
                if(elevator.getCurrentFloorNo() <= floorNo){
                    elevator.setDir(Direction.UP);
                    upMinPQ.offer(floorNo);
                }else{
                    downMaxPQ.offer(floorNo);
                    elevator.setDir(Direction.DOWN);
                }
            }
        }else if (elevator.getDir() == Direction.UP) {
            if (dir == Direction.UP){
                if(elevator.getCurrentFloorNo() <= floorNo){
                    upMinPQ.offer(floorNo);
                }else{
                    pendingUpOperations.add(floorNo);
                }
            }else{
                pendingDownOperations.add(floorNo);
            }
        }

        else if (elevator.getDir() == Direction.DOWN){
            if (dir == Direction.DOWN){
                if(elevator.getCurrentFloorNo() <= floorNo){
                    downMaxPQ.offer(floorNo);
                }else{
                    pendingDownOperations.add(floorNo);
                }
            }else{
                pendingUpOperations.add(floorNo);
            }
        }
    }

    public void acceptInternalRequest(int floorNo){
        if(elevator.getDir() == Direction.NONE){
            System.out.println("Wrong input, please try again!");
            return;
        }

        if(floorNo > elevator.getCurrentFloorNo()){
            if(elevator.getDir() == Direction.UP)
                upMinPQ.offer(floorNo);
            else {
                if (!downMaxPQ.isEmpty())
                    System.out.println("Sorry the lift can't go to the floor " + floorNo + " in this motion.");
                else {
                    elevator.setDir(Direction.UP);
                    upMinPQ.offer(floorNo);
                }
            }
        }else{
            if(elevator.getDir() == Direction.DOWN)
                downMaxPQ.offer(floorNo);
            else{
                // Need to check if some upward direction is still left and requesting for lower floor internally
                if(!upMinPQ.isEmpty()){
                    System.out.println("Sorry the lift can't go to the floor " + floorNo + " in this motion.");
                }else{
                    elevator.setDir(Direction.DOWN);
                    downMaxPQ.offer(floorNo);
                }
            }
        }
    }

    public PriorityQueue<Integer> getOperations(){
        Direction elevatorDir = elevator.getDir();
        if(elevatorDir == Direction.UP){
            if(!upMinPQ.isEmpty()){
                // Still needs to go up
                return upMinPQ;
            }else if(!pendingDownOperations.isEmpty() || !downMaxPQ.isEmpty()){
                elevator.setDir(Direction.DOWN);
                while (!pendingDownOperations.isEmpty()){
                    downMaxPQ.offer(pendingDownOperations.poll());
                }
                return downMaxPQ;
            }
        }else if(elevatorDir == Direction.DOWN){
            if(!downMaxPQ.isEmpty()){
                return downMaxPQ;
            }else if(!pendingUpOperations.isEmpty() || !upMinPQ.isEmpty()){
                elevator.setDir(Direction.UP);
                while (!pendingUpOperations.isEmpty()){
                    upMinPQ.offer(pendingUpOperations.poll());
                }
                return upMinPQ;
            }
        }
        return null;
    }
}
