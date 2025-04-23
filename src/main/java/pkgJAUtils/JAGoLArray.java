package pkgJAUtils;

import java.io.*;
import java.util.*;

public class JAGoLArray extends JAPingPongArrayLive {

    int liveCellCount = 0;

    public JAGoLArray(final String myDataFile) {
        super();
        loadFile(myDataFile);
    }

    public JAGoLArray(final int rows, final int cols) {
        super();
        ROWS = rows;
        COLS = cols+1;
    }

    public JAGoLArray(int numRows, int numCols, int numAlive) {
        super(numRows, numCols, numAlive);
    }

    private void onTickUpdate() {
        //
    }

}
