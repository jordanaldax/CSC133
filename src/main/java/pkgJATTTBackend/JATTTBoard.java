package pkgJATTTBackend;

import static pkgJATTTBackend.JASPOT.*;

public class JATTTBoard {
    private final int totalValidEntries = 2;
    private char winner_char;
    private final char[][] ttt_board;

    public JATTTBoard() {
        ttt_board = new char[ROW][COL];
        clearBoard();
    }

    public char[][] getBoard() {
        return ttt_board.clone();
    }

    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ttt_board[i][j] = default_char;
            }
        }
    }

    protected void setWinnerChar(char winner_char) {
        this.winner_char = winner_char;
    }

    public void playAgainMessage() {
        JAIOManager.playAgainMessage();
        System.out.println("Beginning another round, type 'q' to quit\n");
    }

    private boolean updateBoard(int row, int col) {
        if(row < 0 || row >= ROW || col < 0 || col >= COL) {
            JAIOManager.invalidEntryMessage();
            return false;
        }
        else if(!emptySpace(row, col)) {
            JAIOManager.cellNotFreeMessage(row, col);
            return false;
        }
        else {
            ttt_board[row][col] = player_char;
            return true;
        }
    }

    // isBoardFull happens before checkWinner
    //
    // tmp value equal to first value in array checked
    // go through array row by row, if char equals tmp value each time, that char is the winner
    // if no winner through rows, check column by column
    // if still no winner, check diagonal
    // if still no winner, then no one has won
    // int to return different values based on who won
    private int checkWinner() {
        char tmp;
        char winner_char = winner_check;
        int winner;

        // check across row
        // if it gets to the last col in the row and the
        // char matches the tmp, that char has won
        exitLoop:
        for (int row = 0; row < ROW; row++) {
            tmp = ttt_board[row][0];
            for (int col = 0; col < COL; col++) {
                if (ttt_board[row][col] == default_char) {
                    break;
                }
                else if (col == COL-1 && ttt_board[row][col] == tmp) {
                    winner_char = tmp;
                    break exitLoop;
                } else if (ttt_board[row][col] != tmp) {
                    break;
                }
            }
        }

        // check across col
        exitLoop:
        for(int col = 0; col < COL; col++) {
            tmp = ttt_board[0][col];
            for(int row = 0; row < ROW; row++) {
                if (ttt_board[row][col] == default_char) {
                    break;
                }
                else if(row == ROW-1 && ttt_board[row][col] == tmp) {
                    winner_char = tmp;
                    break exitLoop;
                } else if (ttt_board[row][col] != tmp) {
                    break;
                }
            }
        }

        // check diagonal from top left to bottom right
        tmp = ttt_board[0][0];
        for (int i = 0; i < ROW; i++) {
            if(ttt_board[i][i] != tmp || ttt_board [i][i] == default_char) {
                break;
            }
            else if(ttt_board[ROW-1][COL-1] == tmp) {
                winner_char = tmp;
            }
        }

        // check diagonal from top right to bottom left
        tmp = ttt_board[0][COL-1];
        int col = COL-1;
        for (int row = 0; row < ROW; row++) {
            if(col == 0) {
                winner_char = tmp;
            }
            else if(ttt_board[row][col] != tmp || ttt_board[row][col] == default_char) {
                break;
            }
            col--;
        }

        if(winner_char == player_char) {
            winner = GAME_PLAYER;
        }
        else if(winner_char == machine_char) {
            winner = GAME_MACHINE;
        }
        else {
            winner = GAME_INCOMPLETE;
        }
        return winner;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (ttt_board[i][j] == default_char) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean emptySpace(int row, int col) {
        if(ttt_board[row][col] == default_char) {
            return true;
        }
        else
            return false;
    }

    /*
        print board
        prompt user for input
        check input
        add input to board
        print board
        check if board is full / there's a winner
            if full, prompt game repeat
            if no repeat, game over
        if not full / no winner, machine check board
        prompt machine

     */

    // the game will loop in the driver, therefore play() should play one turn
    // 1. it should check if the board is full
    // 2. it should check for a winner
    // 3. print the board and prompt the player
    // 4. check the user's input
    // 5. prompt the machine

    public int play() {
        JAMachinePlayer machine = new JAMachinePlayer(this);


        JAIOManager.printBoard(this);
        JAIOManager.rowColPrompt();
        int[] input = JAIOManager.readIntegerInput(totalValidEntries);
        if(input == null && JAIOManager.readQuitInput()) {
            return GAME_QUIT;
        }
        else if(input != null) {
            updateBoard(input[0],input[1]);
        }

        machine.testPlay2();

        int game_status = checkWinner();
        if(isBoardFull() && game_status == GAME_INCOMPLETE) {
            return GAME_DRAW;
        }
        else if(game_status != GAME_INCOMPLETE) {
            return game_status;
        }

        return game_status;
    }

    /*
    public int play() {
        JAMachinePlayer machine = new JAMachinePlayer(this);
        int game_status = GAME_INCOMPLETE;
        game_status = checkWinner();
        if(isBoardFull()) {
            if(checkWinner() == -1)
                game_status = GAME_DRAW;
            else
                game_status = checkWinner();
        }
        else if(checkWinner() > 0) {
            game_status = checkWinner();
        }

        // prompting the user
        JAIOManager.printBoard(this);
        JAIOManager.rowColPrompt();
        int[] input = JAIOManager.readIntegerInput(totalValidEntries);
        if(input == null && JAIOManager.readQuitInput()) {
            game_status = GAME_QUIT;
        }
        while(input != null && !emptySpace(input[0], input[1])) {
            JAIOManager.invalidEntryMessage();
            JAIOManager.rowColPrompt();
            input = JAIOManager.readIntegerInput(totalValidEntries);
        }
        if(input != null) {
            updateBoard(input[0], input[1]);
        }
        // prompting the machine
        if(!isBoardFull()) {
            machine.testPlay();
        }

        return game_status;
    }

     */

    /*
    public int play() {
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

        // TEMPORARY RETURN VALUE
        // REMEMBER TO REMOVE THIS
        return 0;
    }
    */

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

    private char winnerConvert(int winner) {
        if (winner == GAME_MACHINE) {
            return machine_char;
        }
        else if (winner == GAME_PLAYER) {
            return player_char;
        }
        else if (winner == GAME_DRAW) {
            return 'd';
        }
        else {
            return default_char;
        }
    }

    public void testWinner() {
        for (int i = 0; i < ROW; i++) {
            ttt_board[i][0] = player_char;
        }
        System.out.println("Checking winner: " + winnerConvert(this.checkWinner()));
        JAIOManager.printBoard(this);
        this.clearBoard();

        for(int i = 0; i < COL; i++) {
            ttt_board[0][i] = player_char;
        }
        System.out.println("Checking winner: " + winnerConvert(this.checkWinner()));
        JAIOManager.printBoard(this);
        this.clearBoard();

        for(int i = 0; i < ROW; i++) {
            ttt_board[i][1] = player_char;
        }
        System.out.println("Checking winner: " + winnerConvert(this.checkWinner()));
        JAIOManager.printBoard(this);
        this.clearBoard();

        for(int i = 0; i < COL; i++) {
            ttt_board[1][i] = machine_char;
        }
        System.out.println("Checking winner: " + winnerConvert(this.checkWinner()));
        JAIOManager.printBoard(this);
        this.clearBoard();

        for(int i = 0; i < ROW; i++) {
            ttt_board[i][i] = machine_char;
        }
        System.out.println("Checking winner: " + winnerConvert(this.checkWinner()));
        JAIOManager.printBoard(this);
        this.clearBoard();

        ttt_board[0][2] = machine_char;
        ttt_board[1][1] = machine_char;
        ttt_board[2][0] = machine_char;
        System.out.println("Checking winner: " + winnerConvert(this.checkWinner()));
        JAIOManager.printBoard(this);
        this.clearBoard();
    }
}
