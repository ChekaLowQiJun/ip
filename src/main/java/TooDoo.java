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
                int index = Integer.parseInt(splitUserInput[1]) - 1;
                TooDoo.mark(index);
            } else if (keyword.equals("unmark")) {
                int index = Integer.parseInt(splitUserInput[1]) - 1;
                TooDoo.unmark(index);
            } else if (keyword.equals("todo")) {
                TooDoo.addToDo(splitUserInput);
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

    public static void mark(int index) {
        taskList[index].markAsDone();
        System.out.println(HORIZONTAL_LINE + "Good Job! You have completed this task:\n" 
                            + taskList[index] + "\n" 
                            + HORIZONTAL_LINE);
    }

    public static void unmark(int index) {
        taskList[index].markAsNotDone();
        System.out.println(HORIZONTAL_LINE + "It's okay! Let's finish it another time!\n" 
                            + taskList[index] + "\n" 
                            + HORIZONTAL_LINE);
    }

    public static void addToDo(String[] splitUserInput) {
        taskList[itemsInList] = new ToDo(processToDoString(splitUserInput));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                            + taskList[itemsInList] + "\n" 
                            + "Now you have " + (itemsInList + 1) + " tasks in the list.\n"
                            + HORIZONTAL_LINE);
        itemsInList++;
    }

    public static String processToDoString(String[] splitToDoString) {
        StringBuilder description = new StringBuilder();
        for (int i = 1;i < splitToDoString.length;i++) {
            description.append(splitToDoString[i] + " ");
        }
        return description.toString();
    }

    public static void main(String[] args) {

        TooDoo.getWelcome();
        TooDoo.processUserInput();
        TooDoo.getExit();
        userInputScanner.close();
    }
}
