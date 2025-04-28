package be.kdg.integration2.mvpglobal.model;

import java.time.LocalDateTime;

public class Move {
    private String playerName;
    private long duration;
    private LocalDateTime timestamp;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
    //...
}
