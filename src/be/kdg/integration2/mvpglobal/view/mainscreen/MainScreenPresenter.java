package be.kdg.integration2.mvpglobal.view.mainscreen;

import be.kdg.integration2.mvpglobal.MVPMain;
import be.kdg.integration2.mvpglobal.model.*;
import be.kdg.integration2.mvpglobal.view.aboutscreen.*;
import be.kdg.integration2.mvpglobal.view.gamescreen.QuartoPresenter;
import be.kdg.integration2.mvpglobal.view.gamescreen.QuartoView;
import be.kdg.integration2.mvpglobal.view.infoscreen.*;
import be.kdg.integration2.mvpglobal.view.settingsscreen.*;
import be.kdg.integration2.mvpglobal.view.UISettings;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

public class MainScreenPresenter {

    private MVPModel model;
    private MainScreenView view;
    private UISettings uiSettings;

    public MainScreenPresenter(MVPModel model, MainScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updateView();
        EventHandlers();
    }

    private void updateView() {
        String playerName = model.getPlayerName();
        System.out.println("Retrieved Player Name: " + playerName);
        view.updatePlayerName(playerName);
    }

    private void EventHandlers() {

        view.getLeaderboardButton().setOnAction(event -> MVPMain.openLeaderboardScreen());

        view.getTestButton().setOnAction(event -> {
            GameSession gameSession = new GameSession(model.getBoardObject(), model.getPlayerName(), model.getDifficulty());
            gameSession.playAITurn(model.getSelectedPiece());
        });

        //settings
        view.getSettingsItem().setOnAction(event -> {
            SettingsView settingsView = new SettingsView(uiSettings);
            SettingsPresenter presenter = new SettingsPresenter(this.model, settingsView, uiSettings);
            Stage settingsStage = new Stage();
            settingsStage.setTitle("Settings");
            settingsStage.initOwner(view.getScene().getWindow());
            settingsStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(settingsView);
            settingsStage.setScene(scene);
            settingsStage.setTitle(uiSettings.getApplicationName() + " - Settings");
            settingsStage.setX(view.getScene().getWindow().getX() + uiSettings.getResX() / 10);
            settingsStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10);
            if (Files.exists(uiSettings.getApplicationIconPath())) {
                try {
                    settingsStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                } catch (MalformedURLException ex) {}
            }
            settingsStage.showAndWait();
        });

        //load nad save
        view.getLoadItem().setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Data File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Textfiles", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(view.getScene().getWindow());
            if (selectedFile != null) {
                try {
                    List<String> input = Files.readAllLines(Paths.get(selectedFile.toURI()));
                } catch (IOException e) {}
            } else {
                Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                errorWindow.setHeaderText("Problem with the selected input file:");
                errorWindow.setContentText("File is not readable");
                errorWindow.showAndWait();
            }
        });

        view.getSaveItem().setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Data File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Textfiles", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showSaveDialog(view.getScene().getWindow());
            if (selectedFile != null) {
                try (Formatter output = new Formatter(selectedFile)) {
                    output.format("Sample data%n");
                } catch (IOException e) {}
            }
        });

        view.getExitItem().setOnAction(event -> handleCloseEvent(event));

        //info about rules
        view.getAboutItem().setOnAction(event -> {
            AboutScreenView aboutScreenView = new AboutScreenView(uiSettings);
            AboutScreenPresenter aboutScreenPresenter = new AboutScreenPresenter(model, aboutScreenView, uiSettings);
            showModalWindow(aboutScreenView, "About");
        });

        view.getInfoItem().setOnAction(event -> {
            InfoScreenView infoScreenView = new InfoScreenView(uiSettings);
            InfoScreenPresenter infoScreenPresenter = new InfoScreenPresenter(model, infoScreenView, uiSettings);
            showModalWindow(infoScreenView, "Info");
        });

        view.getRulesButton().setOnAction(event -> {
            InfoScreenView infoScreenView = new InfoScreenView(uiSettings);
            InfoScreenPresenter infoScreenPresenter = new InfoScreenPresenter(model, infoScreenView, uiSettings);
            showModalWindow(infoScreenView, "Rules");
        });

        //play button
        view.getStartGameButton().setOnAction(e -> {
            view.getEasyButton().setVisible(true);
            view.getMediumButton().setVisible(true);
            view.getHardButton().setVisible(true);
            view.getStartGameButton().setDisable(true);
        });

        //difficulties
        view.getEasyButton().setOnAction(e -> {
            model.setDifficulty(AIDifficulty.EASY);
            openQuartoStage();
        });

        view.getMediumButton().setOnAction(e -> {
            model.setDifficulty(AIDifficulty.MEDIUM);
            openQuartoStage();
        });

        view.getHardButton().setOnAction(e -> {
            model.setDifficulty(AIDifficulty.HARD);
            openQuartoStage();
        });
    }

    private void openQuartoStage() {
        MVPModel model = new MVPModel();
        model.setDifficulty(this.model.getDifficulty()); // pass the chosen difficulty into the new game

        QuartoView view = new QuartoView();
        new QuartoPresenter(model, view);

        Stage quartoStage = new Stage();
        Scene quartoScene = new Scene(view.getViewLayout(), 650, 650);
        quartoStage.setTitle("Quarto Game");
        quartoStage.setScene(quartoScene);
        quartoStage.show();
    }

    private void showModalWindow(Parent content, String title) {
        Stage stage = new Stage();
        stage.initOwner(view.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setTitle(uiSettings.getApplicationName() + " - " + title);
        stage.showAndWait();
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(event -> handleCloseEvent(event));
    }

    private void handleCloseEvent(Event event) {
        Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        stopWindow.setHeaderText("You're closing the application.");
        stopWindow.setContentText("Are you sure? Unsaved data may be lost.");
        stopWindow.setTitle("WARNING!");
        stopWindow.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        stopWindow.showAndWait();
        if (stopWindow.getResult() == ButtonType.NO) {
            event.consume();
        } else {
            view.getScene().getWindow().hide();
        }
    }
}
