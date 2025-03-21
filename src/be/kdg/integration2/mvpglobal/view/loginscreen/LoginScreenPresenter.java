package be.kdg.integration2.mvpglobal.view.loginscreen;

import be.kdg.integration2.mvpglobal.model.MVPModel;
import be.kdg.integration2.mvpglobal.view.UISettings;
import be.kdg.integration2.mvpglobal.view.gamescreen.QuartoPresenter;
import be.kdg.integration2.mvpglobal.view.gamescreen.QuartoView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScreenPresenter {
    private MVPModel model;
    private LoginScreenView view;
    private UISettings uiSettings;
    private Runnable onLoginSuccess;

    public LoginScreenPresenter(MVPModel model, LoginScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        EventHandlers();
    }
    private void EventHandlers() {
        view.getContinueButton().setOnAction(event -> {
            String playerName = view.getUsernameField().getText().trim();
            if (playerName.isEmpty()) {
                view.getErrorLabel().setText("Name cannot be empty");
                return;
            }
            // store player name in the model
            model.setPlayerName(playerName);

            if (onLoginSuccess != null){
                onLoginSuccess.run(); //
            }

            //to transition to Main Screen
           // MainScreenView mainScreenView = new MainScreenView(uiSettings);
           // MainScreenPresenter mainScreenPresenter = new MainScreenPresenter(model, mainScreenView, uiSettings);

          // Stage stage = (Stage)  view.getScene().getWindow();
          //  Scene mainScene = new Scene( mainScreenView,600,400);
         //   stage.setScene(mainScene);
         //   stage.setTitle("Quarto - Main Screen");
        });
    }

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }
}
