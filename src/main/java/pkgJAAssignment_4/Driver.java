package pkgJAAssignment_4;

import static pkgJATTTBackend.JASPOT.*;

public class Driver {
    public static void main(String[] args) {
        pkgJATTTBackend.JATTTBoard my_board = new pkgJATTTBackend.JATTTBoard();

        int retVal = GAME_INCOMPLETE;
        while (retVal == GAME_INCOMPLETE) {
            retVal = my_board.play();
            if (retVal != GAME_QUIT) {
                retVal = GAME_INCOMPLETE;
                my_board.clearBoard();
                my_board.playAgainMessage();
            }  //  if (retVal != GAME_QUIT)
        }  // while (retVal == pkgTTTBackend.SlSPOT.GAME_CONTINUE)
    }  //  public static void main(String[] args)

}  // public class Driver


