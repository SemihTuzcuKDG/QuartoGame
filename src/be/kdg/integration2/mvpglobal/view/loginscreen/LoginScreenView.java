package be.kdg.integration2.mvpglobal.view.loginscreen;

import be.kdg.integration2.mvpglobal.view.UISettings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LoginScreenView extends BorderPane {
    private TextField usernameField;
    private Button continueButton;
    private Label errorLabel;
    private UISettings uiSettings;

    public LoginScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initializeNodes();
        layoutNodes();
    }
    private void initializeNodes() {
        usernameField = new TextField();
        usernameField.setPromptText("Enter your name");
        continueButton = new Button("Continue");
        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
    }
    private void layoutNodes() {
        VBox layout = new VBox(10,new Label("Welcome to Quarto!"),usernameField,continueButton,errorLabel);
        layout.setAlignment(Pos.CENTER);
        this.setCenter(layout);
    }
    public TextField getUsernameField() {return usernameField;}
    public Button getContinueButton() {return continueButton;}
    public Label getErrorLabel() {return errorLabel;}
}
