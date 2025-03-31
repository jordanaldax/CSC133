package pkgJAUtils;

import java.io.*;
import java.util.*;

public class JAPingPongArray {

    int ROWS;
    int COLS;
    int randMin;
    int randMax;
    private int[][] liveArray;
    private int[][] nextArray;

    public JAPingPongArray(int rows, int cols, int min, int max) {
        ROWS = rows;
        COLS = cols + 1;
        randMin = min;
        randMax = max;
        nextArray = new int[ROWS][COLS];
        initializeArray();
        randomizeInRange();
        liveArray = nextArray.clone();
    }

    private void initializeArray() {
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                if(j == 0)
                    nextArray[i][j] = i;
                else
                    nextArray[i][j] = -1;
            }
        }
    }

    // swaps the values in the live and next arrays
    public void swapLiveAndNext() {
        int[][] tempArray = liveArray;
        liveArray = nextArray;
        nextArray = tempArray;
    }

    public int[][] getArray() {
        return liveArray.clone();
    }

    public void copyToNextArray() {
        nextArray = liveArray.clone();
    }

    public void printArray() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(liveArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void testPrintArray() {
        System.out.println("\nLive Array:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(liveArray[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nNext Array:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(nextArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setCell(int row, int col, int value) {
        col++;
        nextArray[row][col] = value;
    }

    private void randomizeInRange() {
        Random rand = new Random();

        // fill array with random numbers within randMin and randMax
        for(int i = 0; i < ROWS; i++) {
            for(int j = 1; j < COLS; j++) {
                nextArray[i][j] = rand.nextInt(randMax - randMin + 1) + randMin;
            }
        }
    }

    public void randomizeViaFisherYatesKnuth() {
        Random rand = new Random();

        // flatten 2D array into 1D list for shuffling
        int totalElements = ROWS * (COLS-1);
        int[] flatArray = new int[totalElements];
        int index = 0;

        for(int i = 0; i < ROWS; i++) {
            for(int j = 1; j < COLS; j++) {
                flatArray[index++] = liveArray[i][j];
            }
        }

        // apply FYK shuffle
        for(int i = totalElements - 1; i > 0; i--) {
            int j = rand.nextInt(i+1);

            // swap flatArray at i and j
            int temp = flatArray[i];
            flatArray[i] = flatArray[j];
            flatArray[j] = temp;
        }

        // convert shuffled array back into 2D array
        index = 0;
        for(int i = 0; i < ROWS; i++) {
            for(int j = 1; j < COLS; j++) {
                nextArray[i][j] = flatArray[index++];
            }
        }
    }

    public void loadFile(String filename) {
        try(BufferedReader myReader = new BufferedReader(new FileReader(filename))) {
            String line;

            // get default value
            int defaultValue = Integer.parseInt(myReader.readLine().trim());

            // read max rows and columns
            String[] rowsAndCols = myReader.readLine().trim().split("\\s+");
            int rows = Integer.parseInt(rowsAndCols[0]);
            int cols = Integer.parseInt(rowsAndCols[1]+1);

            // fill nextArray with default value
            for(int i = 0; i < ROWS; i++) {
                for(int j = 0; j < COLS; j++) {
                    nextArray[i][j] = defaultValue;
                }
            }

            // read data
            while((line = myReader.readLine()) != null) {

                // process each line
                int[] readRow = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();

                // first value is row number
                int curRow = readRow[0];

                // set first value to row number
                nextArray[curRow][0] = curRow;

                // fill in data starting from column 1
                for(int curCol = 1; curCol < readRow.length; curCol++) {
                    nextArray[curRow][curCol] = readRow[curCol];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // neighbors of a number are all adjacent values
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
