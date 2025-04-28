    package be.kdg.integration2.mvpglobal.view.gamescreen;

    import be.kdg.integration2.mvpglobal.MVPMain;
    import be.kdg.integration2.mvpglobal.model.GameStatisticsDAO;
    import be.kdg.integration2.mvpglobal.model.GameStatisticsData;
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


                    String realWinnerName = model.isPlayerOneTurn() ? "Player 2" : "Player 1";
                    System.out.println("Game saved to DB with winner: " + realWinnerName);
                    int realTotalPlayTimeSeconds = (int) ((System.currentTimeMillis() - model.getGameStartTime()) / 1000);

                    int realPlayer1Moves = model.getPlayer1Moves();
                    int realPlayer2Moves = model.getPlayer2Moves();

                    double realPlayer1AvgMoveTime = model.getPlayer1AvgMoveDuration();
                    double realPlayer2AvgMoveTime = model.getPlayer2AvgMoveDuration();

                    int realPlayer1Score = model.getPlayer1Score();
                    int realPlayer2Score = model.getPlayer2Score();

                    GameStatisticsData data = new GameStatisticsData(
                            realWinnerName,
                            realTotalPlayTimeSeconds,
                            realPlayer1Moves,
                            realPlayer2Moves,
                            realPlayer1AvgMoveTime,
                            realPlayer2AvgMoveTime,
                            realPlayer1Score,
                            realPlayer2Score
                    );

                    GameStatisticsDAO dao = new GameStatisticsDAO();
                    dao.saveGameStatistics(data);

                    MVPMain.openGameStatisticsStage();
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