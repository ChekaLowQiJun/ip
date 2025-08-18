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
            } else if (keyword.equals("deadline")) {
                TooDoo.addDeadline(splitUserInput);
            } else if (keyword.equals("event")) {
                TooDoo.addEvent(splitUserInput);
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

    public static void addDeadline(String[] splitUserInput) {
        taskList[itemsInList] = new Deadline(processDeadlineString(splitUserInput)[0], processDeadlineString(splitUserInput)[1]);
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                            + taskList[itemsInList] + "\n" 
                            + "Now you have " + (itemsInList + 1) + " tasks in the list.\n"
                            + HORIZONTAL_LINE);
        itemsInList++;
    }

    public static void addEvent(String[] splitUserInput) {
        taskList[itemsInList] = new Event(processEventString(splitUserInput)[0], processEventString(splitUserInput)[1], processEventString(splitUserInput)[2]);
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

    public static String[] processDeadlineString(String[] splitDeadlineString) {
        String[] deadlineOutput = new String[2];
        StringBuilder description = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        boolean beforeDeadline = true;

        for (int i = 1;i < splitDeadlineString.length;i++) {
            if (splitDeadlineString[i].equals("/by")) {
                beforeDeadline = false;
            } else if (beforeDeadline) {
                description.append(splitDeadlineString[i] + " ");
            } else {
                deadline.append(splitDeadlineString[i] + " ");
            }
        }
        deadlineOutput[0] = description.toString();
        deadlineOutput[1] = deadline.toString();

        return deadlineOutput;
    }

    public static String[] processEventString(String[] splitEventString) {
        String[] eventOutput = new String[3];
        StringBuilder description = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        boolean beforeFrom = true;
        boolean beforeTo = true;

        for (int i = 1;i < splitEventString.length;i++) {
            if (splitEventString[i].equals("/from")) {
                beforeFrom = false;
            } else if (splitEventString[i].equals("/to")) {
                beforeTo = false;
            } else if (beforeFrom) {
                description.append(splitEventString[i] + " ");
            } else if (beforeTo) {
                from.append(splitEventString[i] + " ");
            }else {
                to.append(splitEventString[i] + " ");
            }
        }
        eventOutput[0] = description.toString();
        eventOutput[1] = from.toString();
        eventOutput[2] = to.toString();

        return eventOutput;
    }

    public static void main(String[] args) {

        TooDoo.getWelcome();
        TooDoo.processUserInput();
        TooDoo.getExit();
        userInputScanner.close();
    }
}
