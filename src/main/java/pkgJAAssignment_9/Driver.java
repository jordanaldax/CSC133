package pkgJAAssignment_9;

import pkgJARenderEngine.JARenderer;
import pkgJAUtils.JAGoLArray;
import pkgJAUtils.JAWindowManager;

public class Driver {

    static int ROWS = 16, COLS = ROWS, LIVE_COUNT = (int)(ROWS*COLS*0.5);

    public static void main(String[] args) {
        JAGoLArray myBoard = new JAGoLArray("gol_input_1.txt");
        //JAGoLArray myBoard = new JAGoLArray("ppa_data.txt");
        //JAGoLArray myBoard = new JAGoLArray(ROWS, COLS, LIVE_COUNT);
        //JAGoLArray myBoard = new JAGoLArray(ROWS,COLS);
        myBoard.swapLiveAndNext();
        //myBoard.save();
        ROWS = myBoard.getRows();
        COLS = myBoard.getCols() + 1;
        final int polyLength = 500/ROWS, polyOffset = 2, polyPadding = 2;
        /*
            window size = polyOffset    // left margin
                          + COLS * (polyLength + polyPadding) // number of tiles plus the padding in between
                          + polyOffset  // right margin
         */
        final int winWidth = (polyLength + polyPadding) * COLS + 2 * polyOffset;
        final int winHeight = (polyLength + polyPadding) * ROWS + 2 * polyOffset;
        final int winOrgX = 50, winOrgY = 80;
        final JAWindowManager myWM = JAWindowManager.get(winWidth, winHeight, winOrgX, winOrgY);
        final JARenderer myRenderer = new JARenderer(myWM);
        myRenderer.render(polyOffset, polyPadding, polyLength, ROWS, COLS, myBoard);
    }

}
