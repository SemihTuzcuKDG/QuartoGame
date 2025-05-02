package be.kdg.integration2.mvpglobal.model;

public abstract class Player {
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

