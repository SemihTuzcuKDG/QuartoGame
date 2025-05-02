package be.kdg.integration2.mvpglobal.model;

import be.kdg.integration2.mvpglobal.model.rulebasedsystem.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(AIDifficulty.MEDIUM);
        game.startGame();
    }
}
