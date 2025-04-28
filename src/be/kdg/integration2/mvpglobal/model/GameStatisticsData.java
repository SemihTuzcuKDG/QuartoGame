package be.kdg.integration2.mvpglobal.model;


public class GameStatisticsData {
    private String winnerName;
    private int totalPlayTimeInSeconds;
    private int player1Moves;
    private int player2Moves;
    private double player1AvgMoveDuration;
    private double player2AvgMoveDuration;
    private int player1Score;
    private int player2Score;


    public GameStatisticsData(String winnerName, int totalPlayTimeInSeconds, int player1Moves, int player2Moves,
                              double player1AvgMoveDuration, double player2AvgMoveDuration,
                              int player1Score, int player2Score) {
        this.winnerName = winnerName;
        this.totalPlayTimeInSeconds = totalPlayTimeInSeconds;
        this.player1Moves = player1Moves;
        this.player2Moves = player2Moves;
        this.player1AvgMoveDuration = player1AvgMoveDuration;
        this.player2AvgMoveDuration = player2AvgMoveDuration;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }


    public String getWinnerName() { return winnerName; }
    public int getTotalPlayTimeInSeconds() { return totalPlayTimeInSeconds; }
    public int getPlayer1Moves() { return player1Moves; }
    public int getPlayer2Moves() { return player2Moves; }
    public double getPlayer1AvgMoveDuration() { return player1AvgMoveDuration; }
    public double getPlayer2AvgMoveDuration() { return player2AvgMoveDuration; }
    public int getPlayer1Score() { return player1Score; }
    public int getPlayer2Score() { return player2Score; }
}