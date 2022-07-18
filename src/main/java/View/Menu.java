package View;

import Model.Answer;
import Model.Question;
import Model.Questionnaire;
import Repo.AnswerRepo;
import Repo.QuestionRepo;
import Repo.QuestionnaireRepo;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {

    private static String decorator = "---------------------------------------------";

    private static void formatHeader(String title){

        System.out.println(decorator);
        System.out.println(centerTitle(title, decorator));
        System.out.println(decorator);
    }

    private static void formatMenuItem(String menuItem, int num){
        System.out.println(num + ". " + menuItem);
    }

    private static String centerTitle(String title, String decorator){
        int numOfSpaces = (decorator.length()/2)-(title.length()/2);

        return " ".repeat(Math.max(0, numOfSpaces)) + title;
    }

    private static void printMenuSelections(){
        formatMenuItem("Log in", 1);
        formatMenuItem("Select Questionnaire", 2);
        formatMenuItem("Show High Scores", 3);
    }

    public static void printMenu() throws IOException {
        formatHeader("Menu");
        printMenuSelections();

        QuestionRepo questionRepo = new QuestionRepo();

    }

    public static void LogIn(){
        formatHeader("Log In");
        System.out.println("Username: ");
        System.out.println("Password: ");
    }

    public static void PrintQuestionnaire() throws IOException {
        QuestionnaireRepo questionnaireRepo = new QuestionnaireRepo();
        QuestionRepo questionRepo = new QuestionRepo();
        AnswerRepo answerRepo = new AnswerRepo();

        ArrayList<Questionnaire> questionnaires = questionnaireRepo.getAllQuestionnaires();

        for(Questionnaire questionnaire : questionnaires){

            formatHeader(questionnaire.getQuestionnaireName());

            questionnaire.setQuestions(questionRepo.getQuestionByQuestionnaireId(questionnaire.getId()));

            for(Question q : questionnaire.getQuestions()){

                System.out.println(q.getQuestion());
                q.setAnswers(answerRepo.getAllAnswersByQuestionId(q.getId()));

                int i = 1;

                for(Answer a : q.getAnswers()){
                    formatMenuItem(a.getAnswer(), i++);
                }

                System.out.println(decorator);
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
    }
}
