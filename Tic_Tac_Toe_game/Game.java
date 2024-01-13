package lld.Tic_Tac_Toe_game;

import lld.Tic_Tac_Toe_game.Model.Board;
import lld.Tic_Tac_Toe_game.Model.Piece;
import lld.Tic_Tac_Toe_game.Model.PieceO;
import lld.Tic_Tac_Toe_game.Model.PieceType;
import lld.Tic_Tac_Toe_game.Model.PieceX;
import lld.Tic_Tac_Toe_game.Model.Player;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    Deque<Player> players;
    Board gameBoard;
    Scanner inputScanner = new Scanner(System.in);

    public void initializeGame(){
        //creating 2 players
        players = new LinkedList<>();
        //for removing and adding from front as well as back

        PieceX crossPiece = new PieceX();
        PieceO noughtsPiece = new PieceO();

        //Piece obj1 = new Piece(PieceType.X);

        Player player1 = new Player("Ankit", noughtsPiece);
        Player player2 = new Player("Aayusha", crossPiece);

        players.add(player1);
        players.add(player2);

        //initialize board
        gameBoard = new Board(3);
    }

    public String startGame(){

        boolean noWinner = true;
        while(noWinner){
            //turn of the player
            Player currPlayer = players.removeFirst();

            //print the board for user
            gameBoard.printBoard();

            //checking if board is already full
            if(gameBoard.isBoardFull()){
                noWinner = false;
                continue;
            }

            System.out.print("Player: " + currPlayer.getPlayerName() + " turn. Please Enter row, column value: ");
            
            if (inputScanner.hasNextLine()) {
                String input = inputScanner.nextLine();
                if (input.isEmpty()){
                    System.out.println("Input is empty, Please enter again, " + currPlayer.getPlayerName());
                    continue;
                }

                String[] values = new String[0]; // Initialize to an empty array

                if(input.contains(" ")){
                    values = input.split(" ");
                }else if(input.contains(",")){
                    values = input.split(",");
                }

                int inputRow = Integer.valueOf(values[0]);
                int inputCol = Integer.valueOf(values[1]);

                //add piece at input row and col
                if((inputRow >= gameBoard.getSize() || inputCol >= gameBoard.getSize()) || (!gameBoard.addPiece(inputRow, inputCol, currPlayer.getPlayingPieceType()))){
                    System.out.println("Incorrect Position Selected. Please try again!");
                    players.addFirst(currPlayer);
                    continue;
                }
                players.addLast(currPlayer);

                //Finding a winner
                boolean isWinner = checkWinner(inputRow, inputCol, currPlayer.getPlayingPieceType().pieceType);

                if(isWinner){
                    gameBoard.printBoard();
                    return currPlayer.getPlayerName();
                }
            } else {
                System.out.println("No input found. Please try again!");
            }
        }
        return "tie";
    }

    private boolean checkWinner(int inputRow, int inputCol, PieceType pieceType){
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //Check in row
        for(int i=0;i<gameBoard.getSize();i++) {

            if(gameBoard.board[inputRow][i] == null || gameBoard.board[inputRow][i].pieceType != pieceType) {
                rowMatch = false;
                break;
            }
        }

        //check in column
        for(int j=0; j<gameBoard.getSize();j++) {

            if(gameBoard.board[j][inputCol] == null || gameBoard.board[j][inputCol].pieceType != pieceType) {
                columnMatch = false;
                break;
            }
        }

        //check in diagonal
        if(inputCol != inputRow) {
            diagonalMatch = false;
            antiDiagonalMatch = false;
        }else{
            for(int i=0, j=0; i<gameBoard.getSize(); i++,j++) {
                if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                    diagonalMatch = false;
                    break;
                }
            }
    
            //need to check anti-diagonals
            for(int i=0, j=gameBoard.getSize()-1; i<gameBoard.getSize(); i++,j--) {
                if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                    antiDiagonalMatch = false;
                    break;
                }
            }
        }
        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
