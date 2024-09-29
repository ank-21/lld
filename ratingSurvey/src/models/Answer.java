package models;

public class Answer {
    private String id;
    private String questionId;
    private String response;

    public Answer(String id, String questionId, String response) {
        this.id = id;
        this.questionId = questionId;
        this.response = response;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getResponse() {
        return response;
    }
}
