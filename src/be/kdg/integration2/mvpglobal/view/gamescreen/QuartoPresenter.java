package be.kdg.integration2.mvpglobal.view.gamescreen;

import be.kdg.integration2.mvpglobal.model.MVPModel;
import be.kdg.integration2.mvpglobal.model.Piece;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuartoPresenter {
    private final MVPModel model;
    private final QuartoView view;

    public QuartoPresenter(MVPModel model, QuartoView view) {
        this.model = model;
        this.view = view;
        view.setPresenter(this);
        updateView();
    }

    public void handlePieceSelection(Piece piece) {
        if (model.selectPiece(piece)) {
            view.displayMessage("Piece selected! Opponent must place it.");
            view.displaySelectedPiece(piece);
            updateView();
        } else {
            view.displayMessage("Invalid selection! Choose another.");
        }
    }

    public void handleMove(int row, int col) {
        if (model.placePiece(row, col)) {
            updateView();
            if (model.checkWinCondition()) {
                view.displayMessage("Game Over! " + (model.isPlayerOneTurn() ? "Player 2" : "Player 1") + " Wins!");
            } else {
                view.displayMessage((model.isPlayerOneTurn() ? "Player 1" : "Player 2") + ", select a piece!");
            }
        } else {
            view.displayMessage("Invalid move! Try again.");
        }
    }

    public void resetGame() {
        model.resetGame();
        updateView();
        view.displaySelectedPiece(null);
        view.displayMessage("Game reset! Player 1, select a piece.");
    }

    private void updateView() {
        Platform.runLater(() -> {
            view.displayBoard(model.getBoard());
            view.updatePieceSelection(model.getAvailablePieces());
        });
    }
}