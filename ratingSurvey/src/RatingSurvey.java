import managers.RatingManager;
import managers.SurveyManager;
import managers.SurveyResponseManagers;
import managers.UserManager;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class RatingSurvey {
    public static void main(String[] args) {
        Admin admin = new Admin("Master", "mr21");
        UserManager userManager = UserManager.getInstance();
        userManager.addUser("ank-21", "Ankit");
        userManager.addUser("aisha-11", "Aayusha");
        userManager.addUser("mu17", "Mukul");
        userManager.addUser("annu25", "Anupam");
        userManager.addUser("its_minal_26", "Minal");

        SurveyManager surveyManager = SurveyManager.getInstance();
        surveyManager.createSurvey("s1", "IPL 2024 Survey", "Question related to IPL 2024");
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("q1", "Your thoughts on impact player?", QuestionType.TEXT));
        questions.add(new Question("q3", "Which is your favourite team", QuestionType.TEXT));
        questions.add(new Question("q4", "Do you want to participate in IPL?", QuestionType.BULLET));
        surveyManager.addQuestion("s1", questions);

        SurveyResponseManagers surveyResponseManagers = SurveyResponseManagers.getInstance();
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("a1", "q3", "KKR"));
        answers1.add(new Answer("a2", "q4", "Yes"));
        surveyResponseManagers.addResponse("sr1", "s1", "mu17", answers1);

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("a44", "q3", "KKR"));
        answers2.add(new Answer("a32", "q4", "Yes"));
        answers2.add(new Answer("a32", "q1", "It is creating less all rounders"));
        surveyResponseManagers.addResponse("sr2", "s1", "annu25", answers2);

        RatingManager ratingManager = RatingManager.getInstance();
        ratingManager.addRating("r1", "s1", "mu17", 4);
        ratingManager.addRating("r2", "s1", "ank-21", 3);
        ratingManager.addRating("r4", "s1", "mu17", 5);
        surveyManager.showSurveys();
        surveyResponseManagers.showResponses();
    }
}
