package pkgJAAssignment_3;

import static pkgJATTTBackend.JAIOManager.*;

public class Driver {
    public static void main(String[] args) {
        pkgJATTTBackend.JATTTBoard my_board = new pkgJATTTBackend.JATTTBoard();

        initPrompt();
        printBoard(my_board);
        my_board.testPlay();
        System.out.println("\nStart interactive play");
        my_board.clearBoard();
        my_board.play();
    }
}  // public class Driver


