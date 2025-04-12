package pkgJAAssignment_7;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest_1 {
    public static void main(String[] my_args) {
        System.out.println("Running DriverTest_1");
        testRun();
    }

    private static void testRun() {
        System.out.println("ult_a: " + passOrFail(ult_a()));
    }

    private static String passOrFail(boolean retVal) {
        if(retVal)
            return "PASS";
        else
            return "FAIL";
    }

    private static boolean ult_a() {
        boolean retVal = false;

        retVal = true;

        return retVal;
    }
}