package cs1302.arcade;

/**
 * Defines methods and gameplay mechanics for running a game of chess.
 */
public class ChessGame {
    private boolean isWhiteTurn = true;
    private ChessBoard board = new ChessBoard();

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                /* if (board.getPiece(i, j) != null) {
                    System.out.println(board.getPiece(i, j).getClass());
                } */
                if (board.getPiece(i, j) instanceof Pawn) {
                    System.out.print("P");
                } else if (board.getPiece(i, j) instanceof Rook) {
                    System.out.print("R");
                } else if (board.getPiece(i, j) instanceof Knight) {
                    System.out.print("N");
                } else if (board.getPiece(i, j) instanceof Bishop) {
                    System.out.print("B");
                } else if (board.getPiece(i, j) instanceof King) {
                    System.out.print("K");
                } else if (board.getPiece(i, j) instanceof Queen) {
                    System.out.print("Q");
                } else {
                    System.out.print("-");
                }
            } // for
            System.out.println();
        } // for
    }

}
