package pkgJAAssignment_9;

import pkgJAUtils.*;
import pkgJARenderEngine.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class Assignment9_Test {

    static int ROWS = 10, COLS = ROWS, LIVE_COUNT = (int)(ROWS*COLS*0.5);

    public static void main(String[] args) {
        if(args.length > 0)
            runTests(args[0]);
        else
            runTests(null);
        //testRun3();
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

    /*
        I noticed that sometimes, on the edges of the screen, there would be
        some live cells that should not be alive. In this particular case, the
        right edge of the screen had two cells that had normalized, despite the
        fact that it should be impossible. This test allows me to see whether
        it's a rendering issue or a game logic issue.

        This test run shows us that it's a rendering issue, because the array itself
        has a valid construct that would be alive when normalized. The OpenGL version
        at this point in time does not render the last two columns.
     */
    private static void testRun3() {
        JAGoLArray myBoard = new JAGoLArray("ppa_data.txt");
        myBoard.swapLiveAndNext();
        while(true) {
            myBoard.run();
            myBoard.printArray();
        }
    }

    private static void runTests(String s) {
        System.out.println("ult_a: " + passOrFail(ult_a()));
        System.out.println("ult_b: " + passOrFail(ult_b()));
        System.out.println("ult_c: " + passOrFail(ult_c()));
        System.out.println("ult_d: " + passOrFail(ult_d()));
        System.out.println("ult_e: " + passOrFail(ult_e()));
        System.out.println("ult_f: " + passOrFail(ult_f()));
        System.out.println("ult_g: " + passOrFail(ult_g()));
        System.out.println("ult_h: " + passOrFail(ult_h(s)));
        System.out.println("ult_i: " + passOrFail(ult_i(s)));
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

    /*
        ult_e tests the rules of the Game of Life to ensure that the
        cells are following the rules.
        The rules are:
            1. If live neighbors < 2: DEAD
            2. If live neighbors == 2 or == 3: Retain state (do nothing)
            3. If live neighbors > 3: DEAD
            4. If cell is DEAD and live neighbors == 3: LIVE
        We test this by loading a specific file, running for one tick,
        and checking the array with the second file, which is the first
        array after one tick done manually.

        The test passes if the liveArray string of the first board after
        the automated update matches the liveArray string of the second
        board which has only loaded the file.
     */
    private static boolean ult_e() {
        boolean retVal = false;

        JAGoLArray myBoard = new JAGoLArray("ult_e_input.txt");
        myBoard.swapLiveAndNext();
        myBoard.run();

        JAGoLArray myBoard2 = new JAGoLArray("ult_e_verify_wrap.txt");
        myBoard2.swapLiveAndNext();

        String s1 = myBoard.getLiveString();
        String s2 = myBoard2.getLiveString();

        if(!s1.equals(s2)) {
            return retVal;
        }

        retVal = true;
        return retVal;
    }

    /*
        ult_f tests the creation of an OpenGL window through the
        JAWindowManager class. To do this, we create a window using the
        class, let it stay open for a bit, and then closing it.

        The test passes if it successfully opens and destroys the window
        without any problems.
     */
    private static boolean ult_f() {
        boolean retVal = false;

        int winWidth = 500;
        int winHeight = 500;
        int winOrgX = 50, winOrgY = 80;

        try {
            JAWindowManager wm = JAWindowManager.get(winWidth, winHeight, winOrgX, winOrgY);
            wm.updateContextToThis();

            Thread.sleep(100);

            wm.destroyGlfwWindow();

        } catch(Exception e) {
            return false;
        }

        retVal = true;
        return retVal;
    }

    /*
        ult_g tests the save() method. To test this, we create a board,
        save that board, and then create a second board loading the file
        created by the first.

        The test passes if the liveArray strings are equal to each other.
     */
    private static boolean ult_g() {
        boolean retVal = false;

        JAGoLArray myBoard = new JAGoLArray(ROWS,COLS,LIVE_COUNT);
        myBoard.swapLiveAndNext();
        myBoard.save("ult_g_verify.txt");

        JAGoLArray myBoard2 = new JAGoLArray("ult_g_verify.txt");
        myBoard2.swapLiveAndNext();

        String s1 = myBoard.getLiveString();
        String s2 = myBoard2.getLiveString();

        if(!s1.equals(s2)) {
            return retVal;
        }

        retVal = true;
        return retVal;
    }

    /*
        ult_h tests the command line argument. To test this, we set the command
        line argument in the test configuration, and then compare it to a string.

        The test passes if the string in the command line argument matches
        the filename "gol_input_1.txt".
     */
    private static boolean ult_h(String s) {
        boolean retVal = false;

        if(!s.equals("gol_input_1.txt"))
            return retVal;

        retVal = true;
        return retVal;
    }

    /*
        ult_i tests the creation of a board using the command line argument.
        To test this, we create a board using the command line argument.

        The test passes if the liveArray string of the board created using
        the command line argument matches the liveArray string of the board
        created using the loadFile() function.
     */
    private static boolean ult_i(String s) {
        boolean retVal = false;

        JAGoLArray myBoard;

        if(s == null)
            myBoard = new JAGoLArray(ROWS,COLS,LIVE_COUNT);
        else
            myBoard = new JAGoLArray(s);

        myBoard.swapLiveAndNext();

        JAGoLArray myBoard2 = new JAGoLArray("gol_input_1.txt");
        myBoard2.swapLiveAndNext();

        String s1 = myBoard.getLiveString();
        String s2 = myBoard2.getLiveString();

        if(!s1.equals(s2)) {
            return retVal;
        }

        retVal = true;
        return retVal;
    }

}