package elevatorsystem.model;

public class Elevator {
    int id;
    Door door;
    Display display;
    Direction currentDir;
    int currentFloor;
    public Status status;

    public Elevator(int id) {
        this.id = id;
        door = new Door();
        display = new Display();
        status = Status.Idle;
        currentFloor = 0;
        currentDir = Direction.None;
    }

    public void move(int destFloor, Direction direction) {
        status = Status.Running;
        if (direction == Direction.Up) {
            moveElevator(currentFloor, destFloor, direction);
        } else if(direction == Direction.Down){
            moveElevator(currentFloor, destFloor, Direction.Up);
            moveElevator(destFloor, 0, direction);
        }
    }

    public void setDisplay(int floor, Direction direction) {
        this.display.setCurrentFloor(floor);
        this.display.setDirection(direction);
    }

    public void moveElevator(int startFloor, int endFloor, Direction direction){
        for (int i = startFloor; i < endFloor; i++) {
            this.currentFloor = i;
            this.currentDir = direction;
            setDisplay(currentFloor, currentDir);
            display.showDisplay();
        }
        display.showDestinationDisplay(endFloor);
        door.open(id);
        door.close(id);
    }
}
