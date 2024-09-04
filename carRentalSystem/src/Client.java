public class Client {
    public static void main(String[] args) {
        RentalSystem rentalSystem = RentalSystem.getInstance();
        Customer customer = new Customer("Ankit", "120421", "TFRE22", "ankit@gmail.com");

        rentalSystem.showAllVehicles();
        rentalSystem.addReservation("1003", customer);
        rentalSystem.addReservation("1003", customer);
        rentalSystem.addReservation("4003", customer);

    }
}
