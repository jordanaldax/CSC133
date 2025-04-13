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

        // creating a new JAPingPongArray object to test the default value portion of the constructor
        JAPingPongArray myBoard2 = new JAPingPongArray(7, 7, 0, 5);

        // the constructor initializes both the liveArray and nextArray with the default value
        // before the first swap, the liveArray should be full of the default value
        // returns if any value besides the row number is not the default value
        int[][] temp = myBoard2.getLiveArray();
        int defValue = myBoard2.getDefaultValue();
        for(int i = 0; i < rows; i++) {
            for(int j = 1; j < cols+1; j++) {
                if(temp[i][j] != defValue) {
                    return retVal;
                }
            }
        }

        myBoard2.swapLiveAndNext();

        // validates that filling the array with defaultValue in loadFile is working
        /*
            ult_a_input.txt comes with empty spaces in the file. if loadFile is working correctly,
            then it will fill those empty spaces with 0 as the default value, which the file provides
        */
        myBoard2.loadFile("ult_a_input.txt");
        myBoard2.swapLiveAndNext();
        int cols = myBoard2.getCols();
        temp = myBoard2.getLiveArray();
        defValue = myBoard2.getDefaultValue();
        if(temp[2][7] != defValue)
            return retVal;
        if(temp[4][6] != defValue && temp[4][7] != defValue)
            return retVal;
        for(int i = 4; i < cols; i++) {
            if(temp[3][i] != defValue) {
                return retVal;
            }
        }

        retVal = true;
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

        myBoard.randomize();

        // create an array of 5 strings
        String[] stringArray = new String[5];

        // store the live array in to the stringArray and then randomize the
        for(int i = 0; i < stringArray.length; i++) {
            stringArray[i] = myBoard.getLiveString();
            myBoard.randomize();
        }

        // checks the value of the current index with the string in the next index
        /*
            since randomize happens at every step in the previous for loop, the randomize
            function should only have failed to properly randomize if two adjacent indices
            are the same. the for loop will not return early so long as the two adjacent
            arrays are not identical.
        */
        for(int j = 0; j < stringArray.length-1; j++) {
            if(stringArray[j].equals(stringArray[j+1])) {
                return retVal;
            }
        }

        // if the code got to the end of the method, then ult_d passed
        retVal = true;
        return retVal;
    }
}