package org.openjfx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    void getWidthTest() {
        assertEquals(10, simulator.getWidth());
    }

    @Test
    void getHeightTest() {
        assertEquals(10, simulator.getHeight());
    }

    @Test
    void setAliveTest() {
        simulator.setAlive(3, 2);
        assertEquals(1, simulator.getCellState(3,2));
    }

    @Test
    void setDeadTest() {
        simulator.setDead(3, 3);
        assertEquals(0, simulator.getCellState(3,3));
    }

    @Test
    @DisplayName("Testing state if alive or dead")
    void getCellStateTest() {
        assertAll(
                () -> Assertions.assertEquals(1, simulator.getCellState(5,6), "alive"),
                () -> Assertions.assertEquals(0, simulator.getCellState(4,4), "dead"),
                () -> Assertions.assertEquals(1, simulator.getCellState(5,2), "alive")
        );
    }


}