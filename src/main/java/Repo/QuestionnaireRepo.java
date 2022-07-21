package Repo;

import Model.Questionnaire;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionnaireRepo extends AbstractRepo<Questionnaire> {

    public QuestionnaireRepo() throws IOException {
        super("questionnaire");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean insertQuestionnaire(Questionnaire questionnaire) {

        String query = "INSERT INTO questionnaire(questionnaireName) VALUES ('" + questionnaire.getQuestionnaireName() + "')";
        return Insert(query);
    }

    public ArrayList<Questionnaire> getAllQuestionnaires() {
        return RetrieveAll();
    }

    public boolean deleteQuestionnaire(Questionnaire questionnaire) {
        String query = "DELETE FROM question WHERE id='" + questionnaire.getId() + "'";
        return Delete(query);
    }

    public Questionnaire getQuestionnaireById(int id) {

        String query = "SELECT * FROM questionnaire WHERE id='" + id + "'";
        return RetrieveById(query);
    }

    @Override
    public Questionnaire resultMapper(ResultSet resultSet) throws SQLException {
        return new Questionnaire(resultSet.getInt("id"), resultSet.getString("questionnaireName"));
    }
}
