package View;

public class Menu {

    private static void formatHeader(String title){

        String decorator = "-------------------";

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

    public static void printMenu(){
        formatHeader("Menu");
        printMenuSelections();
    }
}
