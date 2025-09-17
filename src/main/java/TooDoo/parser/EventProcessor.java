package toodoo.parser;

import toodoo.exceptions.EmptyDescriptionException;
import toodoo.exceptions.EmptyFromException;
import toodoo.exceptions.EmptyToException;

/**
 * Processes the user's input when the event Keyword is encountered.
 */
public class EventProcessor {

    /**
     * Processes the user's input when the event Keyword is encountered and
     * returns the event's description, from and to.
     *
     * @param eventStrings An array containing the words from the user's input when the event Keyword is encountered.
     * @return An array containing the event's description, from and to.
     * @throws EmptyDescriptionException If the event's description is an empty string.
     * @throws EmptyFromException If the event's from is an empty string.
     * @throws EmptyToException If the event's to is an empty string.
     */
    public static String[] processEventString(String[] eventStrings) throws EmptyDescriptionException,
            EmptyFromException, EmptyToException {
        assert eventStrings != null : "Input array should not be null";
        assert eventStrings.length > 1 : "Input should contain more than keyword";

        String[] eventOutputs = new String[3];
        StringBuilder description = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        buildEventFields(eventStrings, description, from, to);

        validateEventInputs(description, from, to);

        eventOutputs[0] = removeLastCharacter(description).toString();
        eventOutputs[1] = removeLastCharacter(from).toString();
        eventOutputs[2] = removeLastCharacter(to).toString();

        return eventOutputs;
    }

    /**
     * Removes the last character from a StringBuilder.
     *
     * @param string The StringBuilder from which to remove the last character.
     * @return The StringBuilder with the last character removed.
     */
    public static StringBuilder removeLastCharacter(StringBuilder string) {
        return string.deleteCharAt(string.length() - 1);
    }

    /**
     * Check the event inputs for missing details.
     *
     * @param description Description of the event.
     * @param from From of the event.
     * @param to To of the event.
     * @throws EmptyDescriptionException If the event's description is an empty string.
     * @throws EmptyFromException If the event's from is an empty string.
     * @throws EmptyToException If the event's to is an empty string.
     */
    public static void validateEventInputs(StringBuilder description, StringBuilder from, StringBuilder to)
            throws EmptyDescriptionException, EmptyFromException, EmptyToException {
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        } else if (from.length() == 0) {
            throw new EmptyFromException();
        } else if (to.length() == 0) {
            throw new EmptyToException();
        }
    }

    /**
     * Builds the fields of an event.
     *
     * @param eventStrings An array containing the words from the user's input when the event Keyword is encountered.
     * @param description Description of the event.
     * @param from From of the event.
     * @param to To of the event.
     */
    public static void buildEventFields(String[] eventStrings, StringBuilder description, StringBuilder from,
            StringBuilder to) {
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
    }
}
