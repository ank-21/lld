import product.Vehicle;

import java.util.Date;
import java.util.Random;

public class Reservation {
    int reservationId;
    User user;
    Vehicle vehicle;
    Date BookingDate;
    Date BookingFrom;
    Date BookingTill;
    String BookingCity;
    ReservationStatus reservationStatus;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId() {
        Random random = new Random();
        this.reservationId = random.nextInt(101);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        BookingDate = bookingDate;
    }

    public Date getBookingFrom() {
        return BookingFrom;
    }

    public void setBookingFrom(Date bookingFrom) {
        BookingFrom = bookingFrom;
    }

    public Date getBookingTill() {
        return BookingTill;
    }

    public void setBookingTill(Date bookingTill) {
        BookingTill = bookingTill;
    }

    public String getBookingCity() {
        return BookingCity;
    }

    public void setBookingCity(String bookingCity) {
        BookingCity = bookingCity;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
