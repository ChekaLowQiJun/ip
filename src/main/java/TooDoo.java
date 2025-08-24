import java.util.Scanner;
import java.io.FileNotFoundException;

public class TooDoo {

    private static Scanner userInputScanner = new Scanner(System.in);

    private static final String STORAGE_PATH = "./../storage/TooDooList.txt";
    // private static final String STORAGE_PATH = "./../src/main/storage/TooDooList.txt"; // For testing

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public TooDoo(String filePath) {
        try {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadList());
        ui = new Ui();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        TooDoo toodoo = new TooDoo(STORAGE_PATH);
        toodoo.ui.getWelcome();
        toodoo.ui.processUserInput(toodoo.taskList);
        toodoo.storage.saveList(toodoo.taskList);
        toodoo.ui.getExit();
        userInputScanner.close();

    }
}
