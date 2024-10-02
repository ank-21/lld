package models;

import enums.Direction;
import enums.Status;

public class Elevator {
    private Direction dir;
    private int id;
    private int currentFloorNo;
    private Status status;
    private Door door;
    private int maxCapacity;
    private Display displayObj;

    public Elevator(int id, int maxCapacity) {
        this.dir = Direction.NONE;
        this.id = id;
        this.currentFloorNo = 0;
        this.status = Status.IDLE;
        this.door = new Door();
        this.maxCapacity = maxCapacity;
        this.displayObj = new Display();
    }

    public boolean move(int destinationFloor, Direction dir){
        int startFloor = currentFloorNo;
        if(dir == Direction.UP){
            for(int i = startFloor; i <= destinationFloor; i++){
                this.currentFloorNo = i;
                setDisplay();
                showDisplay();
                if(i == destinationFloor){
                    door.open(id);
                    door.close(id);
                    return true;
                }
            }
        }
        if(dir == Direction.DOWN){
            for(int i = startFloor; i >= destinationFloor; i--){
                this.currentFloorNo = i;
                setDisplay();
                showDisplay();
                if(i == destinationFloor){
                    door.open(id);
                    door.close(id);
                    return true;
                }
            }
        }
        return false;
    }

    public void setDisplay(){
        this.displayObj.setDir(dir);
        this.displayObj.setCurrentFloorNo(currentFloorNo);
    }

    public void showDisplay(){
        this.displayObj.showDisplay();
    }


}
