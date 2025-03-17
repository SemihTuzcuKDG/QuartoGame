package be.kdg.integration2.mvpglobal.model;

public class MVPModel {

    GameSession gameSession;
    public MVPModel() {
         this.gameSession = new GameSession();
         // complete the code here
    }
    public GameSession getGameSession() {
        return gameSession;
    }

    public boolean placePiece(int row, int col, String piece) {
        return false;//not finishe
    }

    public boolean checkWinCondition() {
        return false;//not finishe
    }

    public boolean isPlayerOneTurn() {
        return false;//not finishec
    }


    //...

}
