package model;

import java.util.HashMap;
import java.util.List;

public class City {
    private String cityName;
    private String state;
    private HashMap<Movie, List<Theatre>> movieVsTheatreList;

    public City(String cityName, String state) {
        this.cityName = cityName;
        this.state = state;
        this.movieVsTheatreList = new HashMap<>();
    }

    public String getCityName() {
        return cityName;
    }

    public String getState() {
        return state;
    }

    public HashMap<Movie, List<Theatre>> getMovieVsTheatreList() {
        return movieVsTheatreList;
    }

    public List<Theatre> getTheatreList(Movie movie){
        return movieVsTheatreList.get(movie);
    }
}
