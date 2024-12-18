package lld.Tic_Tac_Toe_game.Model;

import java.util.HashSet;
import java.util.Set;

public class Board {
    
    private final int size;
    public Piece[][] board;
    private int occupiedCells;
    private int maxSize;

    public Board(int size) {
        this.size = size;
        board = new Piece[size][size];
        occupiedCells = 0;
        maxSize = size * size;
    }

    public int getSize() {
        return size;
    }

    public void printBoard() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                   System.out.print(board[i][j].pieceType.name() + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull(){
        return occupiedCells >= maxSize;
    }

    public boolean addPiece(int row, int col, Piece piece){

        if(board[row][col] != null){
            return false;
        }
        board[row][col] = piece;
        occupiedCells++;
        return true;
    }
}
