package be.kdg.integration2.mvpglobal.model;

import java.time.LocalDateTime;

public class Move {
    private String playerName;
    private long duration;
    private LocalDateTime timestamp;

    private Piece piece;
    private int row;
    private int col;

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public long getDuration() { return duration; }
    public void setDuration(long duration) { this.duration = duration; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }
}
