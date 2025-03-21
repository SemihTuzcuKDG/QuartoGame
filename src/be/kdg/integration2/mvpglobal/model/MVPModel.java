package be.kdg.integration2.mvpglobal.model;

import java.util.ArrayList;
import java.util.List;

public class MVPModel {
    private Piece[][] board;
    private List<Piece> availablePieces;
    private Piece selectedPiece;
    private boolean isPlayerOneTurn;
    private String playerName;


    public MVPModel() {
        resetGame();
    }

    public void resetGame() {
        board = new Piece[4][4];
        availablePieces = new ArrayList<>();
        selectedPiece = null;
        isPlayerOneTurn = true;
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
        if (selectedPiece == null || board[row][col] != null) {
            return false;
        }
        board[row][col] = selectedPiece;
        selectedPiece = null;

        if (checkWinCondition()) {
            return true;
        }

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
        return checkAttributes(board[0][0], board[1][1], board[2][2], board[3][3])
                || checkAttributes(board[0][3], board[1][2], board[2][1], board[3][0]);
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

        boolean sameHeight = pieces[0].hasAttribute(Attribute.TALL) == pieces[1].hasAttribute(Attribute.TALL)
                && pieces[0].hasAttribute(Attribute.TALL) == pieces[2].hasAttribute(Attribute.TALL)
                && pieces[0].hasAttribute(Attribute.TALL) == pieces[3].hasAttribute(Attribute.TALL);

        boolean sameColor = pieces[0].hasAttribute(Attribute.YELLOW) == pieces[1].hasAttribute(Attribute.YELLOW)
                && pieces[0].hasAttribute(Attribute.YELLOW) == pieces[2].hasAttribute(Attribute.YELLOW)
                && pieces[0].hasAttribute(Attribute.YELLOW) == pieces[3].hasAttribute(Attribute.YELLOW);

        boolean sameShape = pieces[0].hasAttribute(Attribute.SQUARE) == pieces[1].hasAttribute(Attribute.SQUARE)
                && pieces[0].hasAttribute(Attribute.SQUARE) == pieces[2].hasAttribute(Attribute.SQUARE)
                && pieces[0].hasAttribute(Attribute.SQUARE) == pieces[3].hasAttribute(Attribute.SQUARE);

        boolean sameTop = pieces[0].hasAttribute(Attribute.SOLID) == pieces[1].hasAttribute(Attribute.SOLID)
                && pieces[0].hasAttribute(Attribute.SOLID) == pieces[2].hasAttribute(Attribute.SOLID)
                && pieces[0].hasAttribute(Attribute.SOLID) == pieces[3].hasAttribute(Attribute.SOLID);

        return sameHeight || sameColor || sameShape || sameTop;
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
}
