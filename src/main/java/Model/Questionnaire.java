package Model;

import java.util.ArrayList;

public class Questionnaire {

    private int id;
    private String questionnaireName;
    private ArrayList<Question> questions;

    public Questionnaire(int id, String questionnaireName) {
        this.id = id;
        this.questionnaireName = questionnaireName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", questionnaireName='" + questionnaireName + '\'' +
                '}';
    }
}
