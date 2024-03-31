package model;

import java.util.List;
import java.util.UUID;

public class Movie {
    private String movieName;
    private List<String> cast;
    private UUID id;
    private int movieDurationInMin;

    private int basePrice;

    public Movie(String movieName, List<String> cast, UUID id, int movieDurationInMin, int basePrice) {
        this.movieName = movieName;
        this.cast = cast;
        this.id = id;
        this.movieDurationInMin = movieDurationInMin;
        this.basePrice = basePrice;
    }

    public String getMovieName() {
        return movieName;
    }

    public List<String> getCast() {
        return cast;
    }

    public UUID getId() {
        return id;
    }

    public int getMovieDurationInMin() {
        return movieDurationInMin;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
