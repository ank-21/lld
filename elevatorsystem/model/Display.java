package elevatorsystem.model;

public class Display {
    int currentFloor;
    Direction dir;

    public Direction getDir() {
        return dir;
    }

    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void showDisplay() {
        String direction = this.dir == Direction.Up ? "upward" : "downward";
        System.out.println("Lift is currently at " + this.currentFloor + " floor and is going in " + direction + " direction!");
    }

    public void showDestinationDisplay(int destFloor){
        System.out.println("Lift reached the pressed floor "+ destFloor);
    }
}
