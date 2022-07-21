package Logic;

import Model.Answer;
import Model.Question;
import Model.Questionnaire;
import Model.User;
import Repo.AnswerRepo;
import Repo.QuestionRepo;
import Repo.QuestionnaireRepo;
import View.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GameLogic {

    private ArrayList<Questionnaire> questionnaires;
    private User user;
    private int currentScore = 0;
    private int maxScore = 0;

    public GameLogic() throws IOException {
        InitiateQuestionnaires();
    }

    public void InitiateQuestionnaires() throws IOException {
        QuestionnaireRepo questionnaireRepo = new QuestionnaireRepo();
        QuestionRepo questionRepo = new QuestionRepo();
        AnswerRepo answerRepo = new AnswerRepo();

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

        Questionnaire selectedQuestionnaire = null;

        menu.formatHeader("QUIZ OF THE YEAR!");
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
                        } else if ("Q".equalsIgnoreCase(answer)) {
                            System.out.println("Ending Session");
                            break;
                        }
                        System.out.println("Invalid input, please try again");
                    }
                }
            }

            System.out.println("You're finished! Your score was " + currentScore + "/" + maxScore);
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
