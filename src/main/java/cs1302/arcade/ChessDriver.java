package cs1302.arcade;

public class ChessDriver {
    public static void main(String[] args) {


        ChessGame game = new ChessGame();
        while (true) {

            game.printBoard();
            game.promptUser();
        }
    }
} // ChessDriver
