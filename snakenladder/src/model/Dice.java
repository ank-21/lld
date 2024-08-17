package model;

import java.util.Random;

public class Dice {
    private final int diceCount;

    public Dice(int diceCount){
        this.diceCount = diceCount;
    }

    public int rollDice(Player player){
        Random random = new Random();
        int score = random.nextInt(6 * diceCount) + 1;
        System.out.println("Player " + player.getPlayerName() + " gets " + score);
        return score;
    }

    public int getDiceCount() {
        return diceCount;
    }
}
