import java.time.LocalDateTime;

public class Reservation {
    String reservationId;
    Customer customer;
    Vehicle vehicle;
    LocalDateTime bookingFrom;
    LocalDateTime bookingTill;
    int totalPrice;
    ReservationStatus reservationStatus;

    public Reservation(String reservationId, Customer customer, Vehicle vehicle, LocalDateTime bookingFrom, LocalDateTime bookingTill, int totalPrice, ReservationStatus reservationStatus) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.bookingFrom = bookingFrom;
        this.bookingTill = bookingTill;
        this.totalPrice = totalPrice;
        this.reservationStatus = reservationStatus;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getBookingFrom() {
        return bookingFrom;
    }

    public LocalDateTime getBookingTill() {
        return bookingTill;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
}
