package pkgJATTTBackend;

import java.util.Scanner;

public class JATTTBoard {
    private Scanner myScanner = new Scanner(System.in);
    private char[][] board = new char[3][3];

    // Fills the array with a '-' in each cell when the object is created.
    public JATTTBoard() {
        fillArray();
    }
    private void fillArray() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isCellFree(int row, int col) {
        return board[row][col] == '-';
    }
    public void updateCell(int row, int col) {
        board[row][col] = 'P';
    }
    public void play() {
        while(!isBoardFull()) {
            System.out.print("Enter a row and column: ");
            int row = myScanner.nextInt();
            int col = myScanner.nextInt();
            if(row > 2 || row < 0 || col > 2 || col < 0) {
                System.out.println("Invalid row or column");
            }
            else if(!isCellFree(row, col)) {
                System.out.println("Cell is not free");
            }
            else {
                updateCell(row, col);
            }
            printBoard();
        }
        System.out.println("Game Over");
    }
}
