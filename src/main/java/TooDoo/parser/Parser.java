package toodoo.parser;

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

/**
 * The Parser is used by TooDoo to receive and process the input by the user.
 */
public class Parser {

    /**
     * Returns a String representing the Keyword of the user's input.
     * 
     * @param userInputStrings An array containing the words in the user's input.
     * @return The keyword (first word) of the user's input.
     */
    public String getKeyWord(String[] userInputStrings) {
        return userInputStrings[0];
    }

    /**
     * Processes the user's input and carries out the appropriate actions.
     * 
     * @param taskList A TaskList object used to manage TooDoo's task list.
     */
    public String processUserInput(TaskList taskList, String userInput) {
        Keyword keyword;
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
                return "exit";
            case LIST:
                return taskList.toString();
            case MARK:
                if (splitUserInput.length == 1) {
                    throw new EmptyIndexException();
                }
                index = Integer.parseInt(splitUserInput[1]) - 1;
                return taskList.mark(index);
            case UNMARK:
                if (splitUserInput.length == 1) {
                    throw new EmptyIndexException();
                }
                index = Integer.parseInt(splitUserInput[1]) - 1;
                return taskList.unmark(index);
            case TODO:
                String processedToDoString = this.processToDoString(splitUserInput);
                return taskList.addToDo(processedToDoString);
            case DEADLINE:
                String[] processedDeadlineString = this.processDeadlineString(splitUserInput);
                return taskList.addDeadline(processedDeadlineString[0], processedDeadlineString[1]);
            case EVENT:
                String[] processedEventString = this.processEventString(splitUserInput);
                return taskList.addEvent(processedEventString[0], processedEventString[1], processedEventString[2]);
            case DELETE:
                if (splitUserInput.length == 1) {
                    throw new EmptyIndexException();
                }
                index = Integer.parseInt(splitUserInput[1]) - 1;
                return taskList.delete(index);
            case FIND:
                if (splitUserInput.length == 1) {
                    throw new EmptyRegexException();
                }
                return taskList.find(splitUserInput[1]);
            case UNKNOWN:
                throw new UnknownKeywordException(firstWord);
            default:
                throw new UnknownKeywordException(firstWord);
            }

        } catch (UnknownKeywordException e) {
            return e.getMessage();
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        } catch (EmptyDeadlineException e) {
            return e.getMessage();
        } catch (EmptyFromException e) {
            return e.getMessage();
        } catch (EmptyToException e) {
            return e.getMessage();
        } catch (EmptyRegexException e) {
            return e.getMessage();
        } catch (EmptyIndexException e) {
            return e.getMessage();
        } catch (DateTimeConflictException e) {
            return e.getMessage();
        } catch (IndexDoesNotExistException e) {
            return e.getMessage();
        } catch (TaskAlreadyMarkedException e) {
            return e.getMessage();
        } catch (TaskAlreadyUnmarkedException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Please provide a valid integer for the task number :(";
        }
    }

    /**
     * Processes the user's input when the todo Keyword is encountered and returns the todo's description.
     * 
     * @param toDoStrings An array containing the words from the user's input when the todo Keyword is encountered.
     * @return The todo's description.
     * @throws EmptyDescriptionException If the todo's description is an empty string.
     */
    public String processToDoString(String[] toDoStrings) throws EmptyDescriptionException {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < toDoStrings.length; i++) {
            description.append(toDoStrings[i] + " ");
        }

        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }

        return description.toString();
    }

    /**
     * Processes the user's input when the deadline Keyword is encountered and returns the deadline's description and deadline.
     * 
     * @param deadlineStrings An array containing the words from the user's input when the deadline Keyword is encountered.
     * @return An array containing the deadlines's description and deadline.
     * @throws EmptyDescriptionException If the deadline's description is an empty string.
     * @throws EmptyDeadlineException If the deadline's deadline is an empty string.
     */
    public String[] processDeadlineString(String[] deadlineStrings) throws EmptyDescriptionException, 
            EmptyDeadlineException {
        String[] deadlineOutputs = new String[2];
        StringBuilder description = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        boolean beforeDeadline = true;

        for (int i = 1; i < deadlineStrings.length; i++) {
            if (deadlineStrings[i].equals("/by")) {
                beforeDeadline = false;
            } else if (beforeDeadline) {
                description.append(deadlineStrings[i] + " ");
            } else {
                deadline.append(deadlineStrings[i] + " ");
            }
        }

        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        } else if (deadline.length() == 0) {
            throw new EmptyDeadlineException();
        }

        deadlineOutputs[0] = description.deleteCharAt(description.length() - 1).toString();
        deadlineOutputs[1] = deadline.deleteCharAt(deadline.length() - 1).toString();

        return deadlineOutputs;
    }

    /**
     * Processes the user's input when the event Keyword is encountered and returns the event's description, from and to.
     * 
     * @param eventStrings An array containing the words from the user's input when the event Keyword is encountered.
     * @return An array containing the event's description, from and to.
     * @throws EmptyDescriptionException If the event's description is an empty string.
     * @throws EmptyFromException If the event's from is an empty string.
     * @throws EmptyToException If the event's to is an empty string.
     * @throws DateTimeConflictException If the to is before the from.
     */
    public String[] processEventString(String[] eventStrings) throws EmptyDescriptionException, 
            EmptyFromException, EmptyToException, DateTimeConflictException {
        String[] eventOutputs = new String[3];
        StringBuilder description = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        boolean isBeforeFrom = true;
        boolean isBeforeTo = true;

        for (int i = 1; i < eventStrings.length; i++) {
            if (eventStrings[i].equals("/from")) {
                isBeforeFrom = false;
            } else if (eventStrings[i].equals("/to")) {
                isBeforeTo = false;
            } else if (isBeforeFrom) {
                description.append(eventStrings[i] + " ");
            } else if (isBeforeTo) {
                from.append(eventStrings[i] + " ");
            }else {
                to.append(eventStrings[i] + " ");
            }
        }

        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        } else if (from.length() == 0) {
            throw new EmptyFromException();
        } else if (to.length() == 0) {
            throw new EmptyToException();
        }

        eventOutputs[0] = description.deleteCharAt(description.length() - 1).toString();
        eventOutputs[1] = from.deleteCharAt(from.length() - 1).toString();
        eventOutputs[2] = to.deleteCharAt(to.length() - 1).toString();

        return eventOutputs;
    }
}
