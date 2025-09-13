package toodoo.parser;

import toodoo.exceptions.EmptyDeadlineException;
import toodoo.exceptions.EmptyDescriptionException;

/**
 * Processes the user's input when the deadline Keyword is encountered.
 */
public class DeadlineProcessor {

    /**
     * Processes the user's input when the deadline Keyword is encountered and returns
     * the deadline's description and deadline.
     * @param deadlineStrings An array containing the words from the user's input
     *     when the deadline Keyword is encountered.
     * @return An array containing the deadlines's description and deadline.
     * @throws EmptyDescriptionException If the deadline's description is an empty string.
     * @throws EmptyDeadlineException If the deadline's deadline is an empty string.
     */
    public static String[] processDeadlineString(String[] deadlineStrings) throws EmptyDescriptionException,
            EmptyDeadlineException {
        assert deadlineStrings != null : "Input array should not be null";
        assert deadlineStrings.length > 1 : "Input should contain more than keyword";

        String[] deadlineOutputs = new String[2];
        StringBuilder description = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        boolean isBeforeDeadline = true;

        for (int i = 1; i < deadlineStrings.length; i++) {
            if (deadlineStrings[i].equals("/by")) {
                isBeforeDeadline = false;
            } else if (isBeforeDeadline) {
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

}
