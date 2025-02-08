package pkgJATTTBackend;

public class JATTTBoard {
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
        //
    }
}
