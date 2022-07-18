package Model;

import java.util.ArrayList;

public class Question {

    private int id;
    private int questionnaireId;
    private String question;
    private QuestionType type;
    private ArrayList<Answer> answers;

    public Question(int id, int questionnaireId, String question, QuestionType type) {
        this.id = id;
        this.questionnaireId = questionnaireId;
        this.question = question;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionnaireId=" + questionnaireId +
                ", question='" + question + '\'' +
                ", type=" + type +
                '}';
    }
}
