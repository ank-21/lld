package elevatorsystem.model;

import elevatorsystem.dispatcher.ExternalDispatcher;

public class ExternalButton {
    private ExternalDispatcher eDispatcher = ExternalDispatcher.INSTANCE;

    private Direction direction;

    public void pressButton(int floor, Direction dir) {
        direction = dir;
        System.out.println("Pressed " + direction + " direction from floor " + floor);
        eDispatcher.submitRequest(floor, dir);
    }
}
