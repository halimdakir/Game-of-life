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

}