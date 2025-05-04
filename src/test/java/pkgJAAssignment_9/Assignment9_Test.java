package pkgJAAssignment_9;

import pkgJAUtils.*;
import pkgJARenderEngine.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class Assignment9_Test {

    static int ROWS = 10, COLS = ROWS, LIVE_COUNT = (int)(ROWS*COLS*0.5);

    public static void main(String[] args) {
        //runTests();
        testRun2();
    }

    private static void testRun() {
        JAGoLArray myBoard = new JAGoLArray("gol_input_1.txt");
        JAGoLArray myBoard2 = new JAGoLArray(ROWS,COLS,LIVE_COUNT);

        /*
            With the loaded file, we should get the same results every time.
            This allows us to make sure that the rules of the Game of Life
            are being followed.
         */
        System.out.println("Running Assignment9_Test - loadFile Test");
        myBoard.testRun();

        /*
            Without loading the file, we should get different results every
            time. This lets us make sure that the randomization is working
            correctly.
         */
        System.out.println("Running Assignment9_Test - Randomized Board Test");
        myBoard2.testRun();
    }

    private static void testRun2() {
        JAGoLArray myBoard = new JAGoLArray("gol_input_1.txt");
        myBoard.swapLiveAndNext();
        ROWS = myBoard.getRows();
        COLS = myBoard.getCols();
        final int polyLength = 50, polyOffset = 10, polyPadding = 2;
        final int winWidth = (polyLength + polyPadding) * COLS + 2 * polyOffset;
        final int winHeight = (polyLength + polyPadding) * ROWS + 2 * polyOffset;
        final int winOrgX = 50, winOrgY = 80;
        final JAWindowManager myWM = JAWindowManager.get(winWidth, winHeight, winOrgX, winOrgY);
        final JARenderer myRenderer = new JARenderer(myWM);
        myRenderer.render(polyOffset, polyPadding, polyLength, ROWS, COLS, myBoard);

    }

    private static void runTests() {
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

    /*
        ult_a tests the loadFile() function. To do this, we load the same file
        twice, so that both the live and next arrays have the board from the
        file. Then, the live and next arrays are compared to each other.

        The test passes if the strings of both the next and live array are equal
        to each other.
     */
    private static boolean ult_a() {
        boolean retVal = false;

        JAGoLArray myBoard = new JAGoLArray(ROWS,COLS,LIVE_COUNT);

        myBoard.loadFile("gol_input_1.txt");
        myBoard.swapLiveAndNext();
        myBoard.loadFile("gol_input_1.txt");

        String live = myBoard.getLiveString();
        String next = myBoard.getNextString();

        if(!live.equals(next))
            return retVal;

        retVal = true;
        return retVal;
    }

    /*
        ult_b tests that the board is creating the proper amount of live cells.
        To do this, we simply create a new board and compare the stored liveCount
        from the object to the LIVE_COUNT variable we defined and used for the
        creation of the object.

        The test passes if the LIVE_COUNT and stored liveCount values are the same.
     */
    private static boolean ult_b() {
        boolean retVal = false;

        JAGoLArray myBoard = new JAGoLArray(ROWS,COLS,LIVE_COUNT);
        myBoard.swapLiveAndNext();

        if(LIVE_COUNT == myBoard.getLiveCount())
            retVal = true;
        return retVal;
    }

    /*
        ult_c tests that randomization does not return the same board
        1st: test that the initialization properly randomizes by comparing
             the initialization results of two different boards
        2nd: test the randomize via FYK and compare it to the initialization
             result of the same board

        The test passes if both conditions are met:
        1. The live array strings of boards 1 and 2 are not equal
        2. The FYK randomized array string is not equal to the initial live
           array string
     */
    private static boolean ult_c() {
        boolean retVal = false;

        JAGoLArray myBoard = new JAGoLArray(ROWS,COLS,LIVE_COUNT);
        JAGoLArray myBoard2 = new JAGoLArray(ROWS,COLS, LIVE_COUNT);
        myBoard.swapLiveAndNext();
        myBoard2.swapLiveAndNext();

        String s1 = myBoard.getLiveString();
        String s2 = myBoard2.getLiveString();

        if(s1.equals(s2))
            return retVal;

        myBoard.swapLiveAndNext();
        myBoard.randomizeViaFisherYatesKnuth();
        myBoard.swapLiveAndNext();

        String fyk = myBoard.getLiveString();

        if(s1.equals(fyk))
            return retVal;

        retVal = true;
        return retVal;
    }

    /*
        ult_d tests the FYK randomization over a series of trials to ensure
        that the method is properly randomizing. To do this, we save the live
        array strings to an array of strings 10 times. Then we compare the
        strings within the array to each other.

        The test passes if no live array string within the array equals the
        string of the index before it.
     */
    private static boolean ult_d() {
        boolean retVal = false;

        JAGoLArray myBoard = new JAGoLArray(ROWS,COLS,LIVE_COUNT);
        myBoard.swapLiveAndNext();

        String[] randomResults = new String[10];

        for(int i = 0; i < randomResults.length; i++) {
            randomResults[i] = myBoard.getLiveString();
            myBoard.randomizeViaFisherYatesKnuth();
            myBoard.swapLiveAndNext();
        }

        for(int i = 1; i < randomResults.length; i++) {
            if(randomResults[i].equals(randomResults[i-1]))
                return retVal;
        }

        retVal = true;
        return retVal;
    }

}