package be.kdg.integration2.mvpglobal.model;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MVPModel {
    private Piece[][] board;
    private List<Piece> availablePieces;
    private Piece selectedPiece;
    private boolean isPlayerOneTurn;
    private String playerName;
    private List<Move> player1Moves;
    private List<Move> player2Moves;
    private LocalDateTime gameStartTime;
    private int player1Score;
    private int player2Score;

    public MVPModel() {
        resetGame();
    }

    public void resetGame() {
        board = new Piece[4][4];
        availablePieces = new ArrayList<>();
        selectedPiece = null;
        isPlayerOneTurn = true;
        player1Moves = new ArrayList<>();
        player2Moves = new ArrayList<>();
        gameStartTime = LocalDateTime.now();
        player1Score = 0;
        player2Score = 0;
        generatePieces();
    }

    private void generatePieces() {
        availablePieces.clear();
        for (Attribute height : new Attribute[]{Attribute.TALL, Attribute.SHORT}) {
            for (Attribute color : new Attribute[]{Attribute.YELLOW, Attribute.BROWN}) {
                for (Attribute shape : new Attribute[]{Attribute.SQUARE, Attribute.CIRCULAR}) {
                    for (Attribute top : new Attribute[]{Attribute.SOLID, Attribute.HOLLOW}) {
                        availablePieces.add(new Piece(height, color, shape, top));
                    }
                }
            }
        }
    }

    public boolean selectPiece(Piece piece) {
        if (availablePieces.contains(piece) && selectedPiece == null) {
            selectedPiece = piece;
            availablePieces.remove(piece);
            return true;
        }
        return false;
    }

    public boolean placePiece(int row, int col) {
        if (selectedPiece == null || board[row][col] != null) return false;

        board[row][col] = selectedPiece;

        Move move = new Move();
        move.setPlayerName(isPlayerOneTurn ? playerName : "AI");
        move.setRow(row);
        move.setCol(col);
        move.setTimestamp(LocalDateTime.now());
        move.setStartTime(LocalDateTime.now()); // optional: set earlier if needed
        move.setEndTime(LocalDateTime.now());
        move.computeDuration();

        if (isPlayerOneTurn) {
            player1Moves.add(move);
        } else {
            player2Moves.add(move);
        }

        selectedPiece = null;

        if (checkWinCondition()) return true;

        isPlayerOneTurn = !isPlayerOneTurn;
        return true;
    }

    public boolean checkWinCondition() {
        return checkLines() || checkSquare();
    }

    private boolean checkLines() {
        for (int i = 0; i < 4; i++) {
            if (checkAttributes(board[i][0], board[i][1], board[i][2], board[i][3])) return true;
            if (checkAttributes(board[0][i], board[1][i], board[2][i], board[3][i])) return true;
        }
        return checkAttributes(board[0][0], board[1][1], board[2][2], board[3][3]) ||
                checkAttributes(board[0][3], board[1][2], board[2][1], board[3][0]);
    }

    private boolean checkSquare() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (checkAttributes(board[row][col], board[row+1][col], board[row][col+1], board[row+1][col+1]))
                    return true;
            }
        }
        return false;
    }

    private boolean checkAttributes(Piece... pieces) {
        if (piecesContainNull(pieces)) return false;

        boolean sameHeight = allMatch(pieces, Attribute.TALL);
        boolean sameColor = allMatch(pieces, Attribute.YELLOW);
        boolean sameShape = allMatch(pieces, Attribute.SQUARE);
        boolean sameTop = allMatch(pieces, Attribute.SOLID);

        return sameHeight || sameColor || sameShape || sameTop;
    }

    private boolean allMatch(Piece[] pieces, Attribute attr) {
        boolean first = pieces[0].hasAttribute(attr);
        for (int i = 1; i < pieces.length; i++) {
            if (pieces[i].hasAttribute(attr) != first) return false;
        }
        return true;
    }

    private boolean piecesContainNull(Piece... pieces) {
        for (Piece p : pieces) {
            if (p == null) return true;
        }
        return false;
    }

    public boolean isPlayerOneTurn() {
        return isPlayerOneTurn;
    }

    public List<Piece> getAvailablePieces() {
        return availablePieces;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayer1Moves() {
        return player1Moves.size();
    }

    public int getPlayer2Moves() {
        return player2Moves.size();
    }

    public double getPlayer1AvgMoveDuration() {
        if (player1Moves.isEmpty()) return 0;
        return player1Moves.stream().mapToLong(Move::getDuration).average().orElse(0) / 1000.0;
    }

    public double getPlayer2AvgMoveDuration() {
        if (player2Moves.isEmpty()) return 0;
        return player2Moves.stream().mapToLong(Move::getDuration).average().orElse(0) / 1000.0;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public long getTotalPlayTimeSeconds() {
        return Duration.between(gameStartTime, LocalDateTime.now()).getSeconds();
    }
}

