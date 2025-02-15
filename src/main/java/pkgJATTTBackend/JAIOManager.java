package pkgJATTTBackend;

import java.util.Scanner;

public class JAIOManager {
    private Scanner myScanner = new Scanner(System.in);

    private JAIOManager() {

    }

    public void cellNotFreeMessage(int row, int col) {
        System.out.println("Cell " + row + ", " + col + " is not free. Please try again.");
    }

    public void rowColPrompt() {
        System.out.print("Enter a row and column: ");
        int row = myScanner.nextInt();
        int col = myScanner.nextInt();
    }

    public boolean readQuitInput() {
        return false;
    }

    public void boardCompleteMessage() {
        System.out.println("\nGame Over - come again!");
    }

    public int[] readIntegerInput(int x) {
        return null;
    }

    public void invalidEntryMessage() {
        System.out.println("Invalid entry. Please try again.");
    }

    public void printBoard(JATTTBoard my_board) {
        char[][] board = my_board.getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void initPrompt() {
        System.out.println("Welcome to the game!");
    }

    public void quitGameMessage() {
        System.out.println("Goodbye!");
    }
}
