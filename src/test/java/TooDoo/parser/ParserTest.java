package toodoo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import toodoo.tasklist.TaskList;

public class ParserTest {

    private static Parser Parser = new Parser();
    
    @Test
    public void getKeyWordTest() {
        assertEquals("find", Parser.getKeyWord(new String[]{"find"})); // Array of strings with one element
        assertEquals("mark", Parser.getKeyWord(new String[]{"mark", "1"})); // Array of string with two elements
    }

    @Test
    public void processUserInputTest() {

        TaskList taskList = new TaskList();

        assertEquals("", Parser.processUserInput(taskList, "todo test"));
    }
}
