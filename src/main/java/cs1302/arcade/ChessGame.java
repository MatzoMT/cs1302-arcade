package cs1302.arcade;

import java.util.Scanner;

/**
 * Defines methods and gameplay mechanics for running a game of chess.
 */
public class ChessGame {
    private boolean isWhiteTurn = true;
    private ChessBoard board = new ChessBoard();
    Scanner keyboard = new Scanner(System.in);

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

    public void promptUser() {

        int x = keyboard.nextInt();
        int y = keyboard.nextInt();
        Piece thePiece = board.getPiece(x, y);
        Square theSquare = board.getSquare(x, y);

        int toX = keyboard.nextInt();
        int toY = keyboard.nextInt();
        Square toSquare = board.getSquare(toX, toY);

        if ((thePiece.getWhite() == true) && (isWhiteTurn == true) && (thePiece.canMoveTo(toX, toY))) {
            toSquare.setPiece(thePiece);
            theSquare.setPiece(null);
            isWhiteTurn = false;

/*            if (toSquare.getPiece() == null) {
                toSquare.setPiece(thePiece);
                theSquare.setPiece(null);
                isWhiteTurn = false;
            } else {
                if (toSquare.getPiece().getWhite() == false) {
                    toSquare.setPiece(thePiece);
                    theSquare.setPiece(null);
                    isWhiteTurn = false;
                } else {
                    System.out.println("ILLEGAL 1");
                }
                } */
        } else if ((thePiece.getWhite() == false) && (isWhiteTurn == false) && (thePiece.canMoveTo(toX, toY))) {
                toSquare.setPiece(thePiece);
                theSquare.setPiece(null);
                isWhiteTurn = true;

            /*     if (toSquare.getPiece() == null) {
                toSquare.setPiece(thePiece);
                theSquare.setPiece(null);
                isWhiteTurn = true;
            } else {
                if (toSquare.getPiece().getWhite() == true) {
                    toSquare.setPiece(thePiece);
                    theSquare.setPiece(null);
                    isWhiteTurn = true;
                } else {
                    System.out.println("ILLEGAL 2");
                }
                } */
        } else {
            System.out.println("ILLEGALLLLL");
        }



    } // promptUser

}
