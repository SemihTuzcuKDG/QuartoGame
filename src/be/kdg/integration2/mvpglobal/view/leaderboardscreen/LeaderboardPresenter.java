package be.kdg.integration2.mvpglobal.view.leaderboardscreen;

import be.kdg.integration2.mvpglobal.model.LeaderboardDAO;
import be.kdg.integration2.mvpglobal.model.PlayerStats;

import java.util.List;
public class LeaderboardPresenter {
    private LeaderboardDAO dao;
    private LeaderboardView view;

    public LeaderboardPresenter(LeaderboardView view) {
        this.dao = new LeaderboardDAO();
        this.view = view;
        loadLeaderboard();
    }

    private void loadLeaderboard() {
        List<PlayerStats> playerStats = dao.fetchLeaderboard();
        view.setLeaderboardData(playerStats);
    }
}