package be.kdg.integration2.mvpglobal;

import be.kdg.integration2.mvpglobal.view.gamescreen.QuartoPresenter;
import be.kdg.integration2.mvpglobal.view.gamescreen.QuartoView;
import be.kdg.integration2.mvpglobal.view.startscreen.*;
import be.kdg.integration2.mvpglobal.model.*;
import be.kdg.integration2.mvpglobal.view.*;
import javafx.application.Application;
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
        StartScreenView view = new StartScreenView(uiSettings);

        Scene scene = new Scene(view);
        if (uiSettings.styleSheetAvailable()){
            try {
                scene.getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        }
        primaryStage.setScene(scene);
        primaryStage.setHeight((double) uiSettings.getLowestRes() / 4);
        primaryStage.setWidth((double) uiSettings.getLowestRes() / 4);
        primaryStage.setTitle(uiSettings.getApplicationName());
        if (Files.exists(uiSettings.getApplicationIconPath())) {
            try {
                primaryStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
            }
            catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        } else { // do nothing, if ApplicationIcon is not available, program can continue
        }
        StartScreenPresenter presenter = new StartScreenPresenter(model, view, uiSettings);
        presenter.windowsHandler();
        primaryStage.show();
        Button playButton = new Button("Play Quarto");
        playButton.setOnAction(e -> openQuartoStage());

        StackPane root = new StackPane(playButton);
        Scene scenery = new Scene(root, 300, 200);
        primaryStage.setTitle("Primary Stage");
        primaryStage.setScene(scenery);
        primaryStage.show();
    }
    private void openQuartoStage() {
        MVPModel model = new MVPModel();
        QuartoView view = new QuartoView();
        new QuartoPresenter(model, view);

        Stage quartoStage = new Stage();
        Scene quartoScene = new Scene(view.getViewLayout(), 600, 500);
        quartoStage.setTitle("Quarto Game");
        quartoStage.setScene(quartoScene);

        quartoStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}