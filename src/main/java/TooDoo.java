import java.util.Scanner;

public class TooDoo {

    private static final String CHAT_BOT_NAME = "TooDoo";
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    public static String getWelcome() {
        return HORIZONTAL_LINE + "How are you dooing! " 
                + CHAT_BOT_NAME + " at your service!\n"
                + "What would you like me too doo for you tooday?\n"
                +  HORIZONTAL_LINE;
    }

    public static String getExit() {
        return "____________________________________________________________\n" 
                + "Toodles! Visit me again soon!\n" 
                + "____________________________________________________________";
    }

    public static void main(String[] args) {

        Scanner userInputScanner = new Scanner(System.in);
                                
        String userInput;
        Task[] taskList = new Task[100];
        int itemsInList = 0;
        String[] splitUserInput;

        System.out.println(getWelcome());

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
        System.out.println(getExit());
    }
}
