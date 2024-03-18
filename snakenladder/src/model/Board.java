package model;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell [][] cells; //A 2D matrix in which the cells will contain the cell Object through which we can get if it has snake or ladder

    public Board(int boardSize, int snakeCount, int ladderCount) {
        initializeCells(boardSize);
        addSnakeLadders(snakeCount, ladderCount);
    }

    private void initializeCells(int boardSize){
        cells = new Cell[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                Cell cell = new Cell();
                cells[i][j] = cell;
            }
        }
    }
    private void addSnakeLadders(int snakeCount, int ladderCount){
        //Adding snakes
        while(snakeCount > 0){
            //(40,10)
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

            Cell snakeCell = getCell(snakeHead);
            if(snakeHead <= snakeTail || snakeCell.jumper != null)
                continue;
            Jumper snakeObj = new Jumper(snakeHead, snakeTail);
            snakeCell.setJumper(snakeObj);

            snakeCount--;
        }

        while (ladderCount > 0){
            //(4,23)
            int ladderHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

            Cell ladderCell = getCell(ladderHead);
            if(ladderTail <= ladderHead || ladderCell.jumper != null)
                continue;
            Jumper ladderObj = new Jumper(ladderHead, ladderTail);
            ladderCell.setJumper(ladderObj);

            ladderCount--;
        }
    }

    public Cell getCell(int playerPosition) {
        int boardRow = playerPosition / cells.length;
        int boardColumn = (playerPosition % cells.length);
        return cells[boardRow][boardColumn];
    }

    public int getBoardSize(){
        return cells.length * cells.length - 1;
    }
}
