package be.kdg.integration2.mvpglobal.model;

import be.kdg.integration2.mvpglobal.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardDAO {

    public List<PlayerStats> fetchLeaderboard() {
        List<PlayerStats> players = new ArrayList<>();

        String sql = "SELECT player_name, games_played, wins, losses, " +
                "(wins * 100.0 / games_played) AS win_percentage, " +
                "(total_moves * 1.0 / games_played) AS average_moves, " +
                "(total_move_duration * 1.0 / total_moves) AS average_move_duration, " +
                "score " +
                "FROM players";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                players.add(new PlayerStats(
                        rs.getString("player_name"),
                        rs.getInt("games_played"),
                        rs.getInt("wins"),
                        rs.getInt("losses"),
                        rs.getDouble("win_percentage"),
                        rs.getDouble("average_moves"),
                        rs.getDouble("average_move_duration") / 1000.0, // to seconds
                        rs.getInt("score")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching leaderboard: " + e.getMessage());
        }

        return players;
    }
}
