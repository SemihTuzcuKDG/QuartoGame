package be.kdg.integration2.mvpglobal.view.gamescreen;

import be.kdg.integration2.mvpglobal.model.MVPModel;
import be.kdg.integration2.mvpglobal.model.Piece;
import be.kdg.integration2.mvpglobal.model.Board;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class QuartoView extends GridPane {
    private GridPane boardGrid;
    private Label statusLabel;
    private Button[][] buttons;

    public QuartoView() {
        boardGrid = new GridPane();
        statusLabel = new Label("Player 1's Turn");
        buttons = new Button[4][4];

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                Button button = new Button();
                button.setMinSize(80, 80);
                buttons[row][col] = button;
                boardGrid.add(button, col, row);
            }
        }
    }

    public GridPane getBoardGrid() {
        return boardGrid;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public VBox getViewLayout() {
        VBox layout = new VBox(10, statusLabel, boardGrid);
        layout.setAlignment(Pos.CENTER);
        return layout;
    }
}

