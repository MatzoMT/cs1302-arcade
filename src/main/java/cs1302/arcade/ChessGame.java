package cs1302.arcade;

import java.util.Scanner;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Defines methods and gameplay mechanics for running a game of chess.
 */
public class ChessGame {
    private boolean isWhiteTurn = true;
    private ChessBoard board = new ChessBoard();
    Scanner keyboard = new Scanner(System.in);

    public void printBoard() {
        System.out.print(" ");
        for (int i = 0; i < 8; i++)
            System.out.print(i);
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print(i);
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

    public void promptUser(String goodMove) {
        Scanner scanString = new Scanner(goodMove);
        int fromX = scanString.nextInt();
        int fromY = scanString.nextInt();
        int toX = scanString.nextInt();
        int toY = scanString.nextInt();
        System.out.println(fromX + "" + fromY + "" + toX + "" + toY);

        Piece thePiece = board.getPiece(fromX, fromY);
        Square theSquare = board.getSquare(fromX, fromY);

        Square toSquare = board.getSquare(toX, toY);
        System.out.println("ADADADADA");
        if ((thePiece.getWhite() == true) && (isWhiteTurn == true) &&
        ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
            if (toSquare.getPiece() == null) {
                confirmMove(theSquare, toSquare, thePiece, toX, toY);
            } else {
                if (toSquare.getPiece().getWhite() == false) {
                    confirmMove(theSquare, toSquare, thePiece, toX, toY);
                } else {
                    System.out.println("ILLEGAL 1");
                }
            }
        } else if ((thePiece.getWhite() == false) && (isWhiteTurn == false) &&
        ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
            if (toSquare.getPiece() == null) {
                confirmMove(theSquare, toSquare, thePiece, toX, toY);
            } else {
                if (toSquare.getPiece().getWhite() == true) {
                    confirmMove(theSquare, toSquare, thePiece, toX, toY);
                } else {
                    System.out.println("ILLEGAL 2");
                }
            }
        } else {
            System.out.println("ILLEGALLLLL");
        }
    } // promptUser

    private void promptUserTo(int toX, int toY) {

    } // promptUserTo

    /*
      public void promptUser() {

      int x = keyboard.nextInt();
      int y = keyboard.nextInt();
      Piece thePiece = board.getPiece(x, y);
      Square theSquare = board.getSquare(x, y);

      int toX = keyboard.nextInt();
      int toY = keyboard.nextInt();
      Square toSquare = board.getSquare(toX, toY);

      if ((thePiece.getWhite() == true) && (isWhiteTurn == true) &&
      ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
      if (toSquare.getPiece() == null) {
      confirmMove(theSquare, toSquare, thePiece, toX, toY);
      } else {
      if (toSquare.getPiece().getWhite() == false) {
      confirmMove(theSquare, toSquare, thePiece, toX, toY);
      } else {
      System.out.println("ILLEGAL 1");
      }
      }
      } else if ((thePiece.getWhite() == false) && (isWhiteTurn == false) &&
      ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
      if (toSquare.getPiece() == null) {
      confirmMove(theSquare, toSquare, thePiece, toX, toY);
      } else {
      if (toSquare.getPiece().getWhite() == true) {
      confirmMove(theSquare, toSquare, thePiece, toX, toY);
      } else {
      System.out.println("ILLEGAL 2");
      }
      }
      } else {
      System.out.println("ILLEGALLLLL");
      }
      } // promptUser
    */


    public ChessBoard getBoard() {
        return this.board;
    }

    public boolean isInCheck(boolean whiteKing) {
        int kingX;
        int kingY;
        if (whiteKing == true) {
            kingX = board.getWhiteKing().getX();
            kingY = board.getWhiteKing().getY();
        } else {
            kingX = board.getBlackKing().getX();
            kingY = board.getBlackKing().getY();
        }

        // for whiteKing
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece attackingPiece = board.getPiece(i, j);
                if (attackingPiece != null) {
                    if ((attackingPiece.getWhite() == !whiteKing) && (attackingPiece.canCapture(kingX, kingY))) {
                        System.out.println("CHECK!!!!!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void confirmMove(Square origin, Square dest, Piece thePiece, int toX, int toY) {
        dest.setPiece(thePiece);
        origin.setPiece(null);
        if (isWhiteTurn == true) {
            isWhiteTurn = false;
        } else {
            isWhiteTurn = true;
        }
        thePiece.setX(toX);
        thePiece.setY(toY);
    } // confirmMove

}
