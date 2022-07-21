package View;

import Model.Answer;
import Model.Question;
import Model.Questionnaire;
import Repo.AnswerRepo;
import Repo.QuestionRepo;
import Repo.QuestionnaireRepo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Menu{

    private  ArrayList<Questionnaire> questionnaires;

    private  String decorator = "---------------------------------------------";

    public  void formatHeader(String title){

        System.out.println(decorator);
        System.out.println(centerTitle(title, decorator));
        System.out.println(decorator);
    }

    public void formatMenuItem(String menuItem, int num){
        System.out.println(num + ". " + menuItem);
    }

    private String centerTitle(String title, String decorator){
        int numOfSpaces = (decorator.length()/2)-(title.length()/2);

        return " ".repeat(Math.max(0, numOfSpaces)) + title;
    }
}
