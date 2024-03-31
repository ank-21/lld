package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Audi {
    private List<Seat> seats;
    private int audiId;
    private static int lastAssignedId = 0;
    private int totalSeats;
    private int seatsPerRow;
    private boolean isShowRunning;

    public Audi(){
        lastAssignedId++;
        this.audiId = lastAssignedId;
        this.totalSeats = 100;
        this.seatsPerRow = 10;
        this.isShowRunning = false;
        this.seats = new ArrayList<>();
    }

    public void addSeats(){
        //Running a loop for 100 iterations
        char startRow = 'A';

        for(int i = 0; i < totalSeats; i++){
            // Each row has 10 seats and row starts from A to J
            //The first 3 rows are regular , then next 5 are classic, and the rest are premium
            Seat seat = new Seat();
            seat.setSeatId(UUID.randomUUID());
            seat.setSeatNumber((i % seatsPerRow) + 1);
            char row = (char)(startRow + (i / seatsPerRow));
            seat.setRow(row);

            if(i < 30){
                seat.setSeatType(SeatType.REGULAR);
            } else if(i < 80){
                seat.setSeatType(SeatType.CLASSIC);
            } else{
                seat.setSeatType(SeatType.PREMIUM);
            }
            seats.add(seat);
        }

    }

    public List<Seat> getSeats() {
        return seats;
    }

    public boolean isShowRunning() {
        return isShowRunning;
    }

    public void setShowRunning(boolean showRunning) {
        isShowRunning = showRunning;
    }

    public int getAudiId() {
        return audiId;
    }
}
