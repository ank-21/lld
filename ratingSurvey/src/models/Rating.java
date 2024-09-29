package models;

public class Rating {
    private String id;
    private String surveyId;
    private String UserId;
    private int rating;

    public Rating(String id, String surveyId, String userId, int rating) {
        this.id = id;
        this.surveyId = surveyId;
        UserId = userId;
        this.rating = rating;
    }
}
