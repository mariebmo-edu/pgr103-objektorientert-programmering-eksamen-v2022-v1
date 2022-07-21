package Logic;

import Model.Answer;
import Model.Question;
import Model.Questionnaire;
import Model.User;
import Repo.AnswerRepo;
import Repo.QuestionRepo;
import Repo.QuestionnaireRepo;
import Repo.UserRepo;
import View.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class GameLogic {

    Boolean loggedIn = false;
    private QuestionnaireRepo questionnaireRepo;
    private QuestionRepo questionRepo;
    private AnswerRepo answerRepo;
    private UserRepo userRepo;
    private ArrayList<Questionnaire> questionnaires;
    private User user;
    private int currentScore = 0;
    private int maxScore = 0;

    public GameLogic() throws IOException {
        InitiateQuestionnaires();
    }

    public void InitiateQuestionnaires() throws IOException {
        questionnaireRepo = new QuestionnaireRepo();
        questionRepo = new QuestionRepo();
        answerRepo = new AnswerRepo();
        userRepo = new UserRepo();

        questionnaires = questionnaireRepo.getAllQuestionnaires();

        for (Questionnaire questionnaire : questionnaires) {
            questionnaire.setQuestions(questionRepo.getQuestionByQuestionnaireId(questionnaire.getId()));

            for (Question q : questionnaire.getQuestions()) {
                q.setAnswers(answerRepo.getAllAnswersByQuestionId(q.getId()));
            }
        }
    }

    public void Play() {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        String currentInput;

        ShowMainMenu(menu);

        while (true) {
            currentInput = scanner.nextLine();
            if (currentInput.equalsIgnoreCase("1") && loggedIn) {
                PlayGame(menu, scanner);
                break;
            } else if (currentInput.equalsIgnoreCase("1") && !loggedIn) {
                LogInMenu(menu, scanner);
                break;
            } else if (currentInput.equalsIgnoreCase("2")) {
                ShowHighScore(menu, scanner);
                break;
            } else if (currentInput.equalsIgnoreCase("Q")) {
                System.out.println("Ending session");
                break;
            } else {
                System.out.println("Invalid Input, please try again");
            }
        }
    }

    private void ShowMainMenu(Menu menu) {
        menu.formatHeader("QUIZ OF THE YEAR!");
        if (loggedIn) {
            System.out.println("hello " + user.getUserName() + "!");
            menu.formatMenuItem("Play Quiz!", 1);
        } else {
            menu.formatMenuItem("Log in", 1);
        }
        menu.formatMenuItem("High Scores", 2);
        System.out.println("Q. End Session");
    }

    private void ShowHighScore(Menu menu, Scanner scanner) {

        menu.formatHeader("HIGH SCORE");

        ArrayList<User> users = userRepo.getAllUsers();
        users.sort(new UserComparator());

        int i = 1;

        for (User user : users) {
            menu.formatMenuItem((user.getUserName() + " - " + user.getHighScore()), i++);
        }

        GoToMenu(scanner);

    }

    private void GoToMenu(Scanner scanner) {
        System.out.println("Go back to Main menu? y/n");

        String userInput = scanner.nextLine();

        switch (userInput.toLowerCase()) {
            case "y", "yes" -> Play();
            case "n", "no" -> System.out.println("Session Ending. Thank you for playing.");
            default -> System.out.println("Invalid input");
        }
    }

    private void LogInMenu(Menu menu, Scanner scanner) {

        menu.formatHeader("LOG IN");
        menu.formatMenuItem("log in", 1);
        menu.formatMenuItem("register user", 2);

        String selection = scanner.nextLine();

        switch (selection) {
            case "1" -> LogIn(scanner);
            case "2" -> RegisterUser(scanner);
        }

        GoToMenu(scanner);
    }

    private void LogIn(Scanner scanner) {

        while (true) {
            System.out.println("Username:");
            String username = scanner.nextLine();
            System.out.println("Password:");
            String password = scanner.nextLine();

            User loggedInUser = userRepo.retrieveUserByUsernamePassword(username, password);

            if (loggedInUser != null) {
                user = loggedInUser;
                loggedIn = true;
                System.out.println("Welcome back " + username);
                GoToMenu(scanner);
                break;
            } else {
                System.out.println("Wrong username or password.");
                GoToMenu(scanner);
            }
        }

    }

    private void RegisterUser(Scanner scanner) {

        System.out.println("Please make a new user");
        System.out.println("Username: ");
        String username = scanner.nextLine();

        while (true) {

            Boolean userExists = userRepo.retrieveUserByUsername(username);

            if (userExists) {
                System.out.println("User name is taken. Try another");
                System.out.println("Username: ");
                username = scanner.nextLine();
            } else break;
        }

        System.out.println("Password: ");
        String password = scanner.nextLine();


        User newUser = new User(username, password, 0);

        userRepo.insertUser(newUser);
        user = userRepo.retrieveUserByUsernamePassword(username, password);
        loggedIn = true;

        GoToMenu(scanner);
    }

    private void PlayGame(Menu menu, Scanner scanner) {
        String currentInput;
        Questionnaire selectedQuestionnaire = null;

        System.out.println("Please select a topic");

        int i = 1;
        for (Questionnaire q : questionnaires) {
            menu.formatMenuItem(q.getQuestionnaireName(), i++);
        }

        System.out.println("Q. End Game");

        while (true) {
            currentInput = scanner.nextLine();

            if (isInteger(currentInput)) {
                int selectedInputNum = Integer.parseInt(currentInput);

                if (selectedInputNum <= questionnaires.size() && selectedInputNum > 0) {
                    selectedQuestionnaire = questionnaires.get(selectedInputNum - 1);
                    menu.formatHeader(selectedQuestionnaire.getQuestionnaireName());
                    break;
                }
            } else if ("Q".equalsIgnoreCase(currentInput)) {
                System.out.println("Ending Session");
                break;
            }
            System.out.println("Invalid input, please try again");
        }

        if (selectedQuestionnaire != null) {
            for (Question q : selectedQuestionnaire.getQuestions()) {
                System.out.println(q.getQuestion());

                i = 1;

                for (Answer a : q.getAnswers()) {
                    menu.formatMenuItem(a.getAnswer(), i++);
                }
                System.out.println("N. Next Question");
                System.out.println("please answer: ");

                String answer;

                while (true) {
                    answer = scanner.nextLine();

                    if (isInteger(answer)) {
                        int selectedInputNum = Integer.parseInt(answer);

                        if (selectedInputNum <= q.getAnswers().size() && selectedInputNum > 0) {

                            maxScore++;

                            if (q.getAnswers().get(selectedInputNum - 1).isCorrect()) {
                                currentScore++;
                                System.out.println("Correct! " + currentScore + "/" + maxScore);

                            } else {
                                System.out.println("Aww, wrong answer! " + currentScore + "/" + maxScore);
                            }
                            break;
                        }
                    } else if ("N".equalsIgnoreCase(answer)) {
                        System.out.println("Next Question " + currentScore + "/" + ++maxScore);
                        break;
                    }
                    System.out.println("Invalid input, please try again");
                }
            }

            System.out.println("You're finished! Your score was " + currentScore + "/" + maxScore);
            userRepo.updateHighScore(user, currentScore);
            GoToMenu(scanner);
        }
    }

    private boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o2.getHighScore() - o1.getHighScore();
    }
}
