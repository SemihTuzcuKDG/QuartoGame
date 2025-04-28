package be.kdg.integration2.mvpglobal.model;

import be.kdg.integration2.mvpglobal.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameStatisticsDAO {

    public GameStatisticsData getGameStatistics() {
        String sql = "SELECT * FROM game_statistics ORDER BY game_id DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("Game fetched successfully from DB.");
                String winner = rs.getString("winner_name");
                int totalTime = rs.getInt("total_play_time");
                int player1Moves = rs.getInt("player1_moves");
                int player2Moves = rs.getInt("player2_moves");
                double player1Avg = rs.getDouble("player1_avg_move_duration");
                double player2Avg = rs.getDouble("player2_avg_move_duration");
                int player1Score = rs.getInt("player1_score");
                int player2Score = rs.getInt("player2_score");

                return new GameStatisticsData(winner, totalTime, player1Moves, player2Moves, player1Avg, player2Avg, player1Score, player2Score);
            } else {
                System.out.println("No statistics found.");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching game statistics: " + e.getMessage());
        }
        return null;
    }

    public void saveGameStatistics(GameStatisticsData data) {
        String sql = "INSERT INTO game_statistics (winner_name, total_play_time, player1_moves, player2_moves, player1_avg_move_duration, player2_avg_move_duration, player1_score, player2_score) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data.getWinnerName());
            stmt.setInt(2, data.getTotalPlayTimeInSeconds());
            stmt.setInt(3, data.getPlayer1Moves());
            stmt.setInt(4, data.getPlayer2Moves());
            stmt.setDouble(5, data.getPlayer1AvgMoveDuration());
            stmt.setDouble(6, data.getPlayer2AvgMoveDuration());
            stmt.setInt(7, data.getPlayer1Score());
            stmt.setInt(8, data.getPlayer2Score());

            stmt.executeUpdate();
            System.out.println("Game statistics saved successfully.");
        } catch (SQLException e) {
            System.err.println("Error saving game statistics: " + e.getMessage());
        }
    }
}