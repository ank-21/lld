import product.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleRentalSystem {
    //Singelton Class
    public static VehicleRentalSystem INSTANCE = new VehicleRentalSystem();
    List<User> users = new ArrayList<>();
    List<Store> stores = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void addStore(int storeId, String storeName, String address, String city, String state, int pincode) {
        Location location = new Location(address, city, pincode, state);
        Store store = new Store(storeId, storeName, location);
        List<VehicleManager> vehicleManagerList = new ArrayList<>();

        addVehicleManagers(VehicleType.Bike.toString(), storeName, vehicleManagerList);
        addVehicleManagers(VehicleType.Car.toString(), storeName, vehicleManagerList);
        addVehicleManagers(VehicleType.Scooty.toString(), storeName, vehicleManagerList);

        store.setVehicleManagerList(vehicleManagerList);
        stores.add(store);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Store> getStores() {
        return stores;
    }


    public User findUser(String userId) {
        for (User user : users) {
            if (userId.equals(user.getUserId())) return user;
        }
        return null;
    }

    public Store validateCityAndGetStore(String city) {
        for (Store store : stores) {
            if (city.equals(store.location.city)) return store;
        }
        return null;
    }

    private void addVehicleManagers(String vehicle, String storeName, List<VehicleManager> vehicleManagerList) {
        String input;
        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to add " + vehicle + " in your store " + storeName + " - Y/N - ");
        while (true) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                switch (vehicle) {
                    case "Bike" -> vehicleManagerList.add(new BikeManager());
                    case "Car" -> vehicleManagerList.add(new CarManager());
                    case "Scooty" -> vehicleManagerList.add(new ScootyManager());
                }
                break;
            } else if (input.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Please enter the correct input!");
            }
        }
    }
}
