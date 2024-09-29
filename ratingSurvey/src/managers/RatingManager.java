package managers;

import models.Rating;
import models.Survey;
import models.SurveyResponse;

import java.util.HashMap;

public class RatingManager {
    private HashMap<String, Rating> ratings;
    private SurveyManager surveyManager;

    private RatingManager() {
        this.ratings = new HashMap<>();
        surveyManager = SurveyManager.getInstance();
    }

    public static volatile RatingManager instance = null;
    public static RatingManager getInstance(){
        if(instance == null){
            synchronized (RatingManager.class){
                if(instance == null){
                    return instance = new RatingManager();
                }
            }
        }
        return instance;
    }

    public void addRating(String id, String surveyId, String userId, int rating){
        ratings.put(id, new Rating(id, surveyId, userId, rating));
        HashMap<String, Survey> surveyHashMap = surveyManager.getSurveys();
        Survey survey = surveyHashMap.get(surveyId);
        survey.setAverageRating((survey.getAverageRating() * survey.getTotalRatingsCount() + rating) / (survey.getTotalRatingsCount() + 1));
        survey.setTotalRatingsCount(survey.getTotalRatingsCount() + 1);
    }
}
