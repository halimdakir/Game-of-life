package org.openjfx;

public class Simulator {
    private final int width;
    private final int height;
    private int[][] board;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Simulator(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public void setAlive(int x, int y) {
        this.board[x][y] = 1;
    }

    public void setDead(int x, int y) {
        this.board[x][y] = 0;
    }

    public int getCellState(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }
        if (y < 0 || y >= height) {
            return 0;
        }

        return this.board[x][y];
    }

    public int countAliveNeighbours(int x, int y) {
        int counter = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j==0)){
                    counter += getCellState(x - i, y - j);
                }
            }
        }
        return counter;
    }

    public void nextGenerationStep() {
        int[][] temporaryBoard = new int[width][height];
        checkAllCellsToApplyTheRules(height, width, temporaryBoard);
        this.board = temporaryBoard;
    }

    public void checkAllCellsToApplyTheRules(int height, int width, int[][] temporaryBoard){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                rulesGameOfLife(x, y, temporaryBoard);
            }
        }
    }

    public void rulesGameOfLife(int x, int y, int[][] temporaryBoard){
        if (getCellState(x, y) == 1) {

            temporaryBoard[x][y] = stateIsAliveRules(countAliveNeighbours(x, y), temporaryBoard, x, y);

        } else {

            temporaryBoard[x][y] = stateIsDeadRules(countAliveNeighbours(x, y), temporaryBoard, x, y);

        }

    }

    private int casesWhenStateIsAlive(int a) {
        int i = 0;
        if (a  > 3 || a  < 2) i = 1;    //Any live cell [ with more than three or fewer than two live neighbors] dies.
        if (a == 2 || a == 3) i = 2;    //Any live cell [ with two or three live neighbors ] lives.
        return i;
    }

    public int stateIsAliveRules(int neighboursAreAlive, int[][] temporaryBoard, int x, int y){
        switch (casesWhenStateIsAlive(neighboursAreAlive)) {
            case 1:
                temporaryBoard[x][y]= 0;
                break;
            case 2:
                temporaryBoard[x][y] = 1;
                break;
        }
        return temporaryBoard[x][y];
    }

    public int stateIsDeadRules(int neighboursAreAlive, int[][] temporaryBoard, int x, int y){
        if (neighboursAreAlive == 3)
            temporaryBoard[x][y] = 1;  //Any dead cell with exactly three live neighbors becomes a live cell

        return temporaryBoard[x][y];
    }

}