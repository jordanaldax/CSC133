package pkgJAAssignment_7;

import pkgJAUtils.JAPingPongArray;

public class Test {

    public static void main(String[] args) {
        final int ROWS = 7, COLS = 7;
        int myMin = 10, myMax = 20;
        JAPingPongArray myBoard = new JAPingPongArray(ROWS, COLS, myMin, myMax);
        System.out.println("[10, 20) Board:");
        myBoard.printArray();

        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                myBoard.setCell(row, col, col);
            }  //  for(int col = 0; col < COLS; ++col)
        }  //  for(int row = 0; row < ROWS; ++row)
        System.out.println("\n[0, COLS) Board:");
        myBoard.printArray();
    }
}
