import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;;

public class TooDoo {

    private static Scanner userInputScanner = new Scanner(System.in);

    private static final String CHAT_BOT_NAME = "TooDoo";
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String STORAGE_PATH = "./../storage/TooDooList.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    // private static final String STORAGE_PATH = "./../src/main/storage/TooDooList.txt"; // For testing

    private static ArrayList<Task> taskList;

    private Storage storage;

    public TooDoo(String filePath) {
        storage = new Storage(filePath);
    }

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

            Keyword keyword;
            String userInput = TooDoo.getUserInput();
            String[] splitUserInput = userInput.split(" ");
            String firstWord = splitUserInput[0];
            int index;
            
            try {
                keyword = Keyword.valueOf(firstWord.toUpperCase());
            } catch (IllegalArgumentException e) {
                keyword = Keyword.UNKNOWN;
            }

            try {
                switch (keyword) {
                case BYE:
                    return;
                case LIST:
                    TooDoo.printList();
                    break;
                case MARK:
                    index = Integer.parseInt(splitUserInput[1]) - 1;
                    TooDoo.mark(index);
                    break;
                case UNMARK:
                    index = Integer.parseInt(splitUserInput[1]) - 1;
                    TooDoo.unmark(index);
                    break;
                case TODO:
                    TooDoo.addToDo(splitUserInput);
                    break;
                case DEADLINE:
                    TooDoo.addDeadline(splitUserInput);
                    break;
                case EVENT:
                    TooDoo.addEvent(splitUserInput);
                    break;
                case DELETE:
                    index = Integer.parseInt(splitUserInput[1]) - 1;
                    TooDoo.delete(index);
                    break;
                case UNKNOWN:
                    throw new UnknownKeywordException(firstWord);
                }
            } catch (UnknownKeywordException e) {
                System.out.println(HORIZONTAL_LINE + e.getMessage() + "\n" 
                                    + HORIZONTAL_LINE);
            } catch (EmptyDescriptionException e) {
                System.out.println(HORIZONTAL_LINE + e.getMessage() + "\n" 
                                    + HORIZONTAL_LINE);
            } catch (EmptyDeadlineException e) {
                System.out.println(HORIZONTAL_LINE + e.getMessage() + "\n" 
                                    + HORIZONTAL_LINE);
            } catch (EmptyFromException e) {
                System.out.println(HORIZONTAL_LINE + e.getMessage() + "\n" 
                                    + HORIZONTAL_LINE);
            } catch (EmptyToException e) {
                System.out.println(HORIZONTAL_LINE + e.getMessage() + "\n" 
                                    + HORIZONTAL_LINE);
            }
        }
    }

    public static String getKeyWord(String[] splitUserInput) {
        return splitUserInput[0];
    }

    public static void printList() {
        System.out.println(HORIZONTAL_LINE + "Presenting too you your task list:");

        for (int i = 0;i < taskList.size();i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void mark(int index) {
        taskList.get(index).markAsDone();
        System.out.println(HORIZONTAL_LINE + "Good Job! You have completed this task:\n" 
                            + taskList.get(index) + "\n" 
                            + HORIZONTAL_LINE);
    }

    public static void unmark(int index) {
        taskList.get(index).markAsNotDone();
        System.out.println(HORIZONTAL_LINE + "It's okay! Let's finish it another time!\n" 
                            + taskList.get(index) + "\n" 
                            + HORIZONTAL_LINE);
    }

    public static void delete(int index) {
        System.out.println(HORIZONTAL_LINE + "I have removed this task from the list for you:\n" 
                            + taskList.get(index) + "\n" + "You now have " + (taskList.size() - 1) + " tasks remaining in the list.\n" 
                            + HORIZONTAL_LINE);
        taskList.remove(index);
    }

    public static void addToDo(String[] splitUserInput) throws EmptyDescriptionException {
        taskList.add(new ToDo(processToDoString(splitUserInput)));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                            + taskList.get(taskList.size() - 1) + "\n" 
                            + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                            + HORIZONTAL_LINE);
    }

    public static void addDeadline(String[] splitUserInput) throws EmptyDescriptionException, EmptyDeadlineException {
        String[] processedDealineString = processDeadlineString(splitUserInput);
        String description = processedDealineString[0];
        LocalDateTime by = LocalDateTime.parse(processedDealineString[1], DATE_TIME_FORMATTER);
        
        taskList.add(new Deadline(description, by));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                            + taskList.get(taskList.size() - 1) + "\n" 
                            + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                            + HORIZONTAL_LINE);
    }

    public static void addEvent(String[] splitUserInput) throws EmptyDescriptionException, EmptyFromException, EmptyToException {
        String[] processedEventString = processEventString(splitUserInput);
        String description = processedEventString[0];
        LocalDateTime from = LocalDateTime.parse(processedEventString[1], DATE_TIME_FORMATTER);
        LocalDateTime to = LocalDateTime.parse(processedEventString[2], DATE_TIME_FORMATTER);

        taskList.add(new Event(description, from, to));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                            + taskList.get(taskList.size() - 1) + "\n" 
                            + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                            + HORIZONTAL_LINE);
    }

    public static String processToDoString(String[] splitToDoString) throws EmptyDescriptionException {
        StringBuilder description = new StringBuilder();
        for (int i = 1;i < splitToDoString.length;i++) {
            description.append(splitToDoString[i] + " ");
        }

        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }

        return description.toString();
    }

    public static String[] processDeadlineString(String[] splitDeadlineString) throws EmptyDescriptionException, EmptyDeadlineException {
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

        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        } else if (deadline.length() == 0) {
            throw new EmptyDeadlineException();
        }

        deadlineOutput[0] = description.deleteCharAt(description.length() - 1).toString();
        deadlineOutput[1] = deadline.deleteCharAt(deadline.length() - 1).toString();

        return deadlineOutput;
    }

    public static String[] processEventString(String[] splitEventString) throws EmptyDescriptionException, EmptyFromException, EmptyToException {
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

        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        } else if (from.length() == 0) {
            throw new EmptyFromException();
        } else if (to.length() == 0) {
            throw new EmptyToException();
        }

        eventOutput[0] = description.deleteCharAt(description.length() - 1).toString();
        eventOutput[1] = from.deleteCharAt(from.length() - 1).toString();
        eventOutput[2] = to.deleteCharAt(to.length() - 1).toString();

        return eventOutput;
    }

    /* 
    public static void saveList() {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH);
            StringBuilder tasks = new StringBuilder();
            for (int i = 0;i < itemsInList;i++) {
                tasks.append(taskList.get(i).getTaskString() + "\n");
            }
            fw.write(tasks.toString());
            fw.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public static void loadList() {
        try {
            File taskListFile = new File(STORAGE_PATH); 
            Scanner taskListScanner = new Scanner(taskListFile);
            while (taskListScanner.hasNext()) {
                taskList.add(processStorageInput(taskListScanner.nextLine()));
                itemsInList++;
            }
            taskListScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Task processStorageInput(String input) {
        String[] splitInput = input.split(" \\| ");
        String typeOfTask = splitInput[0];
        boolean isDone = splitInput[1].equals("X") ? true : false;
        Task task;

        if (typeOfTask.equals("T")) {
            task = new ToDo(splitInput[2]);
        } else if (typeOfTask.equals("D")) {
            task = new Deadline(splitInput[2], LocalDateTime.parse(splitInput[3], DATE_TIME_FORMATTER));
        } else {
            task = new Event(splitInput[2], LocalDateTime.parse(splitInput[3], DATE_TIME_FORMATTER), LocalDateTime.parse(splitInput[4], DATE_TIME_FORMATTER));
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
        */

    public static void main(String[] args) {

        try {
            TooDoo toodoo = new TooDoo(STORAGE_PATH);
            TooDoo.getWelcome();
            taskList = toodoo.storage.loadList();
            TooDoo.processUserInput();
            toodoo.storage.saveList(taskList);
            TooDoo.getExit();
            userInputScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
