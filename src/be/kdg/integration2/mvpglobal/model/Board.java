package be.kdg.integration2.mvpglobal.model;

public class Board  {
    private Piece[] [] grid;
    private Player winner;
    public Board() {
            this.grid = new Piece[4] [4];
        }
        public boolean placePiece(Piece piece){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (grid[i][j] == null) {
                        grid[i][j] = piece;
                        return true;
                    }
                }
            }
                return false;
        }

        public boolean isWinningMove (Piece piece){
            return false;}

            public boolean isGame0ver() {
                return winner != null;
            }
            public void setWinner(Player player) {
                this.winner = player;
            }

            public String getWinner() {
            return winner != null ? winner.getName() : "No winner";
        }

    public Piece[][] getGrid() {
        return grid;
    }

    public boolean endMoveAIPossible() {
        return false;
    }
    public boolean endMovePlayerPossible() {
        return false;
    }
    public boolean otherFactPossible() { return false;}
    public boolean endWinningPositionAIPossible() {
        return false;
    }
    public boolean endWinningPositionPlayerPossible() {
        return false;
    }
    public void determineBlockEndMove (Move move) {
        // change attributes of move
    }
    public void determineBlockWinningPositionMove (Move move) {
        // change attributes of move
    }
    public void determineEndMove (Move move) {
        // change attributes of move
    }
    public void determineGoodMove (Move move) {
        // change attributes of move
    }
    public void determineRandomMove (Move move) {
        // change attributes of move
    }
    public void determineWinningPositionMove (Move move) {
        // change attributes of move
    }
}
