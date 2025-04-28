package be.kdg.integration2.mvpglobal.model;

public class PlayerStats {
    private String playerName;
    private int gamesPlayed;
    private int wins;
    private int losses;
    private double winPercentage;
    private double averageMoves;
    private double averageMoveDuration;
    private int totalScore;

    public PlayerStats(String playerName, int gamesPlayed, int wins, int losses, double winPercentage,
                       double averageMoves, double averageMoveDuration, int totalScore) {
        this.playerName = playerName;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.losses = losses;
        this.winPercentage = winPercentage;
        this.averageMoves = averageMoves;
        this.averageMoveDuration = averageMoveDuration;
        this.totalScore = totalScore;
    }


    public String getPlayerName() { return playerName; }
    public int getGamesPlayed() { return gamesPlayed; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public double getWinPercentage() { return winPercentage; }
    public double getAverageMoves() { return averageMoves; }
    public double getAverageMoveDuration() { return averageMoveDuration; }
    public int getTotalScore() { return totalScore; }
}