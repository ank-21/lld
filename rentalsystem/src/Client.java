import product.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){

        VehicleRentalSystem vehicleRentalSystem = VehicleRentalSystem.INSTANCE;
        System.out.println("-------------- Vehicle Rental System -----------------");

        //Adding Stores to the system
        addStore(1, vehicleRentalSystem);
        //addStore(2, vehicleRentalSystem);
        //addStore(3, vehicleRentalSystem);

        //Adding Users to the system
        addUser(vehicleRentalSystem);
        //addUser(vehicleRentalSystem);

        System.out.println("-------------- Reservation of vehicle starting -----------------");

        reserveVehicle(vehicleRentalSystem);
    }

    private static void reserveVehicle(VehicleRentalSystem vehicleRentalSystem){
        Scanner sc = new Scanner(System.in);

        User user = null;
        while(user == null){

            System.out.print("Please enter your user Id - ");
            String userId = sc.nextLine();
            user = vehicleRentalSystem.findUser(userId);
        }

        List<Store> storeList = vehicleRentalSystem.getStores();

        System.out.println("Please choose cities among the following city for booking");
        for(Store store: storeList)
            System.out.print(store.location.city + "  ");

        Store store = null;
        while(store == null){

            System.out.print("\nPlease enter vehicle booking city - ");
            String bookingCity = sc.nextLine();
            store = vehicleRentalSystem.validateCityAndGetStore(bookingCity);
        }

        Vehicle vehicle = getVehicleChoice(store);
        assert vehicle != null;

        System.out.print("From which Date you want to book your " + vehicle.getVehicleType().toString() + " - (format: dd-MM-yyyy): ");
        String bookingFrom = sc.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date bookingFromDate = null, bookingTillDate = null;

        System.out.print("Till which Date you want to book your " + vehicle.getVehicleType().toString() + " - (format: dd-MM-yyyy): ");
        String bookingTill = sc.nextLine();

        try {
            bookingFromDate = dateFormat.parse(bookingFrom);
            bookingTillDate = dateFormat.parse(bookingTill);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in the format dd-MM-yyyy. ");
        }

        System.out.print("For which city you want to book your " + vehicle.getVehicleType().toString() + " - ");
        String bookingCity = sc.nextLine();

        Reservation reservation = bookReservation(bookingFromDate, bookingTillDate, bookingCity, user, vehicle);

        store.addReservationList(reservation);
        assert bookingTillDate != null;
        long timeDifference = bookingTillDate.getTime() - bookingFromDate.getTime();
        long totalDays = timeDifference / (1000 * 60 * 60 * 24);
        double totalCost = totalDays * vehicle.getDailyRentalCost();

        Bill bill = createBillAndPay(totalDays, totalCost, reservation);

        System.out.println("You have successfully made your booking with " + store.getName());
        System.out.println("Your booking details are : ");
        System.out.println("Booking Id : " + bill.reservation.getReservationId());
        System.out.println("Booking Date : " + bill.reservation.getBookingDate());
        System.out.println("Booking Start Date : " + bill.reservation.getBookingFrom());
        System.out.println("Booking End Date : " + bill.reservation.getBookingTill());
        System.out.println("Booking City : " + bill.reservation.getBookingCity());
        System.out.println("Booking Vehicle : " + bill.reservation.getVehicle().getVehicleType());
        System.out.println("Booking Days : " + totalDays);
        System.out.println("Booking Cost : " + totalCost);
        System.out.println("Payment Status : " + "Paid");
        System.out.println("Enjoy your ride!");

    }

    public static void addUser(VehicleRentalSystem vehicleRentalSystem){
        System.out.println("\n-------------- User entry -----------------");

        User user = new User();

        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name - ");
        String name = sc.nextLine();
        user.setName(name);

        System.out.print("Please enter your driving license - ");
        int drivingLicense = sc.nextInt();
        // Consume the newline character left in the input stream
        sc.nextLine();
        user.setDrivingLicense(drivingLicense);

        while(true){

            System.out.print("Please create your unique id - ");
            String userId = sc.nextLine();
            if(vehicleRentalSystem.findUser(userId) == null) {
                user.setUserId(userId);
                break;
            }
        }

        vehicleRentalSystem.addUser(user);
    }

     public static void addStore(int storeId, VehicleRentalSystem vehicleRentalSystem){
        System.out.println("\n-------------- Store entry -----------------");

        Scanner sc = new Scanner(System.in);

        System.out.println("Hey Store " + storeId + ", Please enter the following Details!");

        System.out.print("Please enter the store name - ");
        String storeName = sc.nextLine();

        System.out.print("Please enter the address - ");
        String address = sc.nextLine();

        System.out.print("Please enter the city - ");
        String city = sc.nextLine();

        System.out.print("Please enter the state - ");
        String state = sc.nextLine();

        System.out.print("Please enter the pincode - ");
        int pincode = sc.nextInt();

        vehicleRentalSystem.addStore(storeId, storeName, address, city, state, pincode);
    }

    private static Vehicle getVehicle(VehicleManager vehicleManager){

        if(vehicleManager == null)  return null;

        //check this for  better code
        if(vehicleManager instanceof BikeManager){
            return getActiveVehicle(((BikeManager) vehicleManager).getBikes());
        }else if(vehicleManager instanceof CarManager){
            return getActiveVehicle(((CarManager) vehicleManager).getCars());
        }else if(vehicleManager instanceof ScootyManager){
            return getActiveVehicle(((ScootyManager) vehicleManager).getScooties());
        }
        return null;
    }

    private static Vehicle getActiveVehicle(List <? extends Vehicle> vehicles){
        for (Vehicle vehicle : vehicles) {
            if (Status.Active == vehicle.getStatus()) {
                return vehicle;
            }
        }
        return null;
    }

    private static Vehicle getVehicleChoice(Store store){
        //select buying option from store
        List<VehicleManager> vehicleManagerList = store.getVehicleManagerList();
        List<String> options = new ArrayList<>();

        for(VehicleManager vehicleManager : vehicleManagerList){
            if(vehicleManager instanceof BikeManager)
                options.add(VehicleType.Bike.toString());
            else if(vehicleManager instanceof CarManager)
                options.add(VehicleType.Car.toString());
            else if(vehicleManager instanceof ScootyManager)
                options.add(VehicleType.Scooty.toString());
        }

        System.out.println("Please choose one of the vehicles!");
        for(String option : options){
            System.out.print(option + " ");
        }
        System.out.println("\n");

        Scanner sc = new Scanner(System.in);
        String vehicleChoice = sc.nextLine();
        VehicleManager vehicleManager = store.getVehicleManager(vehicleChoice);

        Vehicle vehicle = getVehicle(vehicleManager);

        if(vehicle == null) {
            System.out.println("Sorry, we have no " + vehicleChoice + " left!");
            return null;
        }
        return vehicle;
    }

    private static Bill createBillAndPay(long totalDays, double totalCost, Reservation reservation){
        System.out.println("From which method you want to pay Rs " + totalCost);

        for(PaymentMode mode : PaymentMode.values()){
            System.out.print(mode + " ");
        }
        System.out.println("\n");

        Scanner sc = new Scanner(System.in);
        String paymentChoice = sc.nextLine();
        PaymentMode paymentMode = PaymentMode.valueOf(paymentChoice);

        Bill bill = new Bill(reservation, totalCost);
        Payment payment = new Payment(bill, paymentMode);
        bill.setPaid(true);
        return bill;
    }
    private static Reservation bookReservation(Date bookingFromDate, Date bookingTillDate, String bookingCity, User user, Vehicle vehicle){
        Reservation reservation = new Reservation();

        reservation.setReservationId();
        reservation.setBookingCity(bookingCity);
        reservation.setBookingFrom(bookingFromDate);
        reservation.setBookingTill(bookingTillDate);
        reservation.setUser(user);
        reservation.setVehicle(vehicle);

        Date currentDate = new Date();

        reservation.setBookingDate(currentDate);

        if(bookingFromDate.after(currentDate)){
            reservation.setReservationStatus(ReservationStatus.SCHEDULED);
        }else{
            reservation.setReservationStatus(ReservationStatus.INPROGRESS);
        }
        return reservation;
    }
}
