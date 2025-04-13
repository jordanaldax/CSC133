package pkgJAUtils;

import java.io.*;
import java.util.*;

public class JAPingPongArray {

    int ROWS;
    int COLS;
    int randMin;
    int randMax;
    int defaultValue = 99;

    // Read from liveArray
    // Write to nextArray

    protected int[][] liveArray;
    protected int[][] nextArray;

    public JAPingPongArray() {
        //
    }

    public JAPingPongArray(int rows, int cols, int min, int max) {
        ROWS = rows;
        COLS = cols + 1;
        randMin = min;
        randMax = max;
        nextArray = new int[ROWS][COLS];
        liveArray = new int[ROWS][COLS];
        initializeArray(nextArray);
        initializeArray(liveArray);
        randomizeInRange();
    }

    protected void initializeArray(int[][] array) {
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                if(j == 0)
                    array[i][j] = i;
                else
                    array[i][j] = defaultValue;
            }
        }
    }

    // swaps the values in the live and next arrays
    public void swapLiveAndNext() {
        int[][] tempArray = liveArray;
        liveArray = nextArray;
        nextArray = tempArray;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }

    public int[][] getLiveArray() {
        return liveArray.clone();
    }

    public String getLiveString() {
        String s = "";
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                s += liveArray[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    public int[][] getNextArray() {
        return nextArray.clone();
    }

    public String getNextString() {
        String s = "";
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                s += nextArray[i][j] + " ";
            }
            s += "\n";
        }
        return s;
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
            int defValue = Integer.parseInt(myReader.readLine().trim());
            defaultValue = defValue;

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
    // should just be value to the top, bottom, left, and right of the current value
    // also needs to include the diagonal values
    // should not include the row number
    // updates the nextArray with the neighbor elements in liveArray
    public void updateToNearestNNSum() {
        // up:           i-1, j
        // down:         i+1, j
        // left:         i, j-1
        // right:        i, j+1
        // top left:     i-1, j-1
        // top right:    i-1, j+1
        // bottom left:  i+1, j-1
        // bottom right: i+1, j+1

        for(int i = 0; i < ROWS; i++) {
            // ignore first column, since it is row numbers
            for(int j = 1; j < COLS; j++) {
                int sum = 0;

                // up: i-1, j
                // if i == 0, there is no up to add
                if(i > 0)
                    sum += liveArray[i-1][j];

                // top left: i-1, j-1
                // if i == 0 there is no up to add
                // if j == 1 there is no left to add
                if(i > 0 && j > 1)
                    sum += liveArray[i-1][j-1];

                // top right: i-1, j+1
                // if i == 0 there is no up to add
                // if j == COLS there is no right to add
                if(i > 0 && j < COLS-1)
                    sum += liveArray[i-1][j+1];

                // down: i+1, j
                // if i == ROWS, there is no down to add
                if(i < ROWS-1)
                    sum += liveArray[i+1][j];

                // down left: i+1, j-1
                // if i == ROWS there is no down to add
                // if j == 1 there is no left to add
                if(i < ROWS-1 && j > 1)
                    sum += liveArray[i+1][j-1];

                // down right: i+1, j+1
                // if i == ROWS there is no down to add
                // if j == COLS there is no right to add
                if(i < ROWS-1 && j < COLS-1)
                    sum += liveArray[i+1][j+1];

                // left: i, j-1
                // if j == 1, left is the row column, and we don't want to add that
                if(j > 1)
                    sum += liveArray[i][j-1];

                // right: i, j+1
                // if j == COLS, there is no right to add
                if(j < COLS-1)
                    sum += liveArray[i][j+1];

                nextArray[i][j] = sum;
            }
        }
    }

    public void save() {
        save("ppa_data.txt");
    }

    public void save(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // being file with default value
            writer.write(String.valueOf(defaultValue));
            writer.newLine();

            // write rows and columns
            // makes sure to write one less column than there actually is
            // since the column for row numbers does not count
            writer.write(ROWS + " " + (COLS-1));
            writer.newLine();

            // write the data
            for(int i = 0; i < ROWS; i++) {

                // write the row number
                writer.write(String.valueOf(i));

                // write the rest of the values in the row
                // makes sure to start at j=1 so that it doesn't
                // rewrite the row number
                for(int j = 1; j < COLS; j++) {
                    writer.write(" " + liveArray[i][j]);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
