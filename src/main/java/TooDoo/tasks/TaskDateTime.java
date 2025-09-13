package toodoo.tasks;

import java.time.LocalDateTime;

public class TaskDateTime {
    
    /**
     * Helper method to extract the relevant date from a task
     * For Events: returns the from date
     * For Deadlines: returns the deadline
     * For ToDos: returns a very distant future date (so they sort to the end)
     * @param task The task from which to extract the date
     * @return The relevant LocalDateTime for sorting
     */
    public static LocalDateTime getTaskDateTime(Task task) {
        if (task instanceof Event) {
            return ((Event) task).getFrom();
        } else if (task instanceof Deadline) {
            return ((Deadline) task).getDeadline();
        } else {
            // For ToDos, return a date far in the future so they sort to the end
            return LocalDateTime.MAX;
        }
    }

}
