package toodoo.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import toodoo.TooDoo;


/**
 * The entry point of the TooDoo JavaFX application.
 * This class sets up the primary stage and loads the main window.
 */
public class Main extends Application {

    private static final String STORAGE_PATH = "./storage/TooDooList.txt";

    private TooDoo tooDoo = new TooDoo(STORAGE_PATH);

    /**
     * Starts the JavaFX application by loading the main window.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTooDoo(tooDoo); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
