package lld.Tic_Tac_Toe_game;

public class Main {
    
    public static void main(String[] args) {
        Game game = new Game();
        game.initializeGame();

        String winner = game.startGame();
        if (!"tie".equals(winner))
            System.out.println("The winner of the game is " + winner);
        else
            System.out.println("The match is tied!");
    }
}
