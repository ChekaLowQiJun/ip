package toodoo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import toodoo.tasklist.TaskList;

public class ParserTest {

    Parser parser = new Parser();
    TaskList taskList = new TaskList();

    @Test
    public void getKeyWordTest() {
        String[] testSplitInput = {"todo", "Read", "book"};

        assertEquals(parser.getKeyWord(testSplitInput), "todo");;
    }

    @Test
    public void processUserInputTest() {
        String testByeInput = "bye";
        assertEquals(parser.processUserInput(taskList, testByeInput), "exit");

        String testToDoInput = "todo Read book";
        assertEquals(parser.processUserInput(taskList, testToDoInput),
                "Aye aye captain! The following task has been added:\n"
                + "[T][ ] Read book\n"
                + "Now you have 1 tasks in the list.");

        String testDeadlineInput = "deadline Submit assignment /by 2024-10-10 23:59";
        assertEquals(parser.processUserInput(taskList, testDeadlineInput),
                "Aye aye captain! The following task has been added:\n"
                + "[D][ ] Submit assignment (by: OCTOBER 10 2024 23:59H)\n"
                + "Now you have 2 tasks in the list.");

        String testEventInput = "event Project meeting /from 2024-09-15 14:00 /to 2024-09-15 15:00";
        assertEquals(parser.processUserInput(taskList, testEventInput),
                "Aye aye captain! The following task has been added:\n"
                + "[E][ ] Project meeting (from: SEPTEMBER 15 2024 14:00H to: SEPTEMBER 15 2024 15:00H)\n"
                + "Now you have 3 tasks in the list.");

        String testListInput = "list";
        assertEquals(parser.processUserInput(taskList, testListInput),
                taskList.toString());

        String testMarkInput = "mark 1";
        assertEquals(parser.processUserInput(taskList, testMarkInput),
                "Good Job! You have completed this task:\n"
                + "[T][X] Read book");

        String testUnmarkInput = "unmark 1";
        assertEquals(parser.processUserInput(taskList, testUnmarkInput),
                "It's okay! Let's finish it another time!\n"
                + "[T][ ] Read book");

        String testDeleteInput = "delete 1";
        assertEquals(parser.processUserInput(taskList, testDeleteInput),
                "I have removed this task from the list for you:\n"
                + "[T][ ] Read book\n"
                + "You now have 2 tasks remaining in the list.");

        String testFindInput = "find Project";
        assertEquals(parser.processUserInput(taskList, testFindInput),
                "These are what you are looking for right:\n"
                + "1.[E][ ] Project meeting (from: SEPTEMBER 15 2024 14:00H to: SEPTEMBER 15 2024 15:00H)\n");
        
        String testSortInput = "sort";
        assertEquals(parser.processUserInput(taskList, testSortInput),
                "Your tasks have been sorted successfully!");
        
        String testForSortedList = "list";
        assertEquals(parser.processUserInput(taskList, testForSortedList),
                taskList.toString());

        String testInvalidInput = "hello";
        assertEquals(parser.processUserInput(taskList, testInvalidInput),
                "Hmmmmm I doo not recognise this word: hello");

        String testEmptyToDoDescriptionInput = "todo ";
        assertEquals(parser.processUserInput(taskList, testEmptyToDoDescriptionInput),
                "Have you made a mistake? It would seem that your task's description is empty.");

        String testEmptyDeadlineDescriptionInput = "deadline /by 2024-10-10 23:59";
        assertEquals(parser.processUserInput(taskList, testEmptyDeadlineDescriptionInput),
                "Have you made a mistake? It would seem that your task's description is empty.");
                
        String testEmptyEventDescriptionInput = "event /from 2024-09-15 14:00 /to 2024-09-15 15:00";
        assertEquals(parser.processUserInput(taskList, testEmptyEventDescriptionInput),
                "Have you made a mistake? It would seem that your task's description is empty.");

        String testEmptyDeadlineDeadlineInput = "deadline Submit assignment /by ";
        assertEquals(parser.processUserInput(taskList, testEmptyDeadlineDeadlineInput),
                "Oh dear...if your task has no deadline, maybe it should be a toodoo.");

        String testEmptyEventFromInput = "event Project meetin /to 2024-09-15 15:00";
        assertEquals(parser.processUserInput(taskList, testEmptyEventFromInput),
                "When does this event start? Maybe you should include it!");

        String testEmptyEventToInput = "event Project meeting /from 2024-09-15 14:00";
        assertEquals(parser.processUserInput(taskList, testEmptyEventToInput),
                "Goodness me! Does this event never end? Be sure to include the ending time.");

        String testEmptyRegexInput = "find";
        assertEquals(parser.processUserInput(taskList, testEmptyRegexInput),
                "How am I going to find it if you don't give me any hints :\"(");

        String testEmptyMarkIndexInput = "mark";
        assertEquals(parser.processUserInput(taskList, testEmptyMarkIndexInput),
                "You must have forgotten too provide an integer representing the task's index -_-");

        String testEmptyUnmarkIndexInput = "unmark";
        assertEquals(parser.processUserInput(taskList, testEmptyUnmarkIndexInput),
                "You must have forgotten too provide an integer representing the task's index -_-");

        String testEmptyDeleteIndexInput = "delete";
        assertEquals(parser.processUserInput(taskList, testEmptyDeleteIndexInput),
                "You must have forgotten too provide an integer representing the task's index -_-");

        String testInvalidMarkIndexInput = "mark one";
        assertEquals(parser.processUserInput(taskList, testInvalidMarkIndexInput),
                "Please provide a valid integer for the task number :(");

        String testInvalidUnmarkIndexInput = "unmark one";
        assertEquals(parser.processUserInput(taskList, testInvalidUnmarkIndexInput),
                "Please provide a valid integer for the task number :(");
        
        String testInvalidDeleteIndexInput = "delete one";
        assertEquals(parser.processUserInput(taskList, testInvalidDeleteIndexInput),
                "Please provide a valid integer for the task number :(");
        
        String testEventDateTimeConflictInput = "event Project meeting /from 2024-09-15 16:00 /to 2024-09-15 15:00";
        assertEquals(parser.processUserInput(taskList, testEventDateTimeConflictInput),
                "Are you a time traveller...why is your to at a time before your from?");

        String testDeadlineInvalidDateTimeFormatInput = "deadline Submit assignment /by 10th October 2024 23:59";
        assertEquals(parser.processUserInput(taskList, testDeadlineInvalidDateTimeFormatInput),
                "When specifying a date and time, please use the following format yyyy-MM-dd HH:mm ! to specify a date that exists");

        String testEventInvalidDateTimeFormatInput = "event Project meeting /from 15th September 2024 14:00 /to 15th September 2024 15:00";
        assertEquals(parser.processUserInput(taskList, testEventInvalidDateTimeFormatInput),
                "When specifying a date and time, please use the following format yyyy-MM-dd HH:mm ! to specify a date that exists");

        String testToDoAlreadyUnmarkedInput = "unmark 1";
        assertEquals(parser.processUserInput(taskList, testToDoAlreadyUnmarkedInput),
                "The task you specified is already marked as not done!");

        String MarkInput = "mark 1";
        assertEquals(parser.processUserInput(taskList, MarkInput),
                "Good Job! You have completed this task:\n"
                + "[E][X] Project meeting (from: SEPTEMBER 15 2024 14:00H to: SEPTEMBER 15 2024 15:00H)");

        String testToDoAlreadMarkedInput = "mark 1";
        assertEquals(parser.processUserInput(taskList, testToDoAlreadMarkedInput),
                "The task you specified is already marked as done!");   
    }

}
