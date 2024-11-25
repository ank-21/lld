package models;

import java.util.ArrayList;
import java.util.List;

public class Survey {
    private final String id;
    private final String title;
    private final String description;
    private List<Question> questions;
    private int totalRatingsCount;
    private double averageRating;

    public Survey(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
        this.totalRatingsCount = 0;
        this.averageRating = 0.0;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setTotalRatingsCount(int totalRatingsCount) {
        this.totalRatingsCount = totalRatingsCount;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getTotalRatingsCount() {
        return totalRatingsCount;
    }

    public double getAverageRating() {
        return averageRating;
    }
}
