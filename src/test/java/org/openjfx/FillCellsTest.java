package org.openjfx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FillCellsTest {

    private Simulator simulator;
    private FillCells fillCells;

    @BeforeEach
    void setUp() {
        simulator = new Simulator(10,10);
        fillCells = new FillCells();
    }

    @Test
    void insertAliveCellsTest() {
        fillCells.insertAliveCells(simulator);
        assertEquals(1, simulator.getCellState(3,2));

    }

}