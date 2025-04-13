package pkgJAAssignment_8;

import pkgJAUtils.JAPingPongArrayLive;

public class Test {
    public static void main(String[] args) {
        int rows = 7, cols = 7, liveCount = 7;
        JAPingPongArrayLive myBoard = new JAPingPongArrayLive(rows, cols, liveCount);
        myBoard.swapLiveAndNext();
        myBoard.testPrintArray();

        myBoard.countNNTest();
    }

    public static void liveCount(int[][] liveArray) {
        //
    }
}
