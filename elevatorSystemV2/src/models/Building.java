package models;

import java.util.List;

public class Building {
    private List<Floor> floors;

    public Building(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Floor> getAllFloors() {
        return floors;
    }

    public void addFloor(Floor newFloor){
        floors.add(newFloor);
    }
    public void removeFloor(Floor removedFloor){
        floors.remove(removedFloor);
    }
}
