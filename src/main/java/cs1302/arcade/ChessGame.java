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

        if (nothingInWay(fromX, fromY, toX, toY) == true) {
            if ((thePiece.getWhite() == true) && (isWhiteTurn == true) &&
                    ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
            /*
            if (thePiece instanceof Pawn) {
                System.out.println(thePiece.canMoveTo(toX, toY));
                System.out.println(toSquare.getPiece() == null);
                if ((thePiece.canMoveTo(toX, toY)) && (toSquare.getPiece() == null)) {
                    confirmMove(theSquare, toSquare, thePiece, toX, toY);
                } else if ((thePiece.canCapture(toX, toY)) && (toSquare.getPiece() != null)) {
                    confirmMove(theSquare, toSquare, thePiece, toX, toY);
                } else {
                    System.out.println("ILLEGAL PAWN");
                }
            } else */
                if ((toSquare.getPiece() == null)) {
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

        }

    } // promptUser

    private void promptUserTo(int toX, int toY) {

    } // promptUserTo



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

    public boolean nothingInWay(int fromX, int fromY, int toX, int toY) {
        Piece thePiece = board.getPiece(fromX, fromY);
        int tempX;
        int tempY;
        if (thePiece instanceof Bishop) {
            tempX = fromX;
            tempY = fromY;
            if ((fromX > toX) && (fromY > toY)) {
                while ((tempX > toX) && (tempY >toY)) {
                    tempX--;
                    tempY--;
                    if ((tempX != toX) || (tempY != toY)) {
                        if (board.getPiece(tempX, tempY) != null) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            } else if ((fromX > toX) && (fromY < toY)) {
                while ((tempX > toX) && (tempY < toY)) {
                    tempX--;
                    tempY++;
                    if ((tempX != toX) || (tempY != toY)) {
                        if (board.getPiece(tempX, tempY) != null) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            } else if ((fromX < toX) && (fromY < toY)) {
                while ((tempX < toX) && (tempY < toY)) {
                    tempX++;
                    tempY++;
                    if ((tempX != toX) || (tempY != toY)) {
                        if (board.getPiece(tempX, tempY) != null) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            } else if ((fromX < toX) && (fromY > toY)) {
                while ((tempX < toX) && (tempY > toY)) {
                    tempX++;
                    tempY--;
                    if ((tempX != toX) || (tempY != toY)) {
                        if (board.getPiece(tempX, tempY) != null) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            }
        } else {
            return true;
        }
        return true;
    } // nothingInWay

    // isincheck - check each opposite color piece if it can attack the opposite king with attack method
}
