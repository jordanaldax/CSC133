package pkgJATTTBackend;

import static pkgJATTTBackend.JASPOT.*;

public class JAMachinePlayer {
    private JATTTBoard my_board;
    private char[][] ttt_board;

    protected JAMachinePlayer(JATTTBoard my_board) {
        this.my_board = my_board;
        this.ttt_board = my_board.getBoard();
    }

    /*

    the game loop seems to be handled in the driver, so this should only play one turn
    first, play any spot that will win the game
    second, play any spot that will prevent a loss
    third, play a strategic spot
        I've thought about it some more, if the machine goes second they cannot take a corner safely
        It would end up like this:
        1. - - -   2. - - -   3. - - P   4. - - P    5. P - P
           - - -      - - -      - - -      - X -       - X -
           P - -      P - X      P - X      P - X       P - X
        and now the machine has guaranteed lost.
        The game is decided by the second turn.
        If the first player cannot lose.
        The second player must take a middle-edge in order to not lose, assuming the first player takes a corner.
        1. - - -   2. - - -   3. - - P   4. - - P    5. - - P
           - - -      - - X      - - X      - X X       P X X
           P - -      P - -      P - -      P - -       P - -
        In this scenario, each player uses their turn to not lose, ending in a draw.
    last, play a random spot (this should be called near the end of the game if the player plays optimally)
    i don't even know how many of these methods from the UML are even useful

     */
    public int play() {
        int[] input = new int[2];
        return 0;
    }

    private boolean playToConclude(char myChar) {
        return true;
    }

    // check if the machine can play the space, call
    private boolean playTheCol(int col) {
        return true;
    }

    private boolean playTheRow(int row) {
        return true;
    }

    private boolean playLDiag() {
        return true;
    }

    private boolean playTDiag() {
        return true;
    }

    // this should be the last option
    private boolean playRandomPick() {
        return true;
    }

    // return which row should be played
    // return row if there is a repeat and playable space
    // return -1 if there is no repeat
    // return -2 if there is no playable space in the column
    // regardless of if the repeat is the player char or machine char, the machine should play that spot
    // either it makes it win, or stops it from losing
    // first non-default_char will be tmp
    // loop to see if there are any empty spaces
    private int findRepeatsInCol(int col) {
        int player_repeats = 0;
        int machine_repeats = 0;
        int rowToPlay = -1;
        for(int row = 0; row < ROW; row++) {
            if(ttt_board[row][col] == player_char) {
                player_repeats++;
            }
            else if(ttt_board[row][col] == machine_char) {
                machine_repeats++;
            }
        }

        // if there are repeats but there's an empty space, finds the empty space and returns it
        if(player_repeats == ROW-1 || machine_repeats == ROW-1) {
            for(int row = 0; row < ROW; row++) {
                if(ttt_board[row][col] == default_char) {
                    rowToPlay = row;
                    break;
                }
            }
        }
        // if there are repeats but not enough space to play, returns -1
        else if(player_repeats + machine_repeats == ROW) {
            rowToPlay = -2;
        }
        // default returns 0, which represents no repeat
        // the two previous conditionals handle the other possible outcomes
        return rowToPlay;
    }

    // return which column should be played
    private int findRepeatsInRow(int row) {
        int player_repeats = 0;
        int machine_repeats = 0;
        int colToPlay = -1;
        for(int col = 0; col < COL; col++) {
            if(ttt_board[row][col] == player_char) {
                player_repeats++;
            }
            else if(ttt_board[row][col] == machine_char) {
                machine_repeats++;
            }
        }

        // if there are repeats but there's an empty space, finds the empty space and returns it
        if(player_repeats == ROW-1 || machine_repeats == ROW-1) {
            for(int col = 0; col < ROW; col++) {
                if(ttt_board[row][col] == default_char) {
                    colToPlay = col;
                    break;
                }
            }
        }
        // if there are repeats but not enough space to play, returns -1
        else if(player_repeats + machine_repeats == ROW) {
            colToPlay = -2;
        }
        // default returns 0, which represents no repeat
        // the two previous conditionals handle the other possible outcomes
        return colToPlay;
    }

    private int findRepeatsLDiagonal(int myInt) {
        return 0;
    }

    private int findRepeatsTDiagonal(int myInt) {
        return 0;
    }

    protected int isGameOver() {
        return 0;
    }
}
