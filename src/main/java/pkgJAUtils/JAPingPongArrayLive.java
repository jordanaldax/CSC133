package pkgJAUtils;

import java.io.*;
import java.util.*;

public class JAPingPongArrayLive extends JAPingPongArray {

    int liveCount;
    final int DEAD = 0;
    final int LIVE = 1;

    public JAPingPongArrayLive(int numRows, int numCols, int numLiveCells) {
        super();
        defaultValue = 0;
        ROWS = numRows;
        COLS = numCols+1;
        liveCount = numLiveCells;
        nextArray = new int[ROWS][COLS];
        liveArray = new int[ROWS][COLS];
        initializeArray(liveArray);
        initializeArray(nextArray);
        addRandomLive();
    }

    private void addRandomLive() {
        int live = liveCount;
        int row = 0, col = 0;
        Random rand = new Random();
        while(live > 0) {
            row = rand.nextInt(ROWS);
            col = rand.nextInt(COLS-1)+1;
            if(nextArray[row][col] != LIVE) {
                makeLive(row, col);
                live--;
            }
        }
    }

    private void makeLive(int row, int col) {
        nextArray[row][col] = LIVE;
    }

    private void makeDead(int row, int col) {
        nextArray[row][col] = DEAD;
    }

    // read from LIVE ARRAY
    public int countLiveDegreeTwoNeighbors(int row, int col) {
        int count = 0;
        // top = row-1, col

        // bottom = row+1, col

        // left = row, col-1

        // right = row, col+1

        return count;
    }
}
