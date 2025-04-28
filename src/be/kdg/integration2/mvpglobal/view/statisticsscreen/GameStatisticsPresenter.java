package be.kdg.integration2.mvpglobal.view.statisticsscreen;


import be.kdg.integration2.mvpglobal.model.GameStatisticsDAO;
import be.kdg.integration2.mvpglobal.model.GameStatisticsData;

public class GameStatisticsPresenter {
    private final GameStatisticsDAO dao;
    private final GameStatisticsView view;

    public GameStatisticsPresenter(GameStatisticsView view) {
        this.dao = new GameStatisticsDAO();
        this.view = view;
        loadStatistics();
    }

    private void loadStatistics() {
        GameStatisticsData data = dao.getGameStatistics();
        view.updateStatistics(data);
    }
}