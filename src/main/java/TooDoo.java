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
        String[] taskList = new String[100];
        int itemsInList = 0;

        System.out.println(welcomeMessage);

        while (true) {
            userInput = userInputScanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0;i < itemsInList;i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________\n" 
                             + "added: " + userInput + "\n" 
                             + "____________________________________________________________");
                
                taskList[itemsInList] = userInput;
                itemsInList++;
            }
        }

        userInputScanner.close();
        System.out.println(exitMessage);
    }
}
