package be.kdg.integration2.mvpglobal.view.statisticsscreen;


import be.kdg.integration2.mvpglobal.model.GameStatisticsData;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameStatisticsView extends VBox {
    private Label winnerLabel;
    private Label totalTimeLabel;
    private Label player1MovesLabel;
    private Label player2MovesLabel;
    private Label player1AvgLabel;
    private Label player2AvgLabel;
    private Label player1ScoreLabel;
    private Label player2ScoreLabel;

    public GameStatisticsView() {
        setPadding(new Insets(20));
        setSpacing(10);

        winnerLabel = new Label();
        totalTimeLabel = new Label();
        player1MovesLabel = new Label();
        player2MovesLabel = new Label();
        player1AvgLabel = new Label();
        player2AvgLabel = new Label();
        player1ScoreLabel = new Label();
        player2ScoreLabel = new Label();

        getChildren().addAll(
                winnerLabel,
                totalTimeLabel,
                player1MovesLabel,
                player2MovesLabel,
                player1AvgLabel,
                player2AvgLabel,
                player1ScoreLabel,
                player2ScoreLabel
        );
    }

    public void updateStatistics(GameStatisticsData data) {
        if (data != null) {
            winnerLabel.setText("Winner: " + data.getWinnerName());
            totalTimeLabel.setText("Total Play Time: " + data.getTotalPlayTimeInSeconds() + " seconds");
            player1MovesLabel.setText("Player 1 Moves: " + data.getPlayer1Moves());
            player2MovesLabel.setText("Player 2 Moves: " + data.getPlayer2Moves());
            player1AvgLabel.setText("Player 1 Avg Move Duration: " + String.format("%.2f", data.getPlayer1AvgMoveDuration()) + " seconds");
            player2AvgLabel.setText("Player 2 Avg Move Duration: " + String.format("%.2f", data.getPlayer2AvgMoveDuration()) + " seconds");
            player1ScoreLabel.setText("Player 1 Score: " + data.getPlayer1Score());
            player2ScoreLabel.setText("Player 2 Score: " + data.getPlayer2Score());
        }
    }
}