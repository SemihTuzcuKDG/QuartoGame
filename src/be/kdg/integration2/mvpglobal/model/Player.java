package be.kdg.integration2.mvpglobal.model;

public class Player {
    private String name;
    public Player(String name) {
        this.name = name;
    }
    public Player() {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Piece choosePiece() {
        return null;
    }
}
