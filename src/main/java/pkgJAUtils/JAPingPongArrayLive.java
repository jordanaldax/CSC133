package pkgJAUtils;

import java.util.*;

public class JAPingPongArrayLive extends JAPingPongArray {

    int liveCount;
    final int DEAD = 0;
    final int LIVE = 1;

    public JAPingPongArrayLive() {
        super();
    }

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

    protected void addRandomLive() {
        int live = liveCount;
        int row = 0, col = 1;
        Random rand = new Random();
        while(live > 0) {
            row = rand.nextInt(ROWS);
            col = rand.nextInt(COLS - 1) + 1;
            if(nextArray[row][col] != LIVE) {
                makeLive(row, col);
                live--;
            }
        }
    }

    protected void makeLive(int row, int col) {
        nextArray[row][col] = LIVE;
    }

    protected void makeDead(int row, int col) {
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

    public int countLiveNeighbors(int row, int col) {
        int count = 0;

        // top = row-1, col
        if(row > 0 && liveArray[row-1][col] == LIVE)
            count++;

        // top-left = row-1, col-1
        if(row > 0 && col > 1 && liveArray[row-1][col-1] == LIVE)
            count++;

        // top-right = row-1, col+1
        if(row > 0 && col < COLS-1 && liveArray[row-1][col+1] == LIVE)
            count++;

        // bottom = row+1, col
        if(row < ROWS-1 && liveArray[row+1][col] == LIVE)
            count++;

        // bottom-left = row+1, col-1
        if(row < ROWS-1 && col > 1 && liveArray[row+1][col-1] == LIVE)
            count++;

        // bottom-right = row+1, col+1
        if(row < ROWS-1 && col < COLS-1 && liveArray[row+1][col+1] == LIVE)
            count++;

        // left = row, col-1
        if(col > 1 && liveArray[row][col-1] == LIVE)
            count++;

        // right = row, col+1
        if(col < COLS-1 && liveArray[row][col+1] == LIVE)
            count++;

        return count;
    }

    /*
        Since my output for the game was very different, I tested a variety
        of things. I made sure that the rules are being followed correctly,
        it's excluding the first column, etc. So my final conclusion is that
        the professor's version of the program must wrap to the beginning to
        check the live neighbors.

        So this method is to count the live neighbors after wrapping.
     */
    public int countLiveNeighborsWrap(int row, int col) {
        int count = 0;

        /*
            Assuming ROWS = 100 and row = 0:
            r = (0 + -1 + 100) % 100
              = (99) % 100
              = 99
            So when row = 0, we wrap around to check the 99th row,
            or ROWS-1.

            However, when col = 1, this should be equivalent to our
            col = 0. This also means that when col = COLS-1, it should
            wrap around to col = 1, not col = 0.

            Assuming COLS = 100 and col = COLS-1 (99)
            c = (99 + 1 + 100) % 100
              = (200) % 100
              = 0
            This needs to wrap to col = 1, not col = 0

            Assuming COLS = 100 and col = 1
            c = (1 + -1 + 100) % 100
              = (100) % 100
              = 0
            Similarly, if col = 1, it needs to wrap to COLS-1, not col = 0
         */


        for(int i = -1; i <= 1; i++) { // Checks row-1, row, and row+1
            int r = (row + i + ROWS) % ROWS;

            for(int j = -1; j <= 1; j++) { // Checks col-1, col, and col+1

                if(i == 0 && j == 0) continue; // Skip current cell

                int c = (col + j + COLS) % COLS;
                if(col == COLS-1 && c == 0)
                    c = 1;
                else if(col == 1 && c == 0)
                    c = COLS-1;

                if(liveArray[r][c] == LIVE)
                    count++;
            }
        }

        /*
            Conditions:
            top-left:
                row == 0, col == 1:
                row == ROWS-1, col == COLS-1:
                    wrap both row and col
                row == 0, col > 1:
                row == ROWS-1, col > 1:
                    wrap row
                row > 0, col == 1:
                row > 0, col == COLS-1:
                    wrap col

                [-] [-] [ ] [-]
                [ ] [ ] [ ] [ ]
                [-] [-] [ ] [-]
                [X] [-] [ ] [-]

                When the checking the neighbors for a corner, like X
                in this scenario, we need to count the cells marked -.
         */
        /*
        int rowCheck = row;
        int colCheck = col;
        if(row == 0)
            rowCheck = ROWS-1;
        else if(row == ROWS-1)
            rowCheck = 0;

        if(col == 0)
            colCheck = COLS-1;
        else if(col == COLS-1)
            colCheck = 1;

        if(row != rowCheck && col != colCheck) { // row and col need to be wrapped if they aren't equal to rowCheck/colCheck
            if(liveArray[rowCheck][colCheck] == LIVE)
                count++;
        }

         */

        return count;
    }

    // read from LIVE ARRAY
    /*
        0th degree neighbors: 2^2   = 4
        1st degree neighbors: 2^3   = 8
        2nd degree neighbors: 2^4   = 16?
        0 [ ] [ ] [ ] [ ] [ ] [ ] [ ]
        1 [ ] [ ] [ ] [2] [ ] [ ] [ ]
        2 [ ] [ ] [1] [O] [1] [ ] [ ]
        3 [ ] [2] [O] [X] [O] [2] [ ]
        4 [ ] [ ] [1] [O] [1] [ ] [ ]
        5 [ ] [ ] [ ] [2] [ ] [ ] [ ]
        6 [ ] [ ] [ ] [ ] [ ] [ ] [ ]
        with the cell we're checking being marked as X
        0th degree: 0
        1st degree: 1
        2nd degree: 2

        I don't really know how we're supposed to do the 2nd degree neighbors,
        since 2nd degree adds 8 more but there are 12 immediate squares around
        the initial 8. So the way I have it in the diagram is how I'm going to
        be doing it.
     */
    public int countLiveDegreeTwoNeighbors(int row, int col) {
        int count = countLiveNeighbors(row, col);

        // far top = row-2, col
        if(row > 1 && liveArray[row-2][col] == LIVE)
            count++;

        // far bottom = row+2, col
        if(row < ROWS-2 && liveArray[row+2][col] == LIVE)
            count++;

        // far left = row, col-2
        if(col > 2 && liveArray[row][col-2] == LIVE)
            count++;

        // far right = row, col+2
        if(col < COLS-2 && liveArray[row][col+2] == LIVE)
            count++;

        return count;
    }

    protected void resetNextArray() {
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

    protected void printNextArray() {
        System.out.println();
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                System.out.print(nextArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void testRandomLive() {
        addRandomLive();
        swapLiveAndNext();
        printArray();
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
