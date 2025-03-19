package be.kdg.integration2.mvpglobal.model;

public class Player {
    private String name;
    public Player() {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Piece choosePiece(){
        return new Piece();
    }

    public Player(String name) {
        this.name = name;
    }

    public boolean isTurn() {
        return false;//not finished
    }
}
