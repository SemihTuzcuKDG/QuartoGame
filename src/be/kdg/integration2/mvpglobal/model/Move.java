package be.kdg.integration2.mvpglobal.model;

import java.time.LocalDateTime;
import java.time.Duration;

public class Move {
    private String playerName;
    private int row;
    private int col;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long duration; // milliseconds
    private LocalDateTime timestamp;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void computeDuration() {
        if (startTime != null && endTime != null) {
            this.duration = Duration.between(startTime, endTime).toMillis();
        }
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
