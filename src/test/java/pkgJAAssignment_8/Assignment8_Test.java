package pkgJAAssignment_8;

import pkgJAUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class Assignment8_Test {
    public static void main(String[] args) {
        int rows = 7, cols = 7, liveCount = 7;
        JAPingPongArrayLive myBoard = new JAPingPongArrayLive(rows,cols,liveCount);
        myBoard.swapLiveAndNext();
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

        //

        return retVal;
    }

    private static boolean ult_b() {
        boolean retVal = false;

        //

        return retVal;
    }

    private static boolean ult_c() {
        boolean retVal = false;

        //

        return retVal;
    }

    private static boolean ult_d() {
        boolean retVal = false;

        //

        return retVal;
    }
}