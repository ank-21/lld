package elevatorsystem.model;

public class Floor {
    int floorId;

    ExternalButton externalButton;
    
    public Floor(int id) {
        this.floorId = id;
        externalButton = new ExternalButton();
    }

    public void pressButton(Direction dir) {
        externalButton.pressButton(floorId, dir);
    }

    public int getFloorId() {
        return floorId;
    }
}
