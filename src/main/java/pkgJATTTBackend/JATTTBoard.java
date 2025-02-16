package pkgJATTTBackend;

import static pkgJATTTBackend.JAIOManager.*;

public class JATTTBoard {
    private final int ROW = 3, COL = 3;
    private int totalValidEntries = 2;
    private char winner_char;
    private char default_char = '-';
    private char player_char = 'P';
    private char machine_char = 'X';
    private char[][] ttt_board = new char[ROW][COL];

    public JATTTBoard() {
        clearBoard();
    }

    public char[][] getBoard() {
        return ttt_board.clone();
    }

    public void testPlay() {
        JATTTBoard my_board = new JATTTBoard();
        printBoard(my_board);
        updateBoard(0,0);
        printBoard(my_board);
    }

    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ttt_board[i][j] = default_char;
            }
        }
    }

    private boolean updateBoard(int row, int col) {
        if(row < 0 || row >= ROW || col < 0 || col >= COL) {
            invalidEntryMessage();
            return false;
        }
        else if(ttt_board[row][col] != default_char) {
            cellNotFreeMessage(row, col);
            return false;
        }
        else {
            ttt_board[row][col] = player_char;
            return true;
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ttt_board[i][j] == default_char) {
                    return false;
                }
            }
        }
        return true;
    }

    public void play() {
        JATTTBoard my_board = new JATTTBoard();
        while(!my_board.isBoardFull()) {
            printBoard(my_board);
            rowColPrompt();
            int[] input = readIntegerInput(totalValidEntries);
            if(input != null) {
                my_board.updateBoard(input[0],input[1]);
            }
        }
        boardCompleteMessage();
    }
}
