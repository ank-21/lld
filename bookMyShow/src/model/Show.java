package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private int showId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Audi audi;
    private Movie movie;
    private List<String> bookedSeatNumbers = new ArrayList<>();

    public int getShowId() {
        return showId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Audi getAudi() {
        return audi;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<String> getBookedSeatNumbers() {
        return bookedSeatNumbers;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setAudi(Audi audi) {
        this.audi = audi;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setBookedSeatNumbers(List<String> bookedSeatNumbers) {
        this.bookedSeatNumbers = bookedSeatNumbers;
    }

    protected void assignCost(){
        List<Seat> seats = audi.getSeats();
        int basePrice = movie.getBasePrice();
        int seatPrice;

        for(Seat seat : seats){
            if(seat.getSeatType() == SeatType.REGULAR){
                seatPrice = basePrice;
                seat.setCost(seatPrice);
            }else if(seat.getSeatType() == SeatType.CLASSIC){
                seatPrice = (int)(basePrice * 1.3);
                seat.setCost(seatPrice);
            } else if(seat.getSeatType() == SeatType.PREMIUM){
                seatPrice = (int) (basePrice * 1.6);
                seat.setCost(seatPrice);
            }
        }
    }
}
