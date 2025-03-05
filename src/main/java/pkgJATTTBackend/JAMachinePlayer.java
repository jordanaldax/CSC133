package pkgJATTTBackend;

import static pkgJATTTBackend.JASPOT.*;
import java.util.*;

public class JAMachinePlayer {
    private JATTTBoard my_board;
    private char[][] ttt_board;
    private static int test = 0;
    Random random = new Random();

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

     */
    public int play() {
        int[] input = new int[2];

        for(int col = 0; col < ROW; col++) {
            int row = findRepeatsInCol(col);
            if(row >= 0) {
                input[0] = row;
                input[1] = col;
            }
        }

        for(int row = 0; row < ROW; row++) {
            int col = findRepeatsInRow(row);
            if(col >= 0) {
                input[0] = row;
                input[1] = col;
            }
        }
        return 0;
    }

    public int testPlay() {
        int[] input = new int[2];
        input = randomPick();
        while(!checkInput(input[0], input[1])) {
            input = randomPick();
        }
        ttt_board[input[0]][input[1]] = machine_char;
        return 0;
    }

    public int testPlay2() {
        int[] input = new int[2];
        if(test < 3) {
            input[0] = test;
            input[1] = test;
        }
        else {
            input = randomPick();
        }
        while(!checkInput(input[0], input[1])) {
            input = randomPick();
        }
        ttt_board[input[0]][input[1]] = machine_char;
        test++;
        return 0;
    }

    private int[] randomPick() {
        int[] input = new int[2];
        int row = random.nextInt(ROW);
        int col = random.nextInt(COL);
        input[0] = row;
        input[1] = col;
        return input;
    }

    private boolean checkInput(int row, int col) {
        if(ttt_board[row][col] == default_char) {
            return true;
        }
        else
            return false;
    }

    // check if machine can win the game
    private boolean playToConclude(char myChar) {
        return true;
    }

    // check if the machine can play the space
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
        int[] input = new int[2];
        int row = random.nextInt(ROW);
        int col = random.nextInt(COL);
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

    // goes through the diagonal and converts it into a normal array
    // checks the chars array for repeats
    // if there's repeats of a char, searches for an empty space to return
    // returns [-1, -1] if there's no repeats or no free space in the diagonal
    private int[] findRepeatsLDiagonal() {
        int[] cell = {-1,-1};
        int[] chars = new int[ROW];
        int player_repeats = 0;
        int machine_repeats = 0;
        for(int i = 0; i < ROW; i++) {
            chars[i] = ttt_board[i][i];
        }
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == player_char) {
                player_repeats++;
            }
            else if(chars[i] == machine_char) {
                machine_repeats++;
            }
        }
        if(player_repeats == ROW-1 || machine_repeats == ROW-1) {
            for(int i = 0; i < chars.length; i++) {
                if(chars[i] == default_char) {
                    cell[0] = i;
                    cell[1] = i;
                }
            }
        }
        return cell;
    }

    private int[] findRepeatsTDiagonal() {
        int[] cell = {-1,-1};
        int[] chars = new int[ROW];
        int player_repeats = 0;
        int machine_repeats = 0;
        int col = COL-1;
        for(int row = 0; row < ROW; row++) {
            chars[row] = ttt_board[row][col];
            col--;
        }
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == player_char) {
                player_repeats++;
            }
            else if(chars[i] == machine_char) {
                machine_repeats++;
            }
        }
        if(player_repeats == ROW-1 || machine_repeats == ROW-1) {
            for(int i = 0; i < chars.length; i++) {
                if(chars[i] == default_char) {
                    cell[0] = i;
                    cell[1] = i;
                }
            }
        }
        return cell;
    }

    protected int isGameOver() {
        return 0;
    }
}
