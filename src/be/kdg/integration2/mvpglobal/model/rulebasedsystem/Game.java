package be.kdg.integration2.mvpglobal.model.rulebasedsystem;

import be.kdg.integration2.mvpglobal.model.*;


public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;

    public Game(AIDifficulty difficulty) {  // <== IMPORTANT!
        this.board = new Board();
        this.players = new Player[]{
                new HumanPlayer("Player 1"),
                new ComputerPlayer(difficulty)
        };
        this.currentPlayerIndex = 0;
    }

    public void startGame() {
        System.out.println("Starting Quarto Game...");
        while (!board.isWinningMove()) {
            playTurn();
        }
        System.out.println("Game Over! Winner: " + board.getWinner());
    }


    private void playTurn() {
        Player currentPlayer = players[currentPlayerIndex];
        System.out.println(currentPlayer.getName() + "'s turn.");
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }
}

