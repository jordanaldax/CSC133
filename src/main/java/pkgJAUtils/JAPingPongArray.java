package pkgJAUtils;

public class JAPingPongArray {

    int ROWS;
    int COLS;
    int randMin;
    int randMax;
    private int[][] array;

    public JAPingPongArray(int rows, int cols, int min, int max) {
        ROWS = rows;
        COLS = cols;
        randMin = min;
        randMax = max;
        array = new int[ROWS][COLS];
    }

    public void set(int row, int col, int value) {
        //
    }

    public void swapLiveAndNext() {
        //
    }

    public int[][] getArray() {
        return array.clone();
    }

    public void copyToNextArray() {
        //
    }

    public void printArray() {
        //
    }

    public void setCell(int row, int col, int col2) {
        //
    }

    public void randomizeViaFisherYatesKnuth() {
        //
    }

    public void loadFile(String filename) {
        //
    }

    public void updateToNearestNNSum() {
        //
    }

    public void save() {
        //
    }

    public void save(String filename) {
        //
    }

}
