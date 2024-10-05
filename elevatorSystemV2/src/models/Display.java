package models;

import enums.Direction;

public class Display {
    private int currentFloorNo;
    private Direction dir;

    public int getCurrentFloorNo() {
        return currentFloorNo;
    }

    public void setCurrentFloorNo(int currentFloorNo) {
        this.currentFloorNo = currentFloorNo;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void showDisplay(){
        System.out.println("Current floor is " + currentFloorNo);
        System.out.println("Current Direction is " + dir);
    }
}
