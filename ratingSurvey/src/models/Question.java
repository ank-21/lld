package models;

public class Question {
    private String id;
    private String text;
    private QuestionType questionType;

    public Question(String id, String text, QuestionType questionType) {
        this.id = id;
        this.text = text;
        this.questionType = questionType;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
}
