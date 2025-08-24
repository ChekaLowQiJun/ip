import java.util.Scanner;

public class Parser {

    private static Scanner userInputScanner = new Scanner(System.in);
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    
    public Parser() {

    }

    public String getKeyWord(String[] splitUserInput) {
        return splitUserInput[0];
    }

    public String getUserInput() {
        return userInputScanner.nextLine();
    }

    public void processUserInput(TaskList taskList) {
         while (true) {

            Keyword keyword;
            String userInput = this.getUserInput();
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
                    userInputScanner.close();
                    return;
                case LIST:
                    taskList.printList();
                    break;
                case MARK:
                    index = Integer.parseInt(splitUserInput[1]) - 1;
                    taskList.mark(index);
                    break;
                case UNMARK:
                    index = Integer.parseInt(splitUserInput[1]) - 1;
                    taskList.unmark(index);
                    break;
                case TODO:
                    String processedToDoString = this.processToDoString(splitUserInput);
                    taskList.addToDo(processedToDoString);
                    break;
                case DEADLINE:
                    String[] processedDeadlineString = this.processDeadlineString(splitUserInput);
                    taskList.addDeadline(processedDeadlineString[0], processedDeadlineString[1]);
                    break;
                case EVENT:
                    String[] processedEventString = this.processEventString(splitUserInput);
                    taskList.addEvent(processedEventString[0], processedEventString[1], processedEventString[2]);
                    break;
                case DELETE:
                    index = Integer.parseInt(splitUserInput[1]) - 1;
                    taskList.delete(index);
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

    public String processToDoString(String[] splitToDoString) throws EmptyDescriptionException {
        StringBuilder description = new StringBuilder();
        for (int i = 1;i < splitToDoString.length;i++) {
            description.append(splitToDoString[i] + " ");
        }

        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }

        return description.toString();
    }

    public String[] processDeadlineString(String[] splitDeadlineString) throws EmptyDescriptionException, EmptyDeadlineException {
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

    public String[] processEventString(String[] splitEventString) throws EmptyDescriptionException, EmptyFromException, EmptyToException {
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
}
