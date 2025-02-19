package dnar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow extends AnchorPane implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DNar dnar;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/images.png"));
    private Image dnarImage = new Image(this.getClass().getResourceAsStream("/images/Do-Not-Attempt.jpg"));
    private UI ui; // Add a UI instance

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDnar(DNar d) {
        dnar = d;
        this.ui = new UI(dialogContainer, userImage, dnarImage); // Initialize UI with dialogContainer
        ui.greet(); // Display initial greeting
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage)); // Display user input

        try {
            Parser.parse(input, dnar.getTaskList(), ui, dnar.getStorage()); // Pass TaskList and Storage
        } catch (Exception e) {
            ui.showError("An unexpected error occurred: " + e.getMessage());
        }

        userInput.clear();
    }
}
