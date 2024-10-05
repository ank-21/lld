package managers;

import models.Question;
import models.Survey;

import java.util.HashMap;
import java.util.List;

public class SurveyManager {
    private HashMap<String, Survey> surveys;
    private HashMap<String, Question> questionsMap;

    private SurveyManager() {
        this.surveys = new HashMap<>();
        // Uses in surveyResponseManager for printing question for answer
        this.questionsMap = new HashMap<>();
    }

    private static volatile SurveyManager instance = null;

    public static SurveyManager getInstance(){
        if(instance == null){
            synchronized (SurveyManager.class){
                if(instance == null){
                    return instance = new SurveyManager();
                }
            }
        }
        return instance;
    }

    public void createSurvey(String id, String title, String description){
        surveys.put(id, new Survey(id, title, description));
    }

    public void addQuestion(String id, List<Question> questions){
        Survey survey = surveys.get(id);
        survey.setQuestions(questions);

        for(Question question : questions){
            questionsMap.put(question.getId(), question);
        }
    }

    public HashMap<String, Survey> getSurveys() {
        return surveys;
    }

    public HashMap<String, Question> getQuestionsMap() {
        return questionsMap;
    }

    public void showSurveys(){
        for(Survey survey : surveys.values()){
            System.out.println("survey Id : " + survey.getId());
            System.out.println("survey Title : " + survey.getTitle());
            System.out.println("survey Description : " + survey.getDescription());
            System.out.println("survey Rating : " + survey.getAverageRating());
            for(Question question : survey.getQuestions()){
                System.out.println("Question : " + question.getText());
            }
        }
    }
}
