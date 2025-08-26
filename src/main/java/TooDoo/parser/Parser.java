package toodoo.parser;

import java.util.Scanner;

import toodoo.exceptions.DateTimeConflictException;
import toodoo.exceptions.EmptyDeadlineException;
import toodoo.exceptions.EmptyDescriptionException;
import toodoo.exceptions.EmptyFromException;
import toodoo.exceptions.EmptyIndexException;
import toodoo.exceptions.EmptyToException;
import toodoo.exceptions.IndexDoesNotExistException;
import toodoo.exceptions.TaskAlreadyMarkedException;
import toodoo.exceptions.TaskAlreadyUnmarkedException;
import toodoo.exceptions.UnknownKeywordException;
import toodoo.exceptions.EmptyRegexException;

import toodoo.tasklist.TaskList;
import toodoo.ui.Ui;

/**
 * The Parser is used by TooDoo to receive and process the input by the user.
 */
public class Parser {
    private static Scanner userInputScanner = new Scanner(System.in);

    private static Ui ui = new Ui();

    /**
     * Returns a String representing the Keyword of the user's input.
     * 
     * @param splitUserInput An array containing the words in the user's input.
     * @return The keyword (first word) of the user's input.
     */
    public String getKeyWord(String[] splitUserInput) {
        return splitUserInput[0];
    }

    /**
     * Scans the next line of text from the user's input.
     * 
     * @return The next line of text from the user's input.
     */
    public String getUserInput() {
        return userInputScanner.nextLine();
    }

    /**
     * Processes the user's input and carries out the appropriate actions.
     * 
     * @param taskList A TaskList object used to manage TooDoo's task list.
     */
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
                    if (splitUserInput.length == 1) {
                        throw new EmptyIndexException();
                    }
                    index = Integer.parseInt(splitUserInput[1]) - 1;
                    taskList.mark(index);
                    break;
                case UNMARK:
                    if (splitUserInput.length == 1) {
                        throw new EmptyIndexException();
                    }
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
                    if (splitUserInput.length == 1) {
                        throw new EmptyIndexException();
                    }
                    index = Integer.parseInt(splitUserInput[1]) - 1;
                    taskList.delete(index);
                    break;
                case FIND:
                    if (splitUserInput.length == 1) {
                        throw new EmptyRegexException();
                    }
                    taskList.find(splitUserInput[1]);
                    break;
                case UNKNOWN:
                    throw new UnknownKeywordException(firstWord);
                }
            } catch (UnknownKeywordException e) {
                ui.printMessage(e.getMessage());
            } catch (EmptyDescriptionException e) {
                ui.printMessage(e.getMessage());
            } catch (EmptyDeadlineException e) {
                ui.printMessage(e.getMessage());
            } catch (EmptyFromException e) {
                ui.printMessage(e.getMessage());
            } catch (EmptyToException e) {
                ui.printMessage(e.getMessage());
            } catch (EmptyRegexException e) {
                ui.printMessage(e.getMessage());
            } catch (EmptyIndexException e) {
                ui.printMessage(e.getMessage());
            } catch (DateTimeConflictException e) {
                ui.printMessage(e.getMessage());
            } catch (IndexDoesNotExistException e) {
                ui.printMessage(e.getMessage());
            } catch (TaskAlreadyMarkedException e) {
                ui.printMessage(e.getMessage());
            } catch (TaskAlreadyUnmarkedException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    /**
     * Processes the user's input when the todo Keyword is encountered and returns the todo's description.
     * 
     * @param splitToDoString An array containing the words from the user's input when the todo Keyword is encountered.
     * @return The todo's description.
     * @throws EmptyDescriptionException If the todo's description is an empty string.
     */
    public String processToDoString(String[] splitToDoString) throws EmptyDescriptionException {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < splitToDoString.length; i++) {
            description.append(splitToDoString[i] + " ");
        }

        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }

        return description.toString();
    }

    /**
     * Processes the user's input when the deadline Keyword is encountered and returns the deadline's description and deadline.
     * 
     * @param splitDeadlineString An array containing the words from the user's input when the deadline Keyword is encountered.
     * @return An array containing the deadlines's description and deadline.
     * @throws EmptyDescriptionException If the deadline's description is an empty string.
     * @throws EmptyDeadlineException If the deadline's deadline is an empty string.
     */
    public String[] processDeadlineString(String[] splitDeadlineString) throws EmptyDescriptionException, 
            EmptyDeadlineException {
        String[] deadlineOutput = new String[2];
        StringBuilder description = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        boolean beforeDeadline = true;

        for (int i = 1; i < splitDeadlineString.length; i++) {
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

    /**
     * Processes the user's input when the event Keyword is encountered and returns the event's description, from and to.
     * 
     * @param splitEventString An array containing the words from the user's input when the event Keyword is encountered.
     * @return An array containing the event's description, from and to.
     * @throws EmptyDescriptionException If the event's description is an empty string.
     * @throws EmptyFromException If the event's from is an empty string.
     * @throws EmptyToException If the event's to is an empty string.
     * @throws DateTimeConflictException If the to is before the from.
     */
    public String[] processEventString(String[] splitEventString) throws EmptyDescriptionException, 
            EmptyFromException, EmptyToException, DateTimeConflictException {
        String[] eventOutput = new String[3];
        StringBuilder description = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        boolean isBeforeFrom = true;
        boolean isBeforeTo = true;

        for (int i = 1; i < splitEventString.length; i++) {
            if (splitEventString[i].equals("/from")) {
                isBeforeFrom = false;
            } else if (splitEventString[i].equals("/to")) {
                isBeforeTo = false;
            } else if (isBeforeFrom) {
                description.append(splitEventString[i] + " ");
            } else if (isBeforeTo) {
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
