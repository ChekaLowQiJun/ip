package toodoo.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import toodoo.exceptions.EmptyDescriptionException;

public class ToDoProcessor {
    
    /**
     * Processes the user's input when the todo Keyword is encountered and returns the todo's description.
     * @param toDoStrings An array containing the words from the user's input when the todo Keyword is encountered.
     * @return The todo's description.
     * @throws EmptyDescriptionException If the todo's description is an empty string.
     */
    public static String processToDoString(String... toDoStrings) throws EmptyDescriptionException {
        if (toDoStrings.length == 1) {
            throw new EmptyDescriptionException();
        }

        List<String> list = new ArrayList<>(Arrays.asList(toDoStrings));
        list.remove(0);

        return String.join(" ", list);
    }

}
