package TooDoo;
import java.io.FileNotFoundException;

import TooDoo.parser.Parser;
import TooDoo.storage.Storage;
import TooDoo.tasklist.TaskList;
import TooDoo.ui.Ui;

public class TooDoo {

    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String STORAGE_PATH = "./src/main/storage/TooDooList.txt";
    // private static final String STORAGE_PATH = "./../src/main/storage/TooDooList.txt"; // For testing

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public TooDoo(String filePath) {
        try {
            ui = new Ui();
            parser = new Parser();
            storage = new Storage(filePath);
            taskList = new TaskList(storage.loadList());
        } catch (FileNotFoundException e) {
            System.out.println(HORIZONTAL_LINE + "It would seem that you have no existing task list! Starting a blank one for you now :) \n"
                    + HORIZONTAL_LINE);
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.getWelcome();
        parser.processUserInput(this.taskList);
        storage.saveList(this.taskList);
        ui.getExit();
    }

    public static void main(String[] args) {
        new TooDoo(STORAGE_PATH).run();
    }
}
