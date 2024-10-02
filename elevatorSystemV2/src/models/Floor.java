package models;

import enums.Direction;

public class Floor {
    int floorNo;
    ExternalButton externalButton;

    public Floor(int floorNo, ExternalButton externalButton) {
        this.floorNo = floorNo;
        this.externalButton = externalButton;
    }

    public void pressButton(Direction dir){
        externalButton.pressButton(floorNo, dir);
    }

    public int getFloorNo() {
        return floorNo;
    }
}
