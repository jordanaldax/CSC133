package pkgJAAssignment_8;

import pkgJAUtils.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class Assignment8_Test {

    static int rows = 7, cols = 7, liveCount = 7;
    static JAPingPongArrayLive myBoard = new JAPingPongArrayLive(rows, cols, liveCount);

    public static void main(String[] args) {
        testRun();
    }

    private static void testRun() {
        System.out.println("ult_a: " + passOrFail(ult_a()));
        System.out.println("ult_b: " + passOrFail(ult_b()));
        System.out.println("ult_c: " + passOrFail(ult_c()));
        System.out.println("ult_d: " + passOrFail(ult_d()));
    }

    private static String passOrFail(boolean retVal) {
        if(retVal)
            return "PASS";
        else
            return "FAIL";
    }

    private static boolean ult_a() {

        boolean retVal = false;

        // load file to nextArray
        myBoard.loadFile("ult_a_input.txt");

        // swap ones and zeros because the verify file does this
        myBoard.swapOnesAndZeros();

        // swap live and next arrays
        myBoard.swapLiveAndNext();

        // load verify file to nextArray
        myBoard.loadFile("ult_a_verify.txt");

        // create temporary arrays to test if the arrays match
        int[][] temp1 = myBoard.getLiveArray();
        int[][] temp2 = myBoard.getNextArray();

        // compare the two arrays to verify that they are the same
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols+1; j++) {
                if(temp1[i][j] != temp2[i][j]) {
                    retVal = false;
                    return retVal;
                }
            }
        }

        // if the code got to the end of the method, then ult_a passed
        retVal = true;
        return retVal;
    }

    private static boolean ult_b() {

        boolean retVal = false;

        //

        return retVal;
    }

    private static boolean ult_c() {

        boolean retVal = false;

        myBoard.randomize();

        // get liveArray first, swap arrays, then get nextArray
        // since we swapped, liveArray is now nextArray, so the strings should match
        String liveString = myBoard.getLiveString();
        myBoard.swapLiveAndNext();
        String nextString = myBoard.getNextString();

        // check the two strings, if they are the same, then ult_c passed
        if(liveString.equals(nextString)) {
            retVal = true;
        }

        return retVal;
    }

    private static boolean ult_d() {

        boolean retVal = false;

        //

        return retVal;
    }
}