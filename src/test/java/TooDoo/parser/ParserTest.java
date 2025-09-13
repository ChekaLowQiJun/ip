package toodoo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import toodoo.tasklist.TaskList;

public class ParserTest {

    private Parser parser = new Parser();
    private TaskList taskList = new TaskList();

    @Test
    public void getKeyWordTest() {
        String[] testSplitInput = {"todo", "Read", "book"};

        assertEquals("todo", parser.getKeyWord(testSplitInput));
    }

    @Test
    public void processUserInputTest() {
        String testByeInput = "bye";
        assertEquals("exit", parser.processUserInput(taskList, testByeInput));

        String testToDoInput = "todo Read book";
        assertEquals("Aye aye captain! The following task has been added:\n"
                + "[T][ ] Read book\n"
                + "Now you have 1 tasks in the list.", parser.processUserInput(taskList, testToDoInput));

        String testDeadlineInput = "deadline Submit assignment /by 2024-10-10 23:59";
        assertEquals("Aye aye captain! The following task has been added:\n"
                + "[D][ ] Submit assignment (by: OCTOBER 10 2024 23:59H)\n"
                + "Now you have 2 tasks in the list.", parser.processUserInput(taskList, testDeadlineInput));

        String testEventInput = "event Project meeting /from 2024-09-15 14:00 /to 2024-09-15 15:00";
        assertEquals("Aye aye captain! The following task has been added:\n"
                + "[E][ ] Project meeting (from: SEPTEMBER 15 2024 14:00H to: SEPTEMBER 15 2024 15:00H)\n"
                + "Now you have 3 tasks in the list.", parser.processUserInput(taskList, testEventInput));

        String testListInput = "list";
        assertEquals(taskList.toString(), parser.processUserInput(taskList, testListInput));

        String testMarkInput = "mark 1";
        assertEquals("Good Job! You have completed this task:\n"
                + "[T][X] Read book", parser.processUserInput(taskList, testMarkInput));

        String testUnmarkInput = "unmark 1";
        assertEquals("It's okay! Let's finish it another time!\n"
                + "[T][ ] Read book", parser.processUserInput(taskList, testUnmarkInput));

        String testDeleteInput = "delete 1";
        assertEquals("I have removed this task from the list for you:\n"
                + "[T][ ] Read book\n"
                + "You now have 2 tasks remaining in the list.", parser.processUserInput(taskList, testDeleteInput));

        String testFindInput = "find Project";
        assertEquals("These are what you are looking for right:\n"
                + "1.[E][ ] Project meeting (from: SEPTEMBER 15 2024 14:00H to:"
                + " SEPTEMBER 15 2024 15:00H)\n", parser.processUserInput(taskList, testFindInput));

        String testSortInput = "sort";
        assertEquals("Your tasks have been sorted successfully!", parser.processUserInput(taskList, testSortInput));

        String testForSortedList = "list";
        assertEquals(taskList.toString(), parser.processUserInput(taskList, testForSortedList));

        String testInvalidInput = "hello";
        assertEquals("Hmmmmm I doo not recognise this word: hello",
                parser.processUserInput(taskList, testInvalidInput));

        String testEmptyToDoDescriptionInput = "todo ";
        assertEquals("Have you made a mistake? It would seem that your task's description is empty.",
                parser.processUserInput(taskList, testEmptyToDoDescriptionInput));

        String testEmptyDeadlineDescriptionInput = "deadline /by 2024-10-10 23:59";
        assertEquals("Have you made a mistake? It would seem that your task's description is empty.",
                parser.processUserInput(taskList, testEmptyDeadlineDescriptionInput));

        String testEmptyEventDescriptionInput = "event /from 2024-09-15 14:00 /to 2024-09-15 15:00";
        assertEquals("Have you made a mistake? It would seem that your task's description is empty.",
                parser.processUserInput(taskList, testEmptyEventDescriptionInput));

        String testEmptyDeadlineDeadlineInput = "deadline Submit assignment /by ";
        assertEquals("Oh dear...if your task has noo deadline, maybe it should be a toodoo.",
                parser.processUserInput(taskList, testEmptyDeadlineDeadlineInput));

        String testEmptyEventFromInput = "event Project meeting /to 2024-09-15 15:00";
        assertEquals("When does this event start? Maybe you should include it!",
                parser.processUserInput(taskList, testEmptyEventFromInput));

        String testEmptyEventToInput = "event Project meeting /from 2024-09-15 14:00";
        assertEquals("Goodness me! Does this event never end? Be sure too include the ending time.",
                parser.processUserInput(taskList, testEmptyEventToInput));

        String testEmptyRegexInput = "find";
        assertEquals("How am I going too find it if you don't give me any hints :\"(",
                parser.processUserInput(taskList, testEmptyRegexInput));

        String testEmptyMarkIndexInput = "mark";
        assertEquals("You must have forgotten too provide an integer representing the task's index -_-",
                parser.processUserInput(taskList, testEmptyMarkIndexInput));

        String testEmptyUnmarkIndexInput = "unmark";
        assertEquals("You must have forgotten too provide an integer representing the task's index -_-",
                parser.processUserInput(taskList, testEmptyUnmarkIndexInput));

        String testEmptyDeleteIndexInput = "delete";
        assertEquals("You must have forgotten too provide an integer representing the task's index -_-",
                parser.processUserInput(taskList, testEmptyDeleteIndexInput));

        String testInvalidMarkIndexInput = "mark one";
        assertEquals("Please provide a valid integer for the task number :(",
                parser.processUserInput(taskList, testInvalidMarkIndexInput));

        String testInvalidUnmarkIndexInput = "unmark one";
        assertEquals("Please provide a valid integer for the task number :(",
                parser.processUserInput(taskList, testInvalidUnmarkIndexInput));

        String testInvalidDeleteIndexInput = "delete one";
        assertEquals("Please provide a valid integer for the task number :(",
                parser.processUserInput(taskList, testInvalidDeleteIndexInput));

        String testEventDateTimeConflictInput = "event Project meeting /from 2024-09-15 16:00 /to 2024-09-15 15:00";
        assertEquals("Are you a time traveller...why is your too at a time before your from?",
                parser.processUserInput(taskList, testEventDateTimeConflictInput));

        String testDeadlineInvalidDateTimeFormatInput = "deadline Submit assignment /by 10th October 2024 23:59";
        assertEquals("When specifying a date and time, please use the following format yyyy-MM-dd HH:mm !"
                + " to specify a date that exists",
                parser.processUserInput(taskList, testDeadlineInvalidDateTimeFormatInput));

        String testEventInvalidDateTimeFormatInput = "event Project meeting /from 15th September 2024 14:00"
                + " /to 15th September 2024 15:00";
        assertEquals("When specifying a date and time, please use the following format yyyy-MM-dd HH:mm !"
                + " to specify a date that exists",
                parser.processUserInput(taskList, testEventInvalidDateTimeFormatInput));

        String testToDoAlreadyUnmarkedInput = "unmark 1";
        assertEquals("The task you specified is already marked as not done!",
                parser.processUserInput(taskList, testToDoAlreadyUnmarkedInput));

        String markInput = "mark 1";
        assertEquals("Good Job! You have completed this task:\n"
                + "[E][X] Project meeting (from: SEPTEMBER 15 2024 14:00H to: SEPTEMBER 15 2024 15:00H)",
                parser.processUserInput(taskList, markInput));

        String testToDoAlreadMarkedInput = "mark 1";
        assertEquals("The task you specified is already marked as done!",
                parser.processUserInput(taskList, testToDoAlreadMarkedInput));
    }

}
