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
        Task[] taskList = new Task[100];
        int itemsInList = 0;
        String[] splitUserInput;

        System.out.println(welcomeMessage);

        while (true) {
            userInput = userInputScanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________\n"
                                    + "Presenting too you your task list:");
                for (int i = 0;i < itemsInList;i++) {
                    System.out.println((i + 1) + "." + taskList[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                splitUserInput = userInput.split(" ");

                if (splitUserInput[0].equals("mark")) {
                    taskList[Integer.parseInt(splitUserInput[1]) - 1].markAsDone();
                    System.out.println("____________________________________________________________\n" 
                                + "Good Job! You have completed this task:\n" 
                                + taskList[Integer.parseInt(splitUserInput[1]) - 1] + "\n" 
                                + "____________________________________________________________");
                } else if (splitUserInput[0].equals("unmark")) {
                    taskList[Integer.parseInt(splitUserInput[1]) - 1].markAsNotDone();
                    System.out.println("____________________________________________________________\n" 
                                + "It's okay! Let's finish it another time!\n" 
                                + taskList[Integer.parseInt(splitUserInput[1]) - 1] + "\n" 
                                + "____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________\n" 
                                + "Added: " + userInput + "\n" 
                                + "____________________________________________________________");
                    
                    taskList[itemsInList] = new Task(userInput);
                    itemsInList++;
                }
            }
        }

        userInputScanner.close();
        System.out.println(exitMessage);
    }
}
