package org.openjfx;

import java.util.ArrayList;
import java.util.List;

public class FillCells {
    private final static List<Cell> cellList = new ArrayList<>();

    static {
        int[][] tab2DInt ={{3,2},{4,2},{5,2},{6,5},{5,6},{6,6}};
        for (int[] ints : tab2DInt) {
            int[] newArray = new int[2];
            System.arraycopy(ints, 0, newArray, 0, 2);
            cellList.add(new Cell(newArray[0], newArray[1]));
        }

    }

    public void insertAliveCells(Simulator simulator){
        for (Cell cell : cellList) {
            simulator.setAlive(cell.getX(), cell.getY());
        }
    }
}
