package main;

import controllers.ElevatorController;
import enums.Direction;
import models.Elevator;
import models.ElevatorSystem;
import models.ExternalButton;
import models.Floor;
import strategy.LookControlStrategy;
import strategy.OddEvenSelectionStrategy;

import java.util.Scanner;

public class ElevatorSystemApplication {
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = ElevatorSystem.getInstance();

        // Set the control strategy
        elevatorSystem.setElevatorControlStrategy(new LookControlStrategy());
        elevatorSystem.setElevatorSelectionStrategy(new OddEvenSelectionStrategy());

        // Create 3 elevators
        for (int i = 0; i < 3; i++) {
            ElevatorController controller = new ElevatorController(i, 1200);
            elevatorSystem.getElevatorControllerList().add(controller);
        }

        // Create 10 floors
        for (int i = 0; i < 10; i++) {
            Floor floor = new Floor(i, new ExternalButton());
            elevatorSystem.getFloors().add(floor);
        }

        // Set up a timer to process elevator requests periodically
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                elevatorSystem.processRequests();
//            }
//        }, 0, 2000); // Run every 2 seconds

        Thread processingThread = new Thread(() -> {
           while (true){
               elevatorSystem.processRequests();
               try {
                   Thread.sleep(500);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
        });

        processingThread.start();

        // Simulate elevator requests
        simulateElevatorRequests(elevatorSystem);
    }
    private static void simulateElevatorRequests(ElevatorSystem elevatorSystem) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nEnter command (e.g., 'external 3 up', 'internal 1 5', or 'quit'): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("quit")) {
                    break;
                }

                String[] parts = input.split(" ");
                if (parts[0].equalsIgnoreCase("external") && parts.length == 3) {
                    int floorNo = Integer.parseInt(parts[1]);
                    if( floorNo >= 0 && floorNo < elevatorSystem.getFloors().size()){
                        Direction direction = Direction.valueOf(parts[2].toUpperCase());
                        elevatorSystem.getFloors().get(floorNo).pressButton(direction);
                    }else{
                        System.out.println("Wrong value enetered, please try again");
                    }
                } else if (parts[0].equalsIgnoreCase("internal") && parts.length == 3) {
                    int elevatorId = Integer.parseInt(parts[1]);
                    int destinationFloor = Integer.parseInt(parts[2]);

                    if(elevatorId >= 0 && elevatorId < ElevatorSystem.getInstance().getElevatorControllerList().size()){
                        // worked because of index
                        ElevatorController controller = elevatorSystem.getElevatorControllerList().get(elevatorId);
                        Elevator elevator = controller.getElevator();
                        elevator.getInternalButton().pressButton(destinationFloor, elevator);
                    }else {
                        System.out.println("Wrong value enetered, please try again");
                    }
                } else {
                    System.out.println("Invalid command. Try again.");
                }
            } catch (IllegalArgumentException e){
                System.out.println("Please enter a valid argument");
            } catch (IndexOutOfBoundsException e){
                System.out.println("Please enter number in the range");
            }
        }
        scanner.close();
    }
}
