package be.kdg.integration2.mvpglobal.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Piece[][] grid;
    private Player winner;
    private List<Piece> availablePieces;

    public Board() {
        this.grid = new Piece[4][4];
        this.availablePieces = new ArrayList<>();
    }

    public boolean placePiece(int row, int col, Piece piece) {
        if (grid[row][col] == null) {
            grid[row][col] = piece;
            return true;
        }
        return false;
    }

    public Piece[][] getGrid() {
        return grid;
    }

    public void setWinner(Player player) {
        this.winner = player;
    }

    public String getWinner() {
        return winner != null ? winner.getName() : "No winner";
    }

    public boolean isWinningMove() {
        // Simple check if any row, column or diagonal has all same attribute
        return checkLines() || checkSquare();
    }

    private boolean checkLines() {
        for (int i = 0; i < 4; i++) {
            if (checkAttributes(grid[i][0], grid[i][1], grid[i][2], grid[i][3])) return true;
            if (checkAttributes(grid[0][i], grid[1][i], grid[2][i], grid[3][i])) return true;
        }
        return checkAttributes(grid[0][0], grid[1][1], grid[2][2], grid[3][3])
                || checkAttributes(grid[0][3], grid[1][2], grid[2][1], grid[3][0]);
    }

    private boolean checkSquare() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (checkAttributes(grid[row][col], grid[row+1][col], grid[row][col+1], grid[row+1][col+1]))
                    return true;
            }
        }
        return false;
    }

    private boolean checkAttributes(Piece... pieces) {
        if (piecesContainNull(pieces)) return false;

        boolean sameHeight = sameAttr(pieces, Attribute.TALL);
        boolean sameColor = sameAttr(pieces, Attribute.YELLOW);
        boolean sameShape = sameAttr(pieces, Attribute.SQUARE);
        boolean sameTop = sameAttr(pieces, Attribute.SOLID);

        return sameHeight || sameColor || sameShape || sameTop;
    }

    private boolean sameAttr(Piece[] pieces, Attribute attr) {
        boolean base = pieces[0].hasAttribute(attr);
        for (Piece p : pieces) {
            if (p.hasAttribute(attr) != base) return false;
        }
        return true;
    }

    private boolean piecesContainNull(Piece... pieces) {
        for (Piece p : pieces) {
            if (p == null) return true;
        }
        return false;
    }

    public void setAvailablePieces(List<Piece> availablePieces) {
        this.availablePieces = availablePieces;
    }

    public List<Piece> getAvailablePieces() {
        return availablePieces;
    }

    // Stub for rule-based facts
    public boolean endMoveAIPossible() { return false; }
    public boolean endMovePlayerPossible() { return false; }
    public boolean otherFactPossible() { return false; }
    public boolean endWinningPositionAIPossible() { return false; }
    public boolean endWinningPositionPlayerPossible() { return false; }

    // Stubs for rule actions
    public void determineBlockEndMove(Move move) {}
    public void determineBlockWinningPositionMove(Move move) {}
    public void determineEndMove(Move move) {}
    public void determineGoodMove(Move move) {}
    public void determineRandomMove(Move move) {}
    public void determineWinningPositionMove(Move move) {}
}
