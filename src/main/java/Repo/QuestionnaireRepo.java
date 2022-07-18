package Repo;

import Model.Question;
import Model.QuestionType;
import Model.Questionnaire;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionnaireRepo extends AbstractRepo<Questionnaire>{

    public QuestionnaireRepo() throws IOException {
        super("questionnaire");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public boolean insertQuestionnaire(Questionnaire questionnaire){

        String query = "INSERT INTO questionnaire(questionnaireName) VALUES ('" + questionnaire.getQuestionnaireName() + "')";
        return InsertToDatabase(query);
    }

    public ArrayList<Questionnaire> getAllQuestionnaires(){
        return RetrieveAllFromDatabase();
    }

    public boolean deleteQuestionnaire(Questionnaire questionnaire){
        String query = "DELETE FROM question WHERE id='" + questionnaire.getId() + "'";
        return DeleteFromDatabase(query);
    }

    @Override
    public Questionnaire resultMapper(ResultSet resultSet) throws SQLException {
        return new Questionnaire(resultSet.getInt("id") ,resultSet.getString("questionnaireName"));
    }
}
