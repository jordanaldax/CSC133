package pkgJAAssignment_4;

import pkgJATTTBackend.JAIOManager;

import static pkgJATTTBackend.JASPOT.*;

public class Test {
    public static void main(String[] args) {
        pkgJATTTBackend.JATTTBoard my_board = new pkgJATTTBackend.JATTTBoard();
        //my_board.testWinner();
        int retVal = GAME_INCOMPLETE;
        while(retVal == GAME_INCOMPLETE) {
            retVal = my_board.play();
        }
        JAIOManager.printWinMessage(retVal);
        JAIOManager.printBoard(my_board);
        System.out.println("Successfully exited game");
    }
}
