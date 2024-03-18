package model;

public class Player {
    private String playerName;
    private int currPosition;

    public Player(String playerName){
        this.playerName = playerName;
        this.currPosition = 0;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }

    public String getPlayerName() {
        return playerName;
    }
}
