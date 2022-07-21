import Logic.GameLogic;
import Model.Question;
import Model.Questionnaire;
import Repo.AnswerRepo;
import Repo.QuestionRepo;
import Repo.QuestionnaireRepo;
import View.Menu;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) throws IOException {
        GameLogic gameLogic = new GameLogic();

        gameLogic.Play();
    }
}
