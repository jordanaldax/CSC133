package pkgJAAssignment_9;

import pkgJAUtils.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class Assignment9_Test {

    static int ROWS = 10, COLS = ROWS, LIVE_COUNT = (int)(ROWS*COLS*0.5);
    static JAGoLArray myBoard = new JAGoLArray(ROWS,COLS,LIVE_COUNT);
    static JAGoLArray myBoard2 = new JAGoLArray("gol_input_1.txt");

    public static void main(String[] args) {
        testRun();
    }

    private static void testRun() {
        System.out.println("Running Assignment9_Test");
        myBoard2.testRun();
    }

    private static boolean ult_a() {
        boolean retVal = false;

        myBoard.testPrintArray();

        return retVal;
    }

}