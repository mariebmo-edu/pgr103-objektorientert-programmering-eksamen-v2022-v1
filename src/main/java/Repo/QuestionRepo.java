package Repo;

import Model.Question;
import Model.QuestionType;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionRepo extends AbstractRepo<Question> {

    public QuestionRepo() throws IOException {
        super("question");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public boolean insertQuestion(Question question){

        String query = "INSERT INTO question(questionnaireId, question, type) VALUES ('" + question.getQuestionnaireId() +"', '" + question.getQuestion() + "','" + question.getType() + "')";
        return InsertToDatabase(query);
    }

    public ArrayList<Question> getAllQuestions(){
        return RetrieveAllFromDatabase();
    }

    public boolean deleteQuestion(Question question){
        String query = "DELETE FROM question WHERE id='" + question.getId() + "'";
        return DeleteFromDatabase(query);
    }

    @Override
    public Question resultMapper(ResultSet resultSet) throws SQLException {
        return new Question(resultSet.getInt("id"), resultSet.getInt("questionnaireId"), resultSet.getString("question"), QuestionType.valueOf(resultSet.getString("type")));
    }
}
