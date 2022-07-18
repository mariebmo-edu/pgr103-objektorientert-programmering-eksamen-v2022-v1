package Repo;

import Model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepo extends AbstractRepo<User>{

    public UserRepo() throws IOException {
        super("user");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public boolean insertUser(User user){

        String query = "INSERT INTO user(userName, password, highScore) VALUES ('" + user.getUserName() + "','" + user.getPassword() + "','" + user.getHighScore() + "')";
        return Insert(query);
    }

    public ArrayList<User> getAllQuestionnaires(){
        return RetrieveAll();
    }

    public boolean deleteQuestionnaire(User user){
        String query = "DELETE FROM question WHERE id='" + user.getId() + "'";
        return DeleteFromDatabase(query);
    }

    @Override
    public User resultMapper(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id") ,resultSet.getString("userName"), resultSet.getString("password"), resultSet.getInt("highscore"));
    }
}
