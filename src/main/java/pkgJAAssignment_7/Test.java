package pkgJAAssignment_7;

import pkgJAUtils.JAPingPongArray;

public class Test {

    public static void main(String[] args) {


        /*

            every write is done to the nextArray
            every print is done to the liveArray
            therefore, everything that adds a value to the array should add to the nextArray
            then it should swap the next and live arrays
            and then it should print the live array

         */


        final int ROWS = 7, COLS = 7;
        int myMin = 10, myMax = 20;
        JAPingPongArray myBoard = new JAPingPongArray(ROWS, COLS, myMin, myMax);

        System.out.println("INITIALIZATION TEST:");
        myBoard.testPrintArray();

        myBoard.loadFile("neighbors_test.txt");
        System.out.println("\n[0, 1] data file array:");
        myBoard.swapLiveAndNext();
        myBoard.testPrintArray();

        System.out.println("\nBefore calling NNSum:");
        myBoard.testPrintArray();

        myBoard.updateToNearestNNSum();

        System.out.println("\nAfter calling NNSum:");
        myBoard.testPrintArray();

        myBoard.swapLiveAndNext();
        System.out.println("\nNearest Neighbor sum array:");
        myBoard.testPrintArray();
        myBoard.save("test_sum.txt");
    }
}
