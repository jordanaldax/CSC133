package pkgJAUtils;

import java.io.*;
import java.util.*;

public class JAGoLArray extends JAPingPongArrayLive {


    /*
        JAGoLArray(String filename) creates a JAGoLArray object
        based on the file passed by calling the loadFile() function.
     */
    public JAGoLArray(final String myDataFile) {
        super();
        loadFile(myDataFile);
        initializeArray(liveArray); // Initializes the liveArray with row numbers
    }

    /*
        JAGoLArray(int,int) creates a JAGoLArray object with a
        pre-determined percentage of cells as being alive.
     */
    public JAGoLArray(final int rows, final int cols) {
        super(rows, cols, (int)(rows*cols*0.2+0.5));
    }

    /*
        JAGoLArray(int,int,int) creates a JAGoLArray object with
        the number of live cells according to the parameter passed.
        The live cells are added randomly.
     */
    public JAGoLArray(int numRows, int numCols, int numAlive) {
        super(numRows, numCols, numAlive);
    }

    /*
        onTickUpdate() uses the countLiveNeighborsWrap() function to
        count the number of live neighbors, and then uses updateCell()
        to get the rule set that determines whether the current cell
        should retain its state or if it should swap states.
     */
    private void onTickUpdate() {
        for(int row = 0; row < ROWS; row++) {
            for(int col = 1; col < COLS; col++) {
                int nnCount = countLiveNeighborsWrap(row, col);
                if(updateCell(nnCount, liveArray[row][col]) == LIVE) {
                    nextArray[row][col] = LIVE;
                } else {
                    nextArray[row][col] = DEAD;
                }
            }
        }
    }

    /*
        updateCell() takes the number of neighbors passed in the
        parameter to be used in the rule set which determines what
        the status of the cell should be. If it falls under one of
        the rules, the cell's status is changed. Otherwise, it
        returns the cell's current status, as it should remain the
        same.
     */
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

            Rules 1 and 3 are condensed into the first part of the conditional.
            Rule 4 is handled by the else if portion of the conditional.
            Rule 2 is handled by the status passed in by parameter being returned,
            therefore retaining its state.

            Technically, part of rule 2 (If live neighbors == 3) is handled by
            the else if portion of the conditional. But since a cell will always
            be alive if there are exactly 3 neighbors, we can ignore adding
            a conditional to check whether the cell is live or dead when it has
            3 neighbors. This does mean that any cell that is already alive
            will still pass through the else if part of the conditional, but
            this doesn't really matter.
         */
        if(nnCount < 2 || nnCount > 3)
            retVal = DEAD;
        else if(nnCount == 3)
            retVal = LIVE;

        return retVal;
    }

    public int getLiveCount() {
        liveCount();
        return liveCount;
    }

    /*
        liveCount() checks every cell in the liveArray and
        returns the number of live cells.
     */
    private int liveCount() {
        int count = 0;
        for(int row = 0; row < ROWS; row++) {
            for(int col = 1; col < COLS; col++) {
                if(liveArray[row][col] == LIVE)
                    count++;
            }
        }
        liveCount = count;
        return count;
    }

    /*
        run() runs a single step of the simulation by calling onTickUpdate()
        and swapLiveAndNext(). Calling swapLiveAndNext() here is to simplify
        the code written in the driver, since the array should always be
        swapped after an update anyways.
     */
    public void run() {
        onTickUpdate();
        swapLiveAndNext();
    }

    /* --- --- TESTING METHODS --- ---*/

    public boolean liveCountTest() {
        boolean retVal = false;
        int c1 = getLiveCount();
        int c2 = liveCount();
        if(c1 != c2)
            return retVal;
        retVal = true;
        return retVal;
    }

    // testRun does 10 iterations to test the functionality of the methods
    public void testRun() {
        System.out.println("0th Iteration:");
        swapLiveAndNext();
        System.out.println("Live Count: " + getLiveCount() + "\n");
        printArray();
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
            System.out.println("Live Count: " + getLiveCount() + "\n");
            printArray();
        }
    }

}
