package pkgJATTTBackend;

import java.util.Scanner;

public class JATTTBoard {
    private final int ROW = 3, COL = 3;
    private int totalValidEntries;
    private char winner_char;
    private char default_char;
    private char[][] ttt_board = new char[ROW][COL];
    private char player_char;
    private char machine_char;

    public char[][] getBoard() {
        return ttt_board.clone();
    }

    public void testPlay() {

    }

    public void clearBoard() {

    }

    private boolean updateBoard(int row, int col) {
        return false;
    }

    private Scanner myScanner = new Scanner(System.in);

    // Calls fillArray() when the object is created.
    public JATTTBoard() {
        fillArray();
    }

    // Fills each cell in the array with '-'
    private void fillArray() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ttt_board[i][j] = '-';
            }
        }
    }

    // Prints the board as a 3x3 grid.
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(ttt_board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ttt_board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCellFree(int row, int col) {
        return ttt_board[row][col] == '-';
    }

    private void updateCell(int row, int col) {
        ttt_board[row][col] = 'P';
    }

    // Checks if the input from the user is valid. Valid inputs must be values
    // from 0 to 2 and cannot be in a cell that has already been filled. If the
    // user's input breaks either of these conditions, a message is printed
    // telling the user that their input is invalid.
    private boolean validInput(int row, int col) {
        if(row > 2 || row < 0 || col > 2 || col < 0) {
            System.out.println("Input out of bounds. Please enter a valid row/column number");
            return false;
        }
        else if(!isCellFree(row, col)) {
            System.out.println("Cell is not free. Please enter a valid row/column number");
            return false;
        }
        else {
            return true;
        }
    }

    // Plays through the game, prompting the user for an input until the board
    // is full. When the board is full, the game ends and prints "Game Over".
    public void play() {
        while(!isBoardFull()) {
            System.out.print("Enter a row and column: ");
            int row = myScanner.nextInt();
            int col = myScanner.nextInt();
            if(validInput(row, col)) {
                updateCell(row, col);
            }
            printBoard();
        }
        System.out.println("Game Over");
    }
}
