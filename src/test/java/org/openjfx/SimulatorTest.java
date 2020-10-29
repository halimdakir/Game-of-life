package org.openjfx;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {
    Simulator simulator;
    int [][] board;


    @BeforeEach
    void setUp() {
        simulator = new Simulator(10,10);
        board = new int[10][10];
        int[][] tab2DInt ={{3,2},{4,2},{5,2},{6,5},{5,6},{6,6},{1,7},{1,8},{2,8},{1,9},{2,9},{3,9}};
        final List<Cell> cellList = new ArrayList<>();
        for (int[] ints : tab2DInt) {
            int[] newArray = new int[2];
            System.arraycopy(ints, 0, newArray, 0, 2);
            cellList.add(new Cell(newArray[0], newArray[1]));
        }

        for (Cell cell : cellList) {
            simulator.setAlive(cell.getX(), cell.getY());
        }
    }
}