import java.util.Scanner;

public class TooDoo {
    public static void main(String[] args) {

        Scanner userInputScanner = new Scanner(System.in);
        String chatBotName = "TooDoo";
        String welcomeMessage = "____________________________________________________________\n" 
                                + "How are you dooing! " + chatBotName + " at your service!\n"
                                + "What would you like me too doo for you tooday?\n"
                                +  "____________________________________________________________";
        String exitMessage = "____________________________________________________________\n" 
                             + "Toodles! Visit me again soon!\n" 
                             + "____________________________________________________________";
        String userInput;

        System.out.println(welcomeMessage);

        while (true) {
            userInput = userInputScanner.nextLine();

            if (userInput == "bye") {
                break;
            }

            System.out.println("____________________________________________________________\n" 
                             + userInput + "\n" 
                             + "____________________________________________________________");
        }

        userInputScanner.close();
        System.out.println(exitMessage);
    }
}
