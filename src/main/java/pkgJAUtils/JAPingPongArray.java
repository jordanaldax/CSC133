package pkgJAUtils;

import java.io.*;
import java.nio.file.*;
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

    // initializes array with default value
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
        /*
            our first column is the row numbers, which is why we
            set COLS to cols+1, and so we need to return COLS-1
            when getting the number of columns in the array
         */
        return COLS-1;
    }

    public int[][] getLiveArray() {
        return liveArray.clone();
    }

    public int[][] getNextArray() {
        return nextArray.clone();
    }

    /*
        getLiveString() creates a string of the liveArray.

        Since we cannot simply say if(liveArray == nextArray), or
        if(myBoard.getLiveArray() == myBoard2.getLiveArray()), as
        these should always return false if the arrays are created
        correctly, we need another form of comparison. Rather than
        use the nested for loops to compare two 2D arrays each time,
        I created the nested for loops here that create Strings of
        the array. Those Strings are then compared to each other
        using the .equals() method.

        This method is used for test files.
     */
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

    /*
        getNextString() creates a string of the nextArray.

        See getLiveString() for further explanation.
     */
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

    /*
        printArray() prints the liveArray, excluding the row numbers.
     */
    public void printArray() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 1; j < COLS; j++) {
                System.out.print(liveArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
        printFullArray() prints the liveArray including the row numbers.
     */
    public void printFullArray() {
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

    /*
        setCell() sets the cell in the nextArray at the specified row and
        column with the specified value. The col is set to col++ because
        the user should not be compensating for the row numbers, as they
        shouldn't be able to see them. Therefore, wherever they set the
        value should actually be one column up.

        This method is only used in the drivers for Assignments 7 & 8.
     */
    public void setCell(int row, int col, int value) {
        col++;
        nextArray[row][col] = value;
    }

    /*
        getCell() returns the value of the cell at the specified row and
        column. col is not adjusted in this method because this method's
        primary usage should be in other classes that need the value of
        a particular cell, such as the GeometryManager, as PingPongArray
        and its derivatives can simply call liveArray[row][col] instead
        of using getCell().
     */
    public int getCell(int row, int col) {
        return liveArray[row][col];
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

    /*
        randomizeViaFisherYatesKnuth() uses the FYK algorithm to randomize
        the array.

        The FYK algorithm I used begins at the end of the array and moves its
        way to the beginning.

        Random Value: 5 (Swap index 6 & 5)
        Step 0: [0][1][2][3][4][5][6]
                                   ^
        Random Value: 2 (Swap index 5 & 2)
        Step 1: [0][1][6][3][4][2][5]
                                ^
        Random Value: 6 (Swap index 4 & 6)
        Step 2: [0][1][6][3][5][2][4]
                             ^
        Random Value: 0 (Swap index 3 & 0)
        Step 2: [3][1][6][0][5][2][4]
                          ^
        Random Value: 4 (Swap index 2 & 4)
        Step 2: [3][1][5][0][6][2][4]
                       ^
        Random Value: 1 (Swap index 1 & 1)
        Step 2: [3][1][5][0][6][2][4]
                    ^
        Random Value: 3 (Swap index 0 & 3)
        Step 2: [0][1][5][3][6][2][4]
                 ^

        To do this randomization, I began with flattening the array,
        excluding the first column of the liveArray. Then, I apply
        the FYK algorithm to the entire flattened array.
     */
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

    /*
        findCSC133() finds the location of the CSC133 folder, which
        serves as the root folder for the files being loaded and saved
        from the loadFile() and save() functions. It uses
        searchForCSC133() to search through the directory and
        subdirectories to find it.

        Since I cannot guarantee that the root will remain the same on
        another person's PC, this function looks for the root within
        the program's directory and the subdirectories until it finds
        it.
     */
    private File findCSC133() {
        File current = new File(System.getProperty("user.dir"));
        File result = searchForCSC133(current);
        if(result == null) {
            System.out.println("Could not find CSC133 folder.");
        }
        return result;
    }

    private File searchForCSC133(File dir) {
        if(dir.getName().equals("CSC133")) {
            return dir;
        }

        File[] subDirs = dir.listFiles(File::isDirectory);
        if(subDirs != null) {
            for(File subDir : subDirs) {
                File found = searchForCSC133(subDir);
                if(found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    /*
        loadFile() loads the file specified in the String
        passed as a parameter from the CSC133 folder. It
        does this by calling findCSC133() before attempting
        to load the file, then adding that result to the
        front of the filename passed.
     */
    public void loadFile(String filename) {

        File root = findCSC133();
        File targetFile = new File(root, filename);

        // Original solution: just load filename directly
        try(BufferedReader myReader = new BufferedReader(new FileReader(targetFile))) {
            String line;

            // get default value
            int defValue = Integer.parseInt(myReader.readLine().trim());
            defaultValue = defValue;

            // read max rows and columns
            String[] rowsAndCols = myReader.readLine().trim().split("\\s+");
            ROWS = Integer.parseInt(rowsAndCols[0]);
            COLS = Integer.parseInt(rowsAndCols[1])+1;
            nextArray = new int[ROWS][COLS];
            if(liveArray == null)
                liveArray = new int[ROWS][COLS];

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

    /*
        save() creates a file of the PingPongArray's liveArray
        in its current state to a specified filename passed by
        the user. This file is saved in the CSC133 folder using
        the findCSC133() method to search for the path for the
        CSC133 folder, then adding it to the beginning of the
        filename.

        If no filename is passed by the user, the save() function
        will default to using "ppa_data.txt"
     */
    public void save() {
        save("ppa_data.txt");
    }

    public void save(String filename) {

        File root = findCSC133();
        File targetFile = new File(root, filename);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
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
