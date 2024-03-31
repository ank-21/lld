package admin;

import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    public static void main(String[] args) {
        //Generating the book my show instance
        BookMyShow bms = BookMyShow.getInstance();
        System.out.println("Hey Admin, let's enter some details");

        for(int i = 0; i < 2; i++){
            System.out.println("Adding theatre in the city");
            bms.addTheatreInCity();
        }
        for(int i = 0; i < 3; i++){
            System.out.println("Adding Movie to the theatre");
            bms.addMovie();
        }

        Scanner sc = new Scanner(System.in);
        String userBooking;
        System.out.print("Hey User, Do you want to book the ticket : Y/N : ");
        userBooking = sc.nextLine();

        while("Y".equalsIgnoreCase(userBooking)){

            //Ask the city for watching the movies
            City userAskedCity = null;

            while(userAskedCity == null){
                System.out.print("Please enter the city you want to book the movie : ");
                String userCity = sc.nextLine();
                userAskedCity = bms.verifyCity(userCity);
            }

            //Display movie list for the city
            bms.showMoviesList(userAskedCity);

            Movie userSelectedMovie = null;

            //user selects a movie
            while(userSelectedMovie == null){
                System.out.print("Please select a Movie : ");
                String movieName = sc.nextLine();
                userSelectedMovie = bms.validateMovie(movieName, userAskedCity);
            }

            //Display all the theatre for the selected movie in the selected city

            List<Theatre> theatreList = userAskedCity.getTheatreList(userSelectedMovie);

            System.out.println("In " + userAskedCity.getCityName() + " , there are these theatres showing the movie " + userSelectedMovie.getMovieName());
            displayTheatreList(theatreList);
            Theatre userSelectedTheatre = null;

            //user selects a theatre
            while(userSelectedTheatre == null){
                System.out.print("Please select a Theatre : ");
                String theatreName = sc.nextLine();
                userSelectedTheatre = validateTheatre(theatreList, theatreName);
            }

            //Display all the shows with the time information for the selected movie and theatre
            List<Show> showsList = userSelectedTheatre.getShowsForSelectedMovieAndTheatre(userSelectedMovie);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            displayShowsList(showsList, formatter);

            Show userSelectedShow = null;

            //user selects a show
            while(userSelectedShow == null){
                try{
                    System.out.print("Please enter the showtime in the form of yyyy-MM-dd HH:mm : ");
                    String showTime = sc.nextLine();
                    LocalDateTime startTime = LocalDateTime.parse(showTime, formatter);
                    userSelectedShow = validateShow(showsList, startTime);
                }catch (Exception e){
                    System.out.println("Please enter the time in correct format");
                }
            }
            List<String> bookedSeatNumber = userSelectedShow.getBookedSeatNumbers();

            displaySeats(userSelectedShow, bookedSeatNumber);

            System.out.print("\nPlease enter the total quantity of seats you want to book : ");
            int quantity = sc.nextInt();
            sc.nextLine();
            List<Seat> bookedSeatForUser = new ArrayList<>();

            while(quantity > 0){

                //user selects a seat
                Seat userSelectedSeat = null;
                int seatNumber = 0;
                char row = 'A';
                while(userSelectedSeat == null){
                    System.out.print("Please select row by entering row value : ");
                    row = sc.next().charAt(0);
                    System.out.print("Please select seat by entering the Row no : ");
                    seatNumber = sc.nextInt();
                    sc.nextLine();

                    userSelectedSeat = validateSeat(userSelectedShow, seatNumber, row);
                }
                quantity--;
                String userBookedSeat = String.valueOf(row) + seatNumber;
                bookedSeatNumber.add(userBookedSeat);
                bookedSeatForUser.add(userSelectedSeat);
            }
            userSelectedShow.setBookedSeatNumbers(bookedSeatNumber);
            Booking newBooking = new Booking(userSelectedShow, bookedSeatForUser);
            System.out.println("Your booking details are : ");
            System.out.println("Movie Name : " + newBooking.getShow().getMovie().getMovieName());
            System.out.println("Movie Time : " + newBooking.getShow().getStartTime().format(formatter));
            System.out.print("Seats are : ");
            List<Seat> userBookedSeat = newBooking.getBookedSeat();
            for(Seat seat : userBookedSeat){
                System.out.print(String.valueOf(seat.getRow()) + seat.getSeatNumber() + " ");
            }
            System.out.println("Audi : " + newBooking.getShow().getAudi().getAudiId());

            System.out.print("Do you want to pay : Y/N ");
            String paymentChoice = sc.nextLine();
            Payment payment = newBooking.getPaymentStatus();

            if("Y".equalsIgnoreCase(paymentChoice)){
                payment.setStatus(PaymentStatus.SUCCESSFUL);
                System.out.println("Your Payment has been done, Please do come before the movie time to enjoy");
            }else{
                System.out.println("Please pay at the counter");
            }
            System.out.print("Do you still want to book more tickets - Y/N : ");
            userBooking = sc.nextLine();
        }

    }

    private static void displaySeats(Show show, List<String> bookedSeatNumber){

        List<Seat> seats = show.getAudi().getSeats();
        char currentRowValue = seats.getFirst().getRow();
        SeatType currentSeatType = seats.getFirst().getSeatType();

        System.out.println(currentSeatType.toString());
        System.out.println("Price - " + seats.getFirst().getCost() + "\n");

        for(Seat seat : seats){
            if(currentRowValue != seat.getRow()){
                if(seat.getSeatType() != currentSeatType){
                    System.out.println("\n\n" + seat.getSeatType().toString());
                    currentSeatType = seat.getSeatType();
                    System.out.print("Price - " + seat.getCost());
                }
                currentRowValue = seat.getRow();
                System.out.println("\n");
            }
            if(!bookedSeatNumber.isEmpty() && bookedSeatNumber.contains(String.valueOf(seat.getRow()) + seat.getSeatNumber()))
                System.out.print("X ");
            else
                System.out.print(String.valueOf(seat.getRow()) + seat.getSeatNumber() + " ");

        }
    }

    private static Seat validateSeat(Show show, int seatNumber, char row){

        List<Seat> seats = show.getAudi().getSeats();
        List<String> bookedSeats = show.getBookedSeatNumbers();
        String seatChoice = String.valueOf(row) +  seatNumber;
        for(Seat seat : seats){
            if(seat.getSeatNumber() == seatNumber && seat.getRow() == row && !bookedSeats.contains(seatChoice)){
                return seat;
            }
        }
        System.out.println("Please select the correct seat number");
        return null;
    }

    private static void displayShowsList(List<Show> showList, DateTimeFormatter formatter){

        for(Show show : showList){
            System.out.println("Show starting time : " + show.getStartTime().format(formatter));
        }
    }

    private static Show validateShow(List<Show> showList, LocalDateTime startTime){

        for(Show show : showList){
            if(startTime.equals(show.getStartTime())){
                return show;
            }
        }
        System.out.println("Please select the correct show time");
        return null;
    }
    

    private static void displayTheatreList(List<Theatre> theatreList){

        for(Theatre theatre : theatreList){
            System.out.println("Theatre name : " + theatre.getTheatreName());
        }
    }

    private static Theatre validateTheatre(List<Theatre> theatreList, String theatreName){

        for(Theatre theatre : theatreList){
            if(theatreName.equalsIgnoreCase(theatre.getTheatreName())){
                return theatre;
            }
        }
        System.out.println("Please select the correct theatre");
        return null;
    }
}
