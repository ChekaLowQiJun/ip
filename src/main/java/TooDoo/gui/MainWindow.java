package toodoo.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import toodoo.TooDoo;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TooDoo tooDoo;

    private Image tooDooImage = new Image(this.getClass().getResourceAsStream("/images/TooDoo.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Guest.png"));

    @FXML
    /**
     * Initializes the main window by binding the scroll pane to the dialog container height.
     */
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the TooDoo instance into the main window.
     *
     * @param tooDoo The TooDoo instance to be used by this window
     */
    public void setTooDoo(TooDoo tooDoo) {
        this.tooDoo = tooDoo;

        dialogContainer.getChildren().add(
                DialogBox.getTooDooDialog(tooDoo.loadList() + "\n \n" + tooDoo.getWelcome(), tooDooImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    /**
     * Handles user input from the text field, generates a response from TooDoo,
     * and displays both the user input and TooDoo's response in the dialog container.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tooDoo.getResponse(input);
        if (response.equals( "exit")) {
            dialogContainer.getChildren().add(
                    DialogBox.getTooDooDialog(tooDoo.saveList() + "\n \n" + tooDoo.getExit(), tooDooImage));
                
            Platform.exit();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTooDooDialog(response, tooDooImage));

        userInput.clear();
    }
}
