package pkgJAAssignment_7;

import pkgJAUtils.JAPingPongArray;

public class Test {

    public static void main(String[] args) {
        final int ROWS = 7, COLS = 7;
        int myMin = 10, myMax = 20;
        JAPingPongArray myBoard = new JAPingPongArray(ROWS, COLS, myMin, myMax);
        myBoard.swapLiveAndNext();
        System.out.println("[10, 20) Board:");
        myBoard.printArray();
    }
}
