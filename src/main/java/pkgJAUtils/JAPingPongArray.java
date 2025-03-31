package pkgJAUtils;

import java.io.*;
import java.util.*;

public class JAPingPongArray {

    int ROWS;
    int COLS;
    int randMin;
    int randMax;
    private int[][] array;
    private int[][] nextArray;

    public JAPingPongArray(int rows, int cols, int min, int max) {
        ROWS = rows;
        COLS = cols;
        randMin = min;
        randMax = max;
        array = new int[ROWS][COLS];
        randomizeInRange();
        nextArray = array.clone();
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
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setCell(int row, int col, int value) {
        if(col == 0)
            array[row][col] = row;
        else
            array[row][col] = value;
    }

    private void randomizeInRange() {
        Random rand = new Random();

        // fill array with random numbers within randMin and randMax
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                array[i][j] = rand.nextInt(randMax - randMin + 1) + randMin;
            }
        }
    }

    public void randomizeViaFisherYatesKnuth() {
        Random rand = new Random();

        // flatten 2D array into 1D list for shuffling
        int totalElements = ROWS * COLS;
        int[] flatArray = new int[totalElements];
        int index = 0;
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                flatArray[index++] = array[i][j];
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
            for(int j = 0; j < COLS; j++) {
                array[i][j] = flatArray[index++];
            }
        }
    }

    public void loadFile(String filename) {
        try(BufferedReader myReader = new BufferedReader(new FileReader(filename))) {
            List<int[]> dataList = new ArrayList<>();
            String line;

            int curRow;
            while((line = myReader.readLine()) != null) {
                int[] readRow =Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                curRow = readRow[0];

                for(int curCol = 1; curCol < readRow.length; curCol++) {
                    //nextCellArray[curRow][curCol-1] = readRow[curCol];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
