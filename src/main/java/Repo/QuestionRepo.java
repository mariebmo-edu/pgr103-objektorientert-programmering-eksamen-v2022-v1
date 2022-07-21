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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean insertQuestion(Question question) {

        String query = "INSERT INTO question(questionnaireId, question, type) VALUES ('" + question.getQuestionnaireId() + "', '" + question.getQuestion() + "','" + question.getType() + "')";
        return Insert(query);
    }

    public ArrayList<Question> getAllQuestions() {
        return RetrieveAll();
    }

    public boolean deleteQuestion(Question question) {
        String query = "DELETE FROM question WHERE id='" + question.getId() + "'";
        return Delete(query);
    }

    public Question getQuestionById(int id) {

        String query = "SELECT * FROM question WHERE id='" + id + "'";
        return RetrieveById(query);
    }

    public ArrayList<Question> getQuestionByQuestionnaireId(int id) {
        String query = "SELECT * FROM question WHERE questionnaireId='" + id + "'";
        return RetrieveAllWithId(query);
    }

    @Override
    public Question resultMapper(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        int questionnaireId = resultSet.getInt("questionnaireId");
        String question = resultSet.getString("question");
        QuestionType questionType = QuestionType.valueOf(resultSet.getString("type"));
        return new Question(id, questionnaireId, question, questionType);
    }
}
