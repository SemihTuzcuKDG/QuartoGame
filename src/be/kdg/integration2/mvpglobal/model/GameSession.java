package be.kdg.integration2.mvpglobal.model;

public class GameSession {

    private final Board board;
    private final ComputerPlayer ai;
    private final HumanPlayer human;
    private final AIDifficulty difficulty;

    public GameSession(Board board, String playerName, AIDifficulty difficulty) {
        this.board = board;
        this.human = new HumanPlayer(playerName);
        this.difficulty = difficulty;
        this.ai = new ComputerPlayer(difficulty);
    }

    public void playAITurn(Piece pieceGivenByPlayer) {
        board.placePiece(pieceGivenByPlayer);

        if (board.isWinningMove()) {
            board.setWinner(ai);
            return;
        }

        Move move = ai.getMove(board);
        board.placePiece(move.getPiece());

        if (board.isWinningMove()) {
            board.setWinner(ai);
            return;
        }

        Piece pieceForPlayer = ai.selectPieceForOpponent(board);
        board.setSelectedPiece(pieceForPlayer);
    }
}
