package pkgJATTTBackend;

public class JAMachinePlayer {
    private JATTTBoard my_board;
    private char[][] ttt_board;

    protected JAMachinePlayer(pkgJATTTBackend.JATTTBoard my_board) {
        this.my_board = my_board;
    }

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

    private boolean playRandomPick() {
        return true;
    }

    private int findRepeatsInCol(int col, int row) {
        return 0;
    }

    private int findRepeatsInRow(int row, int col) {
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
