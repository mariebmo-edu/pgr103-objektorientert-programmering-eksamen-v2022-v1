package Repo;

import Model.Answer;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class AnswerRepo extends AbstractRepo<Answer> {

    public AnswerRepo() throws IOException {
        super("answer");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public boolean insertAnswer(Answer answer){

        String query = "INSERT INTO answer(questionId, answer, isCorrect) VALUES ('" + answer.getQuestionId() +"', '" + answer.getAnswer() + "','" + answer.isCorrect() + "')";
        return Insert(query);
    }

    public ArrayList<Answer> getAllAnswers(){
        return RetrieveAll();
    }

    public ArrayList<Answer> getAllAnswersByQuestionId(int id){

        String query = "SELECT * FROM answer WHERE questionId=" + id;
        return RetrieveAllWithId(query);
    }


    public boolean deleteAnswer(Answer answer){
        String query = "DELETE FROM answer WHERE id='" + answer.getId() + "'";
        return DeleteFromDatabase(query);
    }

    @Override
    public Answer resultMapper(ResultSet resultSet) throws SQLException {
        return new Answer(resultSet.getInt("id"), resultSet.getInt("questionId"), resultSet.getString("answer"), resultSet.getBoolean("isCorrect"));
    }
}
