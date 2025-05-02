package be.kdg.integration2.mvpglobal.view.mainscreen;

import be.kdg.integration2.mvpglobal.view.UISettings;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class MainScreenView extends BorderPane  {

    private MenuItem exitMI;
    private MenuItem saveMI;
    private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private Button testButton;
    private UISettings uiSettings;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button startGameButton;
    private Button rulesButton;
    private Label playerNameLabel;
    private Button leaderboardButton;


    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        playerNameLabel = new Label("Welcome"); // default text
        startGameButton = new Button("Start Game");
        rulesButton = new Button("Game Rules");
        leaderboardButton = new Button("Leaderboard");
        easyButton = new Button("Easy");
        mediumButton = new Button("Medium");
        hardButton = new Button("Hard");
        easyButton.setVisible(false);
        mediumButton.setVisible(false);
        hardButton.setVisible(false);
        this.exitMI = new MenuItem("Exit");
        this.saveMI = new MenuItem("Save");
        this.loadMI = new MenuItem("Load");
        this.settingsMI = new MenuItem("Settings");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
        this.testButton = new Button ("Test of Rule Based System");
    }
    public void updatePlayerName(String playerName) {
        playerNameLabel.setText("Welcome," + playerName + "!");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File", null, loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(), exitMI);
        Menu menuHelp = new Menu("Help", null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile, menuHelp);
        setTop(menuBar);
        setBottom(testButton);

        VBox centerBox = new VBox(10, playerNameLabel, startGameButton, easyButton, mediumButton, hardButton, rulesButton, leaderboardButton);
        centerBox.setAlignment(Pos.CENTER);

        setCenter(centerBox);
    }




    public Button getLeaderboardButton() {
        return leaderboardButton;
    }

    public Button getEasyButton() { return easyButton; }
    public Button getMediumButton() { return mediumButton; }
    public Button getHardButton() { return hardButton; }


    MenuItem getExitItem() {return exitMI;}

    MenuItem getSaveItem() {return saveMI;}

    MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}
    Button getTestButton () {return testButton;}


    Button getStartGameButton() { return startGameButton; }
    Button getRulesButton() { return rulesButton; }

}
