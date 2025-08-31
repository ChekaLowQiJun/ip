package toodoo;

import java.io.FileNotFoundException;

import toodoo.exceptions.StorageFormatException;
import toodoo.parser.Parser;
import toodoo.storage.Storage;
import toodoo.tasklist.TaskList;
import toodoo.ui.Ui;

/**
 * The main entry point of the TooDoo chatbot application.
 */
public class TooDoo {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private String filePath;

    /**
     * Constructs the TooDoo chatbot by creating the Ui, Parser, Storage and TaskList object. 
     * It also checks if the storage .txt file exists.
     * 
     * @param filePath The path to the storage .txt file.
     */
    public TooDoo(String filePath) {
        ui = new Ui();
        parser = new Parser();
        this.filePath = filePath;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return parser.processUserInput(taskList, input);
    }

    public String getWelcome() {
        return ui.getWelcome();
    }

    public String getExit() {
        return ui.getExit();
    }

    public String saveList() {
        return storage.saveList(taskList);
    }

    public String loadList() {
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.loadList());
            return "Your task list from a previous run has been successfully loaded!";
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
            return "It would seem that you have no existing task list! Starting a blank one for you now :)";
        } catch (StorageFormatException e) {
            taskList = new TaskList();
            return e.getMessage();
        }
    }

}
