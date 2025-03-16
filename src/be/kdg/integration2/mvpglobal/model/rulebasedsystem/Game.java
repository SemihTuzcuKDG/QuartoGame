package be.kdg.integration2.mvpglobal.model.rulebasedsystem;

import be.kdg.integration2.mvpglobal.model.Board;
import be.kdg.integration2.mvpglobal.model.Piece;
import be.kdg.integration2.mvpglobal.model.Player;


    public class Game {
        private Board board;
        private Player[] players;
        private int currentPlayerIndex;

        public Game() {
            this.board = new Board();
            this.players = new Player[]{new Player("Player 1"), new Player("Player 2")};
            this.currentPlayerIndex = 0;
            }

        public void startGame() {
            System.out.println("Starting Quarto Game...");
            while (!board.isGame0ver()) {
                playTurn();
            }
            System.out.println("Game Over! Winner: " + board.getWinner());
        }

        private void playTurn (){
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println(currentPlayer.getName() + "'s turn.");
            Piece piece = currentPlayer.choosePiece();
            board.placePiece(piece);

            if (board.isWinningMove(piece)) {
                board.setWinner(currentPlayer);
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }
