package pkgJATTTBackend;

public class JAMachinePlayer {
    private JATTTBoard my_board;
    private char[][] ttt_board;

    protected JAMachinePlayer(JATTTBoard my_board) {
        this.my_board = my_board;
        this.ttt_board = my_board.getBoard();
    }

    // the game loop seems to be handled in the driver, so this should only play one turn
    // first, play any spot that will win the game
    // second, play any spot that will prevent a loss
    // third, play a strategic spot (corners, middle)
        // we want the machine to try and do the guaranteed win strategy (take three corners)
        // if it can't do that, take the middle
    // last, play a random spot (this should be called near the end of the game if the player plays optimally)
    public int play() {
        return 0;
    }

    private boolean playToConclude(char myChar) {
        return true;
    }

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

    private int findRepeatsInCol(int col) {
        return 0;
    }

    private int findRepeatsInRow(int row) {
        return 0;
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
