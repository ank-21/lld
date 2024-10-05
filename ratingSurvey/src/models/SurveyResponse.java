package models;

import java.util.List;

public class SurveyResponse {
    private String id;
    private String surveyId;
    private String userId;
    private List<Answer> answers;

    public SurveyResponse(String id, String surveyId, String userId, List<Answer> answers) {
        this.id = id;
        this.surveyId = surveyId;
        this.userId = userId;
        this.answers = answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public String getUserId() {
        return userId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
