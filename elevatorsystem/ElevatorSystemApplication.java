package elevatorsystem;

import java.util.ArrayList;
import java.util.List;

import elevatorsystem.model.Building;
import elevatorsystem.model.Direction;
import elevatorsystem.model.ElevatorController;
import elevatorsystem.model.ElevatorSystem;
import elevatorsystem.model.Floor;
import elevatorsystem.strategy.LookControlStrategy;
import elevatorsystem.strategy.OddEvenSelectionStrategy;

public class ElevatorSystemApplication {

    public static void main(String[] args) {

        List<Floor> floorList = new ArrayList<>();
        Building building = new Building("Mansion", floorList);

        // adding floors
        int totalFloors = 50;

        for (int i = 0; i < totalFloors; i++) {
            building.addFloor(new Floor(i));
        }
        System.out.println("Total Number of floors in the " + building.getName() + " are " + totalFloors);

        // adding elevators
        ElevatorSystem elevatorSystem = ElevatorSystem.INSTANCE;

        // will add later
        elevatorSystem.setElevationControlStrategy(new LookControlStrategy());
        elevatorSystem.setElevationSelectionStrategy(new OddEvenSelectionStrategy());

        int totalElevators = 4;

        for (int i = 1; i <= totalElevators; i++) {
            elevatorSystem.addElevator(new ElevatorController(i));
        }
        System.out.println("Total Number of elevators in the " + building.getName() + " are " + totalElevators);

        // will add requests of user
        // loop me user -> request -> pressButton(1, up)
        System.out.println("Lift running!");

        System.out.println("Please press up or down arrow key");
        System.out.println("Person at floor 1 presses UP Button");
        for (Floor floor : floorList) {
            if (floor.getFloorId() == 1) {
                floor.pressButton(Direction.Up);
            }
        }
        System.out.println("Person at floor 3 presses UP Button");
        for (Floor floor : floorList) {
            if (floor.getFloorId() == 3) {
                floor.pressButton(Direction.Up);
            }
        }
        System.out.println("Person presses 10 in elevator 2");
        for (ElevatorController elevatorController : ElevatorSystem.INSTANCE.getElevatorControllerList()) {
            if (elevatorController.getId() == 2)
                elevatorController.acceptNewInternalRequest(10);

        }
        System.out.println("Person at floor 5 presses UP Button");
        for (Floor floor : floorList) {
            if (floor.getFloorId() == 5) {
                floor.pressButton(Direction.Up);
            }
        }
        System.out.println("Person at floor 7 presses Down Button");
        for (Floor floor : floorList) {
            if (floor.getFloorId() == 7) {
                floor.pressButton(Direction.Down);
            }
        }
    }
}
