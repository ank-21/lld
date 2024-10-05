package models;

import enums.Direction;
import enums.Status;

import static java.lang.Thread.sleep;

public class Elevator {
    private Direction dir;
    private int id;
    private int currentFloorNo;
    private Status status;
    private Door door;
    private int maxCapacity;
    private Display displayObj;
    private InternalButton internalButton;

    public Elevator(int id, int maxCapacity) {
        this.dir = Direction.NONE;
        this.id = id;
        this.currentFloorNo = 0;
        this.status = Status.IDLE;
        this.door = new Door();
        this.maxCapacity = maxCapacity;
        this.displayObj = new Display();
        this.internalButton = new InternalButton();
    }

    public void move(int destinationFloor, Direction dir){
        int startFloor = currentFloorNo;
        if(dir == Direction.UP || dir == Direction.NONE){
            for(int i = startFloor; i <= destinationFloor; i++){
                this.currentFloorNo = i;
                setDisplay();
                showDisplay();
                if(i == destinationFloor){
                    arrive(destinationFloor);
                    return;
                }
                // Simulate movement time between floors
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(dir == Direction.DOWN){
            for(int i = startFloor; i >= destinationFloor; i--){
                this.currentFloorNo = i;
                setDisplay();
                showDisplay();
                if(i == destinationFloor){
                    arrive(destinationFloor);
                    return;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void arrive(int destinationFloor) {
        this.status = Status.IDLE;
        door.open(id, destinationFloor);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        door.close(id, destinationFloor);
    }

    public void setDisplay(){
        this.displayObj.setDir(dir);
        this.displayObj.setCurrentFloorNo(currentFloorNo);
    }

    public void showDisplay(){
        this.displayObj.showDisplay();
    }

    public Direction getDir() {
        return dir;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloorNo() {
        return currentFloorNo;
    }

    public Status getStatus() {
        return status;
    }

    public Door getDoor() {
        return door;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Display getDisplayObj() {
        return displayObj;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public InternalButton getInternalButton() {
        return internalButton;
    }
}
