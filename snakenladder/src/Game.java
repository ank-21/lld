import model.*;

import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    Board board;
    Dice dice;
    Deque<Player> players = new LinkedList<>();
    Player winner;

    public Game(){
        initializeGame();
    }
    private void initializeGame(){
        setupBoard();
        addDice();
        addPlayers();
        winner = null;
    }

    private void setupBoard(){

        System.out.print("Please enter the size of the board : ");
        Scanner sc = new Scanner(System.in);
        int boardSize = sc.nextInt();

        System.out.print("Please enter the number of ladders : ");
        int ladderCount = sc.nextInt();

        System.out.print("Please enter the number of snakes : ");
        int snakeCount = sc.nextInt();

        board = new Board(boardSize, ladderCount, snakeCount);
    }

    private void addDice(){
        System.out.println("Assuming one dice to play with min 1 and max 6 as values");
        dice = new Dice(1);
    }

    private void addPlayers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the details of the two players playing");

        int totalPlayers = 2, playerIterator = 1;

        while(playerIterator <= totalPlayers){
            System.out.print("Player " + playerIterator + " name : ");
            String playerName = sc.next();
            players.add( new Player(playerName) );
            playerIterator++;
        }
    }

    public Player startGame(){
        Player currPlayer = null;
        while(winner == null){
            currPlayer = findCurrplayer();

            //roll the dice
            int score = dice.rollDice(currPlayer);

            //get the new Position for the current player
            int newPos = currPlayer.getCurrPosition() + score;
            currPlayer.setCurrPosition(newPos);

            //Check for a snake or a ladder
            if(newPos > board.getBoardSize()){
                winner = currPlayer;
            }else{
                Cell newPositionCell = board.getCell(newPos);
                Jumper jumper = newPositionCell.getJumper();
                String jumpBy;
                if(jumper != null){
                    newPos = jumper.getEnd();
                    if(jumper.getStart() < jumper.getEnd()){
                        jumpBy = "Ladder";
                    }else{
                        jumpBy = "Snake";
                    }
                    System.out.println("Player " + currPlayer.getPlayerName() + " gets a " + jumpBy + " and " +
                            "is moving from " + currPlayer.getCurrPosition() + " to " + newPos);
                    currPlayer.setCurrPosition(newPos);
                }
                System.out.println("Player " + currPlayer.getPlayerName() + " current position is " + currPlayer.getCurrPosition());
            }
        }
        return currPlayer;
    }

    private Player findCurrplayer(){
        Player currPlayer = players.removeFirst();
        players.addLast(currPlayer);
        return currPlayer;
    }
}
