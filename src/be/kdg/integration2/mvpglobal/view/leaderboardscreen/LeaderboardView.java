package be.kdg.integration2.mvpglobal.view.leaderboardscreen;

import be.kdg.integration2.mvpglobal.model.PlayerStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

public class LeaderboardView extends VBox {
    private TableView<PlayerStats> tableView;

    public LeaderboardView() {
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        tableView = new TableView<>();

        TableColumn<PlayerStats, String> nameCol = new TableColumn<>("Player Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<PlayerStats, Integer> gamesCol = new TableColumn<>("Games Played");
        gamesCol.setCellValueFactory(new PropertyValueFactory<>("gamesPlayed"));

        TableColumn<PlayerStats, Integer> winsCol = new TableColumn<>("Wins");
        winsCol.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<PlayerStats, Integer> lossesCol = new TableColumn<>("Losses");
        lossesCol.setCellValueFactory(new PropertyValueFactory<>("losses"));

        TableColumn<PlayerStats, Double> winPercCol = new TableColumn<>("Win %");
        winPercCol.setCellValueFactory(new PropertyValueFactory<>("winPercentage"));

        TableColumn<PlayerStats, Double> avgMovesCol = new TableColumn<>("Avg Moves");
        avgMovesCol.setCellValueFactory(new PropertyValueFactory<>("averageMoves"));

        TableColumn<PlayerStats, Double> avgMoveTimeCol = new TableColumn<>("Avg Move Duration (s)");
        avgMoveTimeCol.setCellValueFactory(new PropertyValueFactory<>("averageMoveDuration"));

        TableColumn<PlayerStats, Integer> scoreCol = new TableColumn<>("Total Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        tableView.getColumns().addAll(nameCol, gamesCol, winsCol, lossesCol, winPercCol, avgMovesCol, avgMoveTimeCol, scoreCol);
        this.getChildren().add(tableView);
    }

    public void setLeaderboardData(List<PlayerStats> playerStatsList) {
        ObservableList<PlayerStats> observableList = FXCollections.observableArrayList(playerStatsList);
        tableView.setItems(observableList);
    }
}
