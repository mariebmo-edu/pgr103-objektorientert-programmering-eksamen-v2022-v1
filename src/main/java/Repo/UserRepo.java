package Repo;

import Model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepo extends AbstractRepo<User> {

    public UserRepo() throws IOException {
        super("user");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean insertUser(User user) {

        String query = "INSERT INTO user(userName, password, highScore) VALUES ('" + user.getUserName() + "','" + user.getPassword() + "','" + user.getHighScore() + "')";
        return Insert(query);
    }

    public ArrayList<User> getAllUsers() {
        return RetrieveAll();
    }

    public boolean deleteUser(User user) {
        String query = "DELETE FROM question WHERE id='" + user.getId() + "'";
        return Delete(query);
    }

    public boolean updateHighScore(User user, int highScore) {
        String query = "UPDATE user SET highScore = '" + highScore + "' WHERE id='" + user.getId() + "'";
        return Update(query);
    }

    public User retrieveUserByUsernamePassword(String username, String password) {
        String query = "SELECT * FROM user WHERE userName='" + username + "' AND password='" + password + "'";
        return RetrieveByConditions(query);
    }

    public Boolean retrieveUserByUsername(String username) {
        String query = "SELECT * FROM user WHERE userName='" + username + "'";
        return Exists(query);
    }

    @Override
    public User resultMapper(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"), resultSet.getString("userName"), resultSet.getString("password"), resultSet.getInt("highscore"));
    }
}
