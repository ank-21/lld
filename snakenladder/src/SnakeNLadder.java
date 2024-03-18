import model.Player;

public class SnakeNLadder {
    public static void main(String[] args){
        System.out.println("Snake and Ladder game started");
        Game game = new Game();
        Player winner = game.startGame();

        if(winner != null){
            System.out.println("The winner of the snake and ladder game is " + winner.getPlayerName());
        }else{
            System.out.println("The snake and ladder game is draw!");
        }
    }
}
