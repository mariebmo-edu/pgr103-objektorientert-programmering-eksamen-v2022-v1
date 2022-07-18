package Model;

public class Questionnaire {

    private int id;
    private String questionnaireName;

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
}
