package be.kdg.integration2.mvpglobal.model;

import java.util.ArrayList;
import java.util.List;

public class MVPModel {
    private Piece[][] board;
    private List<Piece> availablePieces;
    private Piece selectedPiece;
    private boolean isPlayerOneTurn;
    private String playerName;
    private long gameStartTime;
    private AIDifficulty difficulty;

    public MVPModel() {
        resetGame();
    }

    public void resetGame() {
        board = new Piece[4][4];
        availablePieces = new ArrayList<>();
        selectedPiece = null;
        isPlayerOneTurn = true;
        gameStartTime = System.currentTimeMillis();
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
        selectedPiece = null;
        isPlayerOneTurn = !isPlayerOneTurn;
        return true;
    }

    public boolean checkWinCondition() {
        return false;  // To be implemented
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

    public long getGameStartTime() {
        return gameStartTime;
    }

    public AIDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(AIDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Board getBoardObject() {
        return new Board();
    }
}
