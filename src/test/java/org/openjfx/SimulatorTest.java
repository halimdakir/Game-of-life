package org.openjfx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

    @Test
    void countAliveNeighboursTest() {
        assertAll(
                () -> Assertions.assertEquals(2, simulator.countAliveNeighbours(5,6), "Has 2 alive neighbours"),
                () -> Assertions.assertEquals(1, simulator.countAliveNeighbours(5,2), "Has 1 alive neighbours"),
                () -> Assertions.assertEquals(5, simulator.countAliveNeighbours(2,8), "Has 5 alive neighbours")
        );
    }

    @Test
    @DisplayName("Testing the same cell in different generation")
    void nextGenerationStep() {
        assertEquals(1, simulator.getCellState(3,2), "it is alive in first generation");
        simulator.nextGenerationStep();
        assertEquals(0, simulator.getCellState(3,2), "it should be dead in next generation");
    }

    @Test
    @DisplayName("Mock doNothing Family of methods")
    void checkAllCellsToApplyTheRulesTest() {
        simulator = mock(Simulator.class);
        doNothing().when(simulator).checkAllCellsToApplyTheRules(isA(int.class), isA(int.class), isA(int[][].class));
        simulator.checkAllCellsToApplyTheRules(3,2,board);
        simulator.checkAllCellsToApplyTheRules(5,7,board);
        verify(simulator, times(1)).checkAllCellsToApplyTheRules(3,2,board);
    }

    @Test
    @DisplayName("Mock doCallRealMethod Family of methods")
    void rulesGameOfLifeTest() {
        simulator = mock(Simulator.class);
        doCallRealMethod().when(simulator).rulesGameOfLife(isA(int.class), isA(int.class), isA(int[][].class));
        simulator.rulesGameOfLife(5, 6,board);
        verify(simulator, times(1)).rulesGameOfLife(5, 6,board);

    }

    @Test   //Test private method.
    @DisplayName("Testing 'Private method' which return cases")
    void casesWhenStateIsAliveTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Method method = Simulator.class.getDeclaredMethod("casesWhenStateIsAlive", int.class);
        method.setAccessible(true);
        int casesReturn = (int) method.invoke(simulator, 3);
        int expectedReturn = 2;
        Assertions.assertEquals(expectedReturn, casesReturn, "True");
    }

    @Test
    void stateIsAliveRulesTest() {
        int actual = simulator.stateIsAliveRules(1, board,5,2);
        int expected = 0; //0 means dead
        assertEquals(expected, actual, "Any live cell with fewer than two live neighbors dies, as if caused by underpopulation");

        int actual1 = simulator.stateIsAliveRules(5, board,2,8);
        int expected1 = 0; //0 means dead
        assertEquals(expected1, actual1, "Any live cell with more than three live neighbors dies, as if by overcrowding.");

        int actual2 = simulator.stateIsAliveRules(3, board,1,9);
        int expected2 = 1; //1 means alive
        assertEquals(expected2, actual2, " Any live cell with two or three live neighbors lives on to the next generation");
    }

    @Test
    void stateIsDeadRulesTest() {
        int actual = simulator.stateIsDeadRules(3, board,6,5);
        int expected = 1; //1 means alive
        assertEquals(expected, actual, "Any dead cell with exactly three live neighbors becomes a live cell");
    }


}