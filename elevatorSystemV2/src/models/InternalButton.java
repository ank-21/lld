package models;

public class InternalButton {
    private InternalButtonDispatcher internalButtonDispatcher;
    private Elevator elevator;

    public InternalButton(Elevator elevator) {
        this.internalButtonDispatcher = InternalButtonDispatcher.getInstance();
        this.elevator = elevator;
    }

    public void pressButton(int floorNo){
        System.out.println("Pressed internal button to go to " + floorNo + "th floor");
        internalButtonDispatcher.acceptNewRequest(floorNo, elevator);
    }
}
