package org.openjfx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell cell;

    @BeforeEach
    void setUp() {
       cell = new Cell(3,2);
    }

    @Test
    void getXTest() {
        assertEquals(3, cell.getX());
    }

    @Test
    void getYTest() {
        assertEquals(2, cell.getY());
    }
}