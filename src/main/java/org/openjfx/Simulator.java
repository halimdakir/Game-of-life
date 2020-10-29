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

}