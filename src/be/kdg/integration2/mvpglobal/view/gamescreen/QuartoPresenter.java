package be.kdg.integration2.mvpglobal.view.gamescreen;

import be.kdg.integration2.mvpglobal.MVPMain;
import be.kdg.integration2.mvpglobal.model.*;
import javafx.application.Platform;

public class QuartoPresenter {
    private final MVPModel model;
    private final QuartoView view;
    private final GameSession gameSession;

    public QuartoPresenter(MVPModel model, QuartoView view) {
        this.model = model;
        this.view = view;
        view.setPresenter(this);
        updateView();
        gameSession = new GameSession(model.getBoardObject(), model.getPlayerName(), model.getDifficulty());
    }

    public void handlePieceSelection(Piece piece) {
        if (model.selectPiece(piece)) {
            view.displayMessage("Piece selected! Opponent must place it.");
            view.displaySelectedPiece(piece);
            updateView();

            // ✅ Automatically let the AI place the piece and continue the game
            if (!model.isPlayerOneTurn()) {
                model.getBoardObject().setAvailablePieces(model.getAvailablePieces());
                System.out.println("Available pieces: " + model.getBoard().getAvailablePieces().size());

                playAITurn();
                System.out.println("AI placing piece: " + move.getPiece());
                System.out.println("Row: " + move.getRow() + ", Col: " + move.getCol());
            }

        } else {
            view.displayMessage("Invalid selection! Choose another.");
        }
    }

    private void playAITurn() {
        // Give board current available pieces (this is critical!)
        model.getBoardObject().setAvailablePieces(model.getAvailablePieces());

        // AI plays turn
        GameSession gameSession = new GameSession(model.getBoardObject(), model.getPlayerName(), model.getDifficulty());

        // Play the move with the selected piece
        gameSession.playAITurn(model.getSelectedPiece());

        // Update model board with AI move result (optional if board reference is the same)

        updateView();

        // Check if AI won
        if (model.getBoardObject().isWinningMove()) {
            view.displayMessage("Game Over! AI wins!");
            return;
        }

        // If not, show player it's their turn
        view.displayMessage("Your turn! Place the given piece and select a new piece for AI.");
    }

    public void handleMove(int row, int col) {
        view.displayMessage("You don't need to place manually! AI places automatically.");
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

