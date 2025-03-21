package be.kdg.integration2.mvpglobal;

import be.kdg.integration2.mvpglobal.view.gamescreen.QuartoPresenter;
import be.kdg.integration2.mvpglobal.view.gamescreen.QuartoView;
import be.kdg.integration2.mvpglobal.view.loginscreen.LoginScreenPresenter;
import be.kdg.integration2.mvpglobal.view.loginscreen.LoginScreenView;
import be.kdg.integration2.mvpglobal.view.mainscreen.MainScreenPresenter;
import be.kdg.integration2.mvpglobal.view.mainscreen.MainScreenView;
import be.kdg.integration2.mvpglobal.view.startscreen.*;
import be.kdg.integration2.mvpglobal.model.*;
import be.kdg.integration2.mvpglobal.view.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import java.net.MalformedURLException;
import java.nio.file.Files;

public class MVPMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        UISettings uiSettings = new UISettings();
        MVPModel model = new MVPModel();

        StartScreenView startView = new StartScreenView(uiSettings);
        Scene startScene = new Scene(startView, 400, 300);
        primaryStage.setTitle("Quarto - Start Screen");
        primaryStage.setScene(startScene);
        primaryStage.show();


        StartScreenPresenter startPresenter = new StartScreenPresenter(model,startView,uiSettings);


        // Automatically Transition to LoginScreen after a short delay
        // new Thread(() -> {
        //   try {
        //      Thread.sleep(2000);
        //   } catch (InterruptedException e) {
        //      e.printStackTrace();
        //   }

        //  javafx.application.Platform.runLater(() -> {
        LoginScreenView loginView = new LoginScreenView(uiSettings);
        LoginScreenPresenter loginPresenter = new LoginScreenPresenter(model, loginView, uiSettings);

        Scene loginScene = new Scene(loginView, 400, 300);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Quarto - Login Screen");


        //Handle transition from loginScreen to MainScreen
        loginPresenter.setOnLoginSuccess(() -> {
            MainScreenView mainView = new MainScreenView(uiSettings);
            MainScreenPresenter mainPresenter = new MainScreenPresenter(model, mainView, uiSettings);

            Scene mainScene = new Scene(mainView, 400, 300);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Quarto - Main Screen");

            Platform.runLater(primaryStage::sizeToScene);// until the scene is fully initialized

            // mainScene.setRoot(mainView);
            //primaryStage.setTitle("Quarto - Main Screen");
        });


        // });

        // }).start();


        if (uiSettings.styleSheetAvailable()) {
            try {
                startScene.getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        }

        primaryStage.setHeight((double) uiSettings.getLowestRes() / 4);
        primaryStage.setWidth((double) uiSettings.getLowestRes() / 4);
        primaryStage.setTitle(uiSettings.getApplicationName());

        if (Files.exists(uiSettings.getApplicationIconPath())) {
            try {
                primaryStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        } else { // do nothing, if ApplicationIcon is not available, program can continue
        }


    }
    public static void main (String[] args){
        launch(args);
    }
}