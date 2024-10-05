package models;

public class Door {
    public void open(int id, int floorNo){
        System.out.println("The door is opening for elevator " + id + " at " + floorNo + " floor.");
    }

    public void close(int id, int floorNo){
        System.out.println("The door is closing for elevator " + id + " at " + floorNo + " floor.");
    }
}
