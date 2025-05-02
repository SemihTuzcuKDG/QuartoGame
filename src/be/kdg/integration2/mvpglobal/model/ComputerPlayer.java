package be.kdg.integration2.mvpglobal.model;

import be.kdg.integration2.mvpglobal.model.rulebasedsystem.InferenceEngine;

public class ComputerPlayer extends Player {

    private final InferenceEngine inference;

    public ComputerPlayer(AIDifficulty difficulty) {
        super("AI " + difficulty.name());
        this.inference = new InferenceEngine();
    }

    public Move getMove(Board board) {
        Move move = new Move();
        inference.determineFacts(board);
        inference.applyRules(board, move);
        return move;
    }

    public Piece selectPieceForOpponent(Board board) {
        for (Piece piece : board.getAvailablePieces()) {
            return piece; // Just returns first available for now
        }
        return null;
    }
}
