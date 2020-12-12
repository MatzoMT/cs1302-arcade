package cs1302.arcade;

public class ChessDriver {
    public static void main(String[] args) {


        ChessGame game = new ChessGame();
        while (true) {

//            game.printBoard();
            game.isInCheck(true);
            game.isInCheck(false);
//            game.promptUser();

        }
    }
} // ChessDriver
