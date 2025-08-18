import java.util.Scanner;

public class TooDoo {

    private static Scanner userInputScanner = new Scanner(System.in);

    private static final String CHAT_BOT_NAME = "TooDoo";
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    private static Task[] taskList = new Task[100];
    private static int itemsInList = 0;

    public static void getWelcome() {
        System.out.println( HORIZONTAL_LINE + "How are you dooing! " 
                            + CHAT_BOT_NAME + " at your service!\n"
                            + "What would you like me too doo for you tooday?\n"
                            +  HORIZONTAL_LINE);
    }

    public static void getExit() {
        System.out.println(HORIZONTAL_LINE + "Toodles! Visit me again soon!\n" 
                            + HORIZONTAL_LINE);
    }

    public static String getUserInput() {
        return userInputScanner.nextLine();
    }

    public static void processUserInput() {
         while (true) {
            String userInput = TooDoo.getUserInput();
            String[] splitUserInput = userInput.split(" ");
            String keyword = splitUserInput[0];

            if (keyword.equals("bye")) {
                break;
            } else if (keyword.equals("list")) {
                TooDoo.printList();
            } else if (keyword.equals("mark")) {
                taskList[Integer.parseInt(splitUserInput[1]) - 1].markAsDone();
                    System.out.println(HORIZONTAL_LINE + "Good Job! You have completed this task:\n" 
                                        + taskList[Integer.parseInt(splitUserInput[1]) - 1] + "\n" 
                                        + HORIZONTAL_LINE);
            } else if (keyword.equals("unmark")) {
                taskList[Integer.parseInt(splitUserInput[1]) - 1].markAsNotDone();
                System.out.println(HORIZONTAL_LINE + "It's okay! Let's finish it another time!\n" 
                                    + taskList[Integer.parseInt(splitUserInput[1]) - 1] + "\n" 
                                    + HORIZONTAL_LINE);
            } else {
                System.out.println(HORIZONTAL_LINE + "Added: " + userInput + "\n" 
                                    + HORIZONTAL_LINE);
                
                taskList[itemsInList] = new Task(userInput);
                itemsInList++;
            }
        }
    }

    public static String getKeyWord(String[] splitUserInput) {
        return splitUserInput[0];
    }

    public static void printList() {
        System.out.println(HORIZONTAL_LINE + "Presenting too you your task list:");

        for (int i = 0;i < itemsInList;i++) {
            System.out.println((i + 1) + "." + taskList[i]);
        }

        System.out.println(HORIZONTAL_LINE);
}

    public static void main(String[] args) {

        TooDoo.getWelcome();
        TooDoo.processUserInput();
        TooDoo.getExit();
        userInputScanner.close();
    }
}
