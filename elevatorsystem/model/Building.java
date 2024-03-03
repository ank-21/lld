package elevatorsystem.model;

import java.util.List;

public class Building {
    
    String name;

    List<Floor> floorList;

    public Building(String name, List<Floor> floors){
        this.name = name;
        this.floorList = floors;
    }

    public void addFloor(Floor newFloor){
        floorList.add(newFloor);
    }

    public void removeFloor(Floor removeFloor){
        floorList.remove(removeFloor);
    }

    public List<Floor> getAllFloors(){
        return floorList;
    }

    public String getName() {
        return name;
    }

}
