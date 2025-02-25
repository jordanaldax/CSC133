package pkgJATTTBackend;

import java.util.Scanner;

public class JAIOManager {
    private static Scanner myScanner = new Scanner(System.in);

    private JAIOManager() {

    }

    public static void initPrompt() {
        System.out.println("Welcome to the game!");
    }

    public static void rowColPrompt() {
        System.out.print("Enter a row and column: ");
    }

    public static void cellNotFreeMessage(int row, int col) {
        System.out.println("Cell [" + row + ", " + col + "] is not free. Please try again.");
    }

    public static void invalidEntryMessage() {
        System.out.println("Invalid entry. Please try again.");
    }

    public static void errorInPlayMessage() {
        System.out.println("errorInPlayMessage");
    }

    public static void boardCompleteMessage() {
        System.out.println("All entries on the board are filled!\nGame Over - come again!\n");
    }

    public static void playerWinMessage() {
        System.out.println("Player won!");
    }

    public static void machineWinMessage() {
        System.out.println("Machine won!");
    }

    public static void gameDrawMessage() {
        System.out.println("Game draw!");
    }

    public static void quitGameMessage() {
        System.out.println("Goodbye!");
    }

    public static void playAgainMessage() {
        System.out.println("Play again? ");
    }

    public static int[] readIntegerInput(int intsToRead) {
        if(myScanner.hasNextInt()) {
            int[] input = new int[intsToRead];
            for(int i = 0; i < intsToRead; i++) {
                input[i] = myScanner.nextInt();
            }
            myScanner.nextLine();
            return input;
        }
        else {
            readQuitInput();
            return null;
        }
    }

    public static boolean readQuitInput() {
        String tmpString = myScanner.next();
        char tmpChar = tmpString.charAt(0);
        if (tmpChar == 'Q' || tmpChar == 'q') {
            quitGameMessage();
            System.exit(0);
            return true;
        }
        else {
            invalidEntryMessage();
            return false;
        }
    }

    public static void printBoard(JATTTBoard my_board) {
        char[][] board = my_board.getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
