package managers;

import models.Answer;
import models.SurveyResponse;

import java.util.HashMap;
import java.util.List;

public class SurveyResponseManagers {
    private HashMap<String, SurveyResponse> surveyResponses;
    private UserManager userManager;
    private SurveyManager surveyManager;

    private SurveyResponseManagers() {
        this.surveyResponses = new HashMap<String, SurveyResponse>();
        userManager = UserManager.getInstance();
        surveyManager = SurveyManager.getInstance();
    }

    private static volatile SurveyResponseManagers instance = null;

    public static SurveyResponseManagers getInstance(){
        if(instance == null){
            synchronized (SurveyResponseManagers.class){
                if(instance == null){
                    return instance = new SurveyResponseManagers();
                }
            }
        }
        return instance;
    }

    public void addResponse(String id, String surveyId, String userId, List<Answer> answers){
        surveyResponses.put(id, new SurveyResponse(id, surveyId, userId, answers));
    }

    public void showResponses(){
        for(SurveyResponse surveyResponse : surveyResponses.values()){
            System.out.println("Survey Response by user : " + userManager.getUsers().get(surveyResponse.getUserId()).getName());
            System.out.println("Survey Id : " + surveyResponse.getSurveyId());
            System.out.println("Survey Title : " + surveyManager.getSurveys().get(surveyResponse.getSurveyId()).getTitle());

            for(Answer answer : surveyResponse.getAnswers()){
                System.out.println("Question : " + surveyManager.getQuestionsMap().get(answer.getQuestionId()).getText());
                System.out.println("Answer : " + answer.getResponse());
            }
        }
    }
}
