package models;

import enums.Direction;

public class ExternalButton {
    private ExternalButtonDispatcher externalButtonDispatcher;
    public void pressButton(int floorNo, Direction dir){
        System.out.println("Pressed external button to go to " + floorNo + "th floor which is in " + dir + " direction");
        externalButtonDispatcher.acceptNewRequest(floorNo, dir);
    }
}
