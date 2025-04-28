package be.kdg.integration2.mvpglobal;

import be.kdg.integration2.mvpglobal.view.leaderboardscreen.LeaderboardPresenter;
import be.kdg.integration2.mvpglobal.view.leaderboardscreen.LeaderboardView;
import be.kdg.integration2.mvpglobal.view.loginscreen.LoginScreenPresenter;
import be.kdg.integration2.mvpglobal.view.loginscreen.LoginScreenView;
import be.kdg.integration2.mvpglobal.view.mainscreen.MainScreenPresenter;
import be.kdg.integration2.mvpglobal.view.mainscreen.MainScreenView;
import be.kdg.integration2.mvpglobal.view.startscreen.*;
import be.kdg.integration2.mvpglobal.model.*;
import be.kdg.integration2.mvpglobal.view.*;
import be.kdg.integration2.mvpglobal.view.statisticsscreen.GameStatisticsPresenter;
import be.kdg.integration2.mvpglobal.view.statisticsscreen.GameStatisticsView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.image.Image;
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
        });




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
    public static void openGameStatisticsStage() {
        GameStatisticsView view = new GameStatisticsView();
        new GameStatisticsPresenter(view);

        Stage statisticsStage = new Stage();
        statisticsStage.setScene(new Scene(view, 600, 400));
        statisticsStage.setTitle("Game Statistics");
        statisticsStage.initModality(Modality.APPLICATION_MODAL);
        statisticsStage.show();
    }
    public static void openLeaderboardScreen() {
        LeaderboardView view = new LeaderboardView();
        new LeaderboardPresenter(view);
        Stage stage = new Stage();
        stage.setTitle("Leaderboard");
        stage.setScene(new Scene(view, 800, 600));
        stage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}