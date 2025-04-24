package pkgJAUtils;

import java.io.*;
import java.util.*;

public class JAGoLArray extends JAPingPongArrayLive {

    int liveCellCount;

    public JAGoLArray(final String myDataFile) {
        super();
        loadFile(myDataFile);
    }

    public JAGoLArray(final int rows, final int cols) {
        super();
        ROWS = rows;
        COLS = cols+1;
        liveCellCount = (rows*cols)/2;
    }

    public JAGoLArray(int numRows, int numCols, int numAlive) {
        super(numRows, numCols, numAlive);
    }

    /*
        We determine what happens to each cell during each tick update.
        We need to check the live neighbors, and decide based on the GoL
        ruleset whether the current cell is set to LIVE or DEAD.
     */
    private void onTickUpdate() {
        for(int row = 0; row < ROWS; row++) {
            for(int col = 1; col < COLS; col++) {
                int nnCount = countLiveNeighbors(row, col);
                if(updateCell(nnCount, liveArray[row][col]) == LIVE) {
                    nextArray[row][col] = LIVE;
                } else {
                    nextArray[row][col] = DEAD;
                }
            }
        }
    }

    private int updateCell(int nnCount, int status) {
        int retVal = status;

        /*
            Rules:
            1. If live neighbors < 2: DEAD
            2. If live neighbors == 2 or == 3: Retain state (do nothing)
            3. If live neighbors > 3: DEAD
            4. If cell is dead and live neighbors == 3: LIVE

            This should translate to just two conditionals:
            if(count < 2 || count > 3)
                DEAD
            else if(count == 3)
                LIVE

            This is because regardless of if the cell is alive or dead,
            having 3 live neighbors will make the cell alive. If the cell
            is already alive, it will retain that state. If it was dead,
            it's made alive. Therefore, we don't need to bother checking
            the state of the cell when count == 3 because the cell will
            always be alive.
         */

        if(nnCount < 2 || nnCount > 3)
            retVal = DEAD;
        else if(nnCount == 3)
            retVal = LIVE;

        return retVal;
    }

    private int getLiveCount() {
        int count = 0;
        for(int row = 0; row < ROWS; row++) {
            for(int col = 1; col < COLS; col++) {
                if(liveArray[row][col] == LIVE)
                    count++;
            }
        }
        liveCellCount = count;
        return count;
    }

    // will run the simulation when called
    public void run() {
        while(true) {
            onTickUpdate();
            swapLiveAndNext();
            printArray();
        }
    }

    /* --- --- TESTING METHODS --- ---*/

    // testRun does 10 iterations to test the functionality of the methods
    public void testRun() {
        System.out.println("0th Iteration:");
        swapLiveAndNext();
        printArray();
        System.out.println("Live Count: " + getLiveCount() + "\n");
        for(int i = 1; i <= 10; i++) {
            onTickUpdate();
            swapLiveAndNext();
            if(i == 1)
                System.out.println(i + "st Iteration:");
            else if(i == 2)
                System.out.println(i + "nd Iteration:");
            else if(i == 3)
                System.out.println(i + "rd Iteration:");
            else
                System.out.println(i + "th Iteration:");
            printArray();
            System.out.println("Live Count: " + getLiveCount() + "\n");
        }
    }

}
