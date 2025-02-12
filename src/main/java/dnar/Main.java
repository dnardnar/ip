package dnar;

import javafx.application.Application;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/DNar.txt";
    private DNar dnar = new DNar(DEFAULT_FILE_PATH, new UI(), new Storage(DEFAULT_FILE_PATH));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/images.png"));
    private Image dnarImage = new Image(this.getClass().getResourceAsStream("/images/Do-Not-Attempt.jpg"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDnar(dnar);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dnarText = dnar.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDnarDialog(dnarText, dnarImage)
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "You said: " + input + ". I'm here to assist!";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
