package models;

import enums.Direction;

public class ExternalButton {
    private ExternalButtonDispatcher externalButtonDispatcher;

    public ExternalButton() {
        this.externalButtonDispatcher = ExternalButtonDispatcher.getInstance();
    }

    public void pressButton(int floorNo, Direction dir){
        System.out.println("Pressed external button to go to " + floorNo + "th floor and wants to go in " + dir + " direction");
        externalButtonDispatcher.acceptNewRequest(floorNo, dir);
    }
}
