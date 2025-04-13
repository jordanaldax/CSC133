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

    public void swapOnesAndZeros() {
        for(int i = 0; i < ROWS; i++) {
            for(int j = 1; j < COLS; j++) {
                if(nextArray[i][j] == DEAD)
                    nextArray[i][j] = LIVE;
                else
                    nextArray[i][j] = DEAD;
            }
        }
    }

    // read from LIVE ARRAY
    public int countLiveDegreeTwoNeighbors(int row, int col) {
        int count = 0;
        // top = row-1, col
        if(row > 0 && nextArray[row-1][col] == LIVE)
            count++;

        // bottom = row+1, col
        if(row < ROWS-1 && nextArray[row+1][col] == LIVE)
            count++;

        // left = row, col-1
        if(col > 0 && nextArray[row][col-1] == LIVE)
            count++;

        // right = row, col+1
        if(col < COLS-1 && nextArray[row][col+1] == LIVE)
            count++;

        return count;
    }

    private void resetNextArray() {
        for(int i = 0; i < ROWS; i++) {
            for(int j = 1; j < COLS; j++) {
                nextArray[i][j] = DEAD;
            }
        }
    }

    public void randomize() {
        resetNextArray();
        addRandomLive();
        swapLiveAndNext();
        resetNextArray();
        addRandomLive();
    }


    /* --- --- TESTING METHODS --- ---*/

    private void printNextArray() {
        System.out.println();
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                System.out.print(nextArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void countNNTest() {

        // set array to all DEAD except for row numbers
        for(int i = 0; i < ROWS; i++) {
            for(int j = 1; j < COLS; j++) {
                makeDead(i, j);
            }
        }

        // start with making one lone cell alive and then test count
        makeLive(4,4);
        printNextArray();
        System.out.print("Count: " + countLiveDegreeTwoNeighbors(4, 4) + "\n");

        // make all of its nearest neighbors live and then test count
        makeLive(3,4);
        makeLive(5,4);
        makeLive(4,3);
        makeLive(4,5);
        printNextArray();
        System.out.println("Count: " + countLiveDegreeTwoNeighbors(4, 4) + "\n");

        // make one neighbor dead and test again
        makeDead(3,4);
        printNextArray();
        System.out.println("Count: " + countLiveDegreeTwoNeighbors(4, 4) + "\n");
    }
}
