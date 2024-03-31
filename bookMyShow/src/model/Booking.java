package model;

import java.util.List;

public class Booking {
    private static int lastBookingId = 0;
    private int bookingId;
    private Show show;
    private List<Seat> bookedSeat;
    private Payment paymentStatus;
    private int totalCost;

    public Booking(Show show, List<Seat> bookedSeat){
        this.show = show;
        this.bookedSeat = bookedSeat;
        this.bookingId = ++lastBookingId;

        this.paymentStatus = new Payment(PaymentStatus.AWAITING);

    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Seat> getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(List<Seat> bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public Payment getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Payment paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
