package pkgJAAssignment_2;

public class Test {
    public static void main(String[] args) {
        pkgJATTTBackend.JATTTBoard my_board = new pkgJATTTBackend.JATTTBoard();

        System.out.println("printBoard before fillArray:");
        my_board.printBoard();
        my_board.play();
        System.out.println("printBoard after fillArray:");
        my_board.printBoard();
    }
}
