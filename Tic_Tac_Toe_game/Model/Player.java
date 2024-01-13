package lld.Tic_Tac_Toe_game.Model;

public class Player {
    
    private String playerName;
    private Piece playingPieceType;

    public Player(String playerName, Piece playingPieceType){
        this.playerName = playerName;
        this.playingPieceType = playingPieceType;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public Piece getPlayingPieceType() {
        return playingPieceType;
    }
}
