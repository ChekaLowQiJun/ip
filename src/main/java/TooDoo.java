


import java.io.FileNotFoundException;

public class TooDoo {

    private static final String STORAGE_PATH = "./../storage/TooDooList.txt";
    // private static final String STORAGE_PATH = "./../src/main/storage/TooDooList.txt"; // For testing

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public TooDoo(String filePath) {
        try {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadList());
        ui = new Ui();
        parser = new Parser();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
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
