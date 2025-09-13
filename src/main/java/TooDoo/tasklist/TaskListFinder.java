package toodoo.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import toodoo.tasks.Task;

public class TaskListFinder {
    
    /**
     * Prints the Tasks in the tasklist that contains the regex in their description.
     * @param regex A regular expression used to find Tasks by their description.
     * @return A string containing matching tasks.
     */
    public static String find(String regex, ArrayList<Task> tasks) {
        List<Task> matchingTasks = tasks.stream()
            .filter(task -> task.getDescription().contains(regex))
            .collect(Collectors.toList());

        return formatFindResults(matchingTasks, regex);
    }

    /**
     * Formats the results of the find command.
     * @param matchingTasks A list of tasks that match the regex.
     * @param regex The regex used to find matching tasks.
     * @return A formatted string of the find results.
     */
    private static String formatFindResults(List<Task> matchingTasks, String regex) {
        if (matchingTasks.isEmpty()) {
            return "No tasks found matching: " + regex;
        }

        StringBuilder result = new StringBuilder("These are what you are looking for right:\n");
        IntStream.range(0, matchingTasks.size())
            .forEach(i -> result.append((i + 1) + "." + matchingTasks.get(i) + "\n"));

        return result.toString();
    }
}
