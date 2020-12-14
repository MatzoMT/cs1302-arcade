package cs1302.arcade;

import java.util.Scanner;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Defines methods and gameplay mechanics for running a game of chess.
 */
public class ChessGame {
    private boolean isWhiteTurn = true;
    private boolean inCheck;
    private ChessBoard board = new ChessBoard();
    private int whiteScore = 0;
    private int blackScore = 0;


    public void promptUser(String goodMove) {
        Scanner scanString = new Scanner(goodMove);
        int fromX = scanString.nextInt();
        int fromY = scanString.nextInt();
        int toX = scanString.nextInt();
        int toY = scanString.nextInt();

        Piece thePiece = board.getPiece(fromX, fromY);
        Square theSquare = board.getSquare(fromX, fromY);

        Square toSquare = board.getSquare(toX, toY);

        if (thePiece == null) {
            System.out.println("Invalid move. Try again.");
        } else {
            if (nothingInWay(fromX, fromY, toX, toY) == true) {
                if ((thePiece.getWhite() == true) && (isWhiteTurn == true) &&
                        ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
                    if ((toSquare.getPiece() == null)) {
                        changeScores(toSquare);
                        if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                            confirmMove(theSquare, toSquare, thePiece, toX, toY);
                        }
                    } else {
                        if (toSquare.getPiece().getWhite() == false) {
                            changeScores(toSquare);
                            if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                                confirmMove(theSquare, toSquare, thePiece, toX, toY);
                            }
                        } else {
                            System.out.println("ILLEGAL 1");
                        }
                    }
                } else if ((thePiece.getWhite() == false) && (isWhiteTurn == false) &&
                        ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
                    if (toSquare.getPiece() == null) {
                        changeScores(toSquare);
                        if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                            confirmMove(theSquare, toSquare, thePiece, toX, toY);
                        }

                    } else {
                        if (toSquare.getPiece().getWhite() == true) {
                            changeScores(toSquare);
                            if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                                confirmMove(theSquare, toSquare, thePiece, toX, toY);
                            }

                        } else {
                            System.out.println("ILLEGAL 2");
                        }
                    }
                } else {
                    System.out.println("UHHH OHHH");
                }

            }
        }

        scanString.close();
    } // promptUser



    public ChessBoard getBoard() {
        return this.board;
    }

    public boolean blackInCheck() {
        int blackX = 0;
        int blackY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i,j) instanceof King) && (board.getPiece(i, j).getWhite() == false)) {
                        blackX = i;
                        blackY = j;
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i, j).getWhite() == true) && (nothingInWay(i, j, blackX, blackY) == true) && (board.getPiece(i, j).canCapture(blackX, blackY))) {
                        System.out.println("CHECK!!!!!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean whiteInCheck() {
        int whiteX = 0;
        int whiteY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i,j) instanceof King) && (board.getPiece(i, j).getWhite() == true)) {
                        whiteX = i;
                        whiteY = j;
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i, j).getWhite() == false) && (nothingInWay(i, j, whiteX, whiteY) == true) && (board.getPiece(i, j).canCapture(whiteX, whiteY))) {
                        System.out.println("CHECK!!!!!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isInCheck() {
        int whiteX = 0;
        int whiteY = 0;
        int blackX = 0;
        int blackY = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i,j) instanceof King) && (board.getPiece(i, j).getWhite() == true)) {
                        whiteX = i;
                        whiteY = j;
                    } else if ((board.getPiece(i,j) instanceof King) && (board.getPiece(i, j).getWhite() == false)) {
                        blackX = i;
                        blackY = j;
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i, j).getWhite() == false) && (nothingInWay(i, j, whiteX, whiteY) == true) && (board.getPiece(i, j).canCapture(whiteX, whiteY))) {
                        System.out.println("CHECK!!!!!");
                        return true;
                    } else if ((board.getPiece(i, j).getWhite() == true) && (nothingInWay(i, j, blackX, blackY) == true) && (board.getPiece(i, j).canCapture(blackX, blackY))) {
                        System.out.println("CHECK!!!!!!!!!!!!!!!!!!!!!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Finalizes the intended move for a piece.
     *
     * @param origin the starting square
     * @param dest the destination square
     * @param thePiece the piece that is performing the movement
     * @param toX the final x position
     * @param toY the final y position
     */
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

    /**
     * Determines whether there is a piece in the way for a calling piece's movement.
     *
     * @param fromX the starting x position of the calling {@code Piece}
     * @param fromY the starting y position of the calling {@code Piece}
     * @param toX the final x position of the calling {@code Piece}
     * @param toY the final y position of the calling {@code Piece}
     * @return
     */
    public boolean nothingInWay(int fromX, int fromY, int toX, int toY) {
        Piece thePiece = board.getPiece(fromX, fromY);
        int tempX;
        int tempY;
        if (thePiece instanceof Bishop) {
            return bishopWay(fromX, fromY, toX, toY);
        } else if (thePiece instanceof Rook) {
            return rookWay(fromX, fromY, toX, toY);
        } else if (thePiece instanceof Queen) {
            if ((fromX == toX) || (fromY == toY)) {
                return rookWay(fromX, fromY, toX, toY);
            } else {
                return bishopWay(fromX, fromY, toX, toY);
            }
        } else if (thePiece instanceof Pawn) {
            return true;
        } else {
            return true;
        }

    } // nothingInWay

    /**
     * Determines whether there is a piece in the way for a bishop's movement.
     *
     * @param fromX the starting x position of the calling {@code Bishop}
     * @param fromY the starting y position of the calling {@code Bishop}
     * @param toX the final x position of the calling {@code Bishop}
     * @param toY the final y position of the calling {@code Bishop}
     * @return
     */
    private boolean bishopWay(int fromX, int fromY, int toX, int toY) {
        int tempX = fromX;
        int tempY = fromY;
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
        return true;
    } // bishopWay

    /**
     * Determines whether there is a piece in the way for a rook's movement.
     *
     * @param fromX the starting x position of the calling {@code Rook}
     * @param fromY the starting y position of the calling {@code Rook}
     * @param toX the final x position of the calling {@code Rook}
     * @param toY the final y position of the calling {@code Rook}
     * @return
     */
    private boolean rookWay(int fromX, int fromY, int toX, int toY) {
        int tempX = fromX;
        int tempY = fromY;
        if (tempX == toX) {
            if (fromY > toY) {
                while (tempY > toY) {
                    tempY--;
                    if (tempY != toY) {
                        if (board.getPiece(tempX, tempY) != null) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                while (tempY < toY) {
                    tempY++;
                    if (tempY != toY) {
                        if (board.getPiece(tempX, tempY) != null) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            }
        } else if (tempY == toY) {
            if (fromX > toX) {
                while (tempX > toX) {
                    tempX--;
                    if (tempX != toX) {
                        if (board.getPiece(tempX, tempY) != null) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                while (tempX < toX) {
                    tempX++;
                    if (tempX != toX) {
                        if (board.getPiece(tempX, tempY) != null) {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return true;
    } // rookWay

    /**
     * Method that allows for the creation of a new {@code Thread} to operate multiple parts of the
     * code simultaneously.
     *
     * @param runnable an object that implements {@code Runnable} or is runnable
     */
    private static void runNow(Runnable runnable) throws InterruptedException {
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();
    } // runNow
    // isincheck - check each opposite color piece if it can attack the opposite king with attack method

    public void checkStatus() {
        /*
        while (true) {
            if (isInCheck() == true) {
                System.out.println("LOOPING");
                inCheck = true;
            }
        }

         */
    }

    public void newBoard() {
        this.board = new ChessBoard();
        isWhiteTurn = true;
        inCheck = false;
    }

    public boolean getInCheck() {
        return this.inCheck;
    }

    public boolean nextMoveCheck(Square origin, Square dest, Piece thePiece, int toX, int toY) {
        int originalX = thePiece.getX();
        int originalY = thePiece.getY();
        Piece destPiece = dest.getPiece();
        dest.setPiece(thePiece); // 1
        origin.setPiece(null); // 2
        thePiece.setX(toX); // 3
        thePiece.setY(toY); // 4
        if (isInCheck() == true) {
            if (destPiece != null) {
                dest.setPiece(destPiece); // 1
            } else {
                dest.setPiece(null);
            }
            origin.setPiece(thePiece); // 2
            thePiece.setX(originalX); // 3
            thePiece.setY(originalY); // 4
            System.out.println("ILLEGAL MOVE BY NEXTMOVECHECK");
            return true;
        }
        return false;
    }

    public void undoMove() {

    }

    public boolean getWhiteTurn() {
        return this.isWhiteTurn;
    } // getTurn

    public boolean calculateCheckmate() {

        return true;
    }

    public int getWhiteScore() {
        return this.whiteScore;
    }

    public int getBlackScore() {
        return this.blackScore;
    }

    public void changeScores(Square toSquare) {
        if (toSquare.getPiece() != null) {
            if (toSquare.getPiece().getWhite() == false) {
                if (toSquare.getPiece() instanceof Pawn) {
                    whiteScore++;
                } else if ((toSquare.getPiece() instanceof Knight) || (toSquare.getPiece() instanceof Bishop)) {
                    whiteScore = whiteScore + 3;
                } else if (toSquare.getPiece() instanceof Rook) {
                    whiteScore = whiteScore + 5;
                } else if (toSquare.getPiece() instanceof Queen) {
                    whiteScore = whiteScore + 9;
                }
            } else {
                if (toSquare.getPiece() instanceof Pawn) {
                    blackScore++;
                } else if ((toSquare.getPiece() instanceof Knight) || (toSquare.getPiece() instanceof Bishop)) {
                    blackScore = blackScore + 3;
                } else if (toSquare.getPiece() instanceof Rook) {
                    blackScore = blackScore + 5;
                } else if (toSquare.getPiece() instanceof Queen) {
                    blackScore = blackScore + 9;
                }
            }
        }
        System.out.print("WHITE " + whiteScore);
        System.out.print("BLACK " + blackScore);
    } // changeScores

    public void resetScores() {
        whiteScore = 0;
        blackScore = 0;
    }
}
