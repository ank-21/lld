package model;

import admin.TheatreController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private int theatreId;
    private String theatreName;
    private List<Audi> screens = new ArrayList<>();
    private List<Show> shows;

    public Theatre(String theatreName, int screenCount)
    {
        this.theatreName = theatreName;
        TheatreController theatreController = new TheatreController();
        this.screens = theatreController.addScreens(screenCount);
        this.shows = new ArrayList<>();
    }

    public int getTheatreId() {
        return theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public List<Audi> getScreens() {
        return screens;
    }

    public List<Show> getShows() {
        return shows;
    }

    public List<Show> getShowsForSelectedMovieAndTheatre(Movie movie){
        List<Show> showsForMovie = new ArrayList<>();
        for(Show show : shows){
            if(show.getMovie().equals(movie)){
                showsForMovie.add(show);
            }
        }
        return showsForMovie;
    }

    public void addShowsForMovie(Movie movie){
        int movieDuration = movie.getMovieDurationInMin();
        int intervalDuration = 15;
        int gapBetweenMovie = 10;

        int totalShowTime = 720;    //12*60
        int totalShows = totalShowTime/(movieDuration + intervalDuration + gapBetweenMovie);
        int itr  = 1;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.withHour(9).withMinute(0).withSecond(0).withNano(0);
        Audi screenFound = null;

        while(itr <= totalShows){
            Show show = new Show();
            show.setMovie(movie);
            show.setShowId(itr++);

            if(screenFound == null){
                for (Audi screen : screens){
                    if(!screen.isShowRunning()){
                        screen.setShowRunning(true);
                        screenFound = screen;
                        break;
                    }
                }
            }

            show.setAudi(screenFound);
            show.setStartTime(startTime);
            //Adding end time
            show.setEndTime(startTime.plusMinutes(movieDuration + intervalDuration));
            startTime = startTime.plusMinutes(movieDuration + intervalDuration + gapBetweenMovie);
            show.setAudi(screenFound);

            //assign cost to the individual Seat
            show.assignCost();
            shows.add(show);
        }
    }
}
