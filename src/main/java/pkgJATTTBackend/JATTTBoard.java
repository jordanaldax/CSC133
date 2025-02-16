package pkgJATTTBackend;


public class JATTTBoard {
    private final int ROW = 3, COL = 3;
    private final int totalValidEntries = 2;
    private char winner_char;
    private final char default_char = '-';
    private final char player_char = 'P';
    private final char machine_char = 'X';
    private final char[][] ttt_board;

    public JATTTBoard() {
        ttt_board = new char[ROW][COL];
        clearBoard();
    }

    public char[][] getBoard() {
        return ttt_board.clone();
    }

    public void testPlay() {
        this.updateBoard(0,0);
        JAIOManager.printBoard(this);
        this.updateBoard(1,1);
        JAIOManager.printBoard(this);
        this.updateBoard(2,2);
        JAIOManager.printBoard(this);
        System.out.println("Attempt to fill cell [1,1] which is already filled.");
        this.updateBoard(1,1);
        this.updateBoard(0,1);
        JAIOManager.printBoard(this);
        this.updateBoard(0,2);
        JAIOManager.printBoard(this);
        this.updateBoard(1,0);
        JAIOManager.printBoard(this);
        this.updateBoard(1,2);
        JAIOManager.printBoard(this);
        this.updateBoard(2,0);
        JAIOManager.printBoard(this);
        this.updateBoard(2,1);
        JAIOManager.printBoard(this);
        JAIOManager.boardCompleteMessage();
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
            JAIOManager.invalidEntryMessage();
            return false;
        }
        else if(ttt_board[row][col] != default_char) {
            JAIOManager.cellNotFreeMessage(row, col);
            return false;
        }
        else {
            ttt_board[row][col] = player_char;
            return true;
        }
    }

    public void play() {
        while(true) {
            boolean isFull = true;
            for(int i = 0; i < ROW; i++) {
                for(int j = 0; j < COL; j++) {
                    if(ttt_board[i][j] == default_char) {
                        isFull = false;
                        break;
                    }
                }
                if(!isFull) {
                    break;
                }
            }
            if(isFull) {
                break;
            }

            JAIOManager.printBoard(this);
            JAIOManager.rowColPrompt();
            int[] input = JAIOManager.readIntegerInput(totalValidEntries);
            if(input != null) {
                this.updateBoard(input[0],input[1]);
            }
        }
        JAIOManager.boardCompleteMessage();
        JAIOManager.printBoard(this);
    }
}
