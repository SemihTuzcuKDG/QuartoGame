package be.kdg.integration2.mvpglobal.view.gamescreen;

import be.kdg.integration2.mvpglobal.model.MVPModel;
import be.kdg.integration2.mvpglobal.model.Piece;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuartoPresenter {
    private MVPModel model;
    private QuartoView view;

    public QuartoPresenter(MVPModel model, QuartoView view) {
        this.model = model;
        this.view = view;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        Button[][] buttons = view.getButtons();

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                int r = row, c = col;
                buttons[row][col].setOnAction(event -> handleMove(r, c));
            }
        }
    }

    private void handleMove(int row, int col) {
        String piece = model.isPlayerOneTurn() ? "P1" : "P2"; // Example piece (can be improved)

        if (model.placePiece(row, col,piece)) {
            view.getButtons()[row][col].setText(piece);
            if (model.checkWinCondition()) {
                view.getStatusLabel().setText("Game Over! " + (model.isPlayerOneTurn() ? "Player 2" : "Player 1") + " Wins!");
                disableButtons();
            } else {
                view.getStatusLabel().setText(model.isPlayerOneTurn() ? "Player 1's Turn" : "Player 2's Turn");
            }
        }
    }

    private void disableButtons() {
        for (Button[] row : view.getButtons()) {
            for (Button button : row) {
                button.setDisable(true);
            }
        }
    }
}