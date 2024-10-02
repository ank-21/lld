package models;

public class Door {
    public void open(int id){
        System.out.println("The door is opening for elevator " + id);
    }

    public void close(int id){
        System.out.println("The door is closing for elevator " + id);
    }
}
