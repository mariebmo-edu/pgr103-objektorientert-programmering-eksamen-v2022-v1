package Model;

public class User {

    private int id;
    private String userName;
    private String password;
    private int highScore;

    public User(int id, String userName, String password, int highScore) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.highScore = highScore;
    }

    public User(String userName, String password, int highScore) {
        this.userName = userName;
        this.password = password;
        this.highScore = highScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", highScore=" + highScore +
                '}';
    }
}
