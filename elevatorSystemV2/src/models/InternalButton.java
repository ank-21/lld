package models;

public class InternalButton {
    private InternalButtonDispatcher internalButtonDispatcher;

    public InternalButton() {
        this.internalButtonDispatcher = new InternalButtonDispatcher();
    }

    public void pressButton(int floorNo, Elevator elevator){
        System.out.println("Pressed internal button to go to " + floorNo + "th floor");
        internalButtonDispatcher.acceptNewRequest(floorNo, elevator);
    }
}
