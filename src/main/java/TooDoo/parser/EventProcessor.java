package toodoo.parser;

import toodoo.exceptions.DateTimeConflictException;
import toodoo.exceptions.EmptyDescriptionException;
import toodoo.exceptions.EmptyFromException;
import toodoo.exceptions.EmptyToException;

public class EventProcessor {
    
    /**
     * Processes the user's input when the event Keyword is encountered and
     * returns the event's description, from and to.
     * @param eventStrings An array containing the words from the user's input when the event Keyword is encountered.
     * @return An array containing the event's description, from and to.
     * @throws EmptyDescriptionException If the event's description is an empty string.
     * @throws EmptyFromException If the event's from is an empty string.
     * @throws EmptyToException If the event's to is an empty string.
     * @throws DateTimeConflictException If the to is before the from.
     */
    public static String[] processEventString(String[] eventStrings) throws EmptyDescriptionException,
            EmptyFromException, EmptyToException, DateTimeConflictException {
        assert eventStrings != null : "Input array should not be null";
        assert eventStrings.length > 1 : "Input should contain more than keyword";

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
            } else {
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

        eventOutputs[0] = removeLastCharacter(description).toString();
        eventOutputs[1] = removeLastCharacter(from).toString();
        eventOutputs[2] = removeLastCharacter(to).toString();

        return eventOutputs;
    }

    /**
     * Removes the last character from a StringBuilder.
     * @param string The StringBuilder from which to remove the last character.
     * @return The StringBuilder with the last character removed.
     */
    public static StringBuilder removeLastCharacter(StringBuilder string) {
        return string.deleteCharAt(string.length() - 1);
    }

}
