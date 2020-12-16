package cs1302.arcade;

import java.util.Scanner;

/**
 * Defines methods and gameplay mechanics for running a game of chess.
 */
public class ChessGame {
    private boolean isWhiteTurn = true;
    private boolean inCheck;
    private ChessBoard board = new ChessBoard();
    private int whiteScore = 0;
    private int blackScore = 0;

    /**
     * Processes the mouse clicks by the user on the GUI into a move on the chess board.
     * @param goodMove a {@code String} containing 4 values of type {@code int} to process as
     *                 fromX, fromY, toX, and toY
     */
    public void promptUser(String goodMove) {
        Scanner scanString = new Scanner(goodMove);
        int fromX = scanString.nextInt();
        int fromY = scanString.nextInt();
        int toX = scanString.nextInt();
        int toY = scanString.nextInt();
        scanString.close();
        Piece thePiece = board.getPiece(fromX, fromY);
        Square theSquare = board.getSquare(fromX, fromY);
        Square toSquare = board.getSquare(toX, toY);
        if (thePiece != null) {
            if (thePiece instanceof Pawn) {
                doPawnMove(theSquare, toSquare, thePiece, toX, toY);
            } else if (nothingInWay(fromX, fromY, toX, toY) == true) {
                if ((thePiece.getWhite() == true) && (isWhiteTurn == true) &&
                ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
                    if ((toSquare.getPiece() == null)) {
                        if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                            confirmMove(theSquare, toSquare, thePiece, toX, toY);
                        }
                    } else {
                        if (toSquare.getPiece().getWhite() == false) {
                            if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                                confirmMove(theSquare, toSquare, thePiece, toX, toY);
                            }
                        }
                    }
                } else if ((thePiece.getWhite() == false) && (isWhiteTurn == false) &&
                ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
                    if (toSquare.getPiece() == null) {
                        if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                            confirmMove(theSquare, toSquare, thePiece, toX, toY);
                        }
                    } else {
                        if (toSquare.getPiece().getWhite() == true) {
                            if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                                confirmMove(theSquare, toSquare, thePiece, toX, toY);
                            }
                        }
                    }
                }
            }
        }
    } // promptUser

    /**
     * Returns the chessboard being used for this instance of a chess game.
     *
     * @return {@code ChessBoard} object representing the board being used to play
     */
    public ChessBoard getBoard() {
        return this.board;
    } // getBoard

    /**
     * Calculates whether the black player is in check by seeing whether there are white pieces
     * that are attacking the black king.
     *
     * @return true if there are white pieces attacking the black king
     */
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
                        return true;
                    }
                }
            }
        }
        return false;
    } // blackInCheck

    /**
     * Calculates whether the white player is in check by seeing whether there are black pieces
     * that are attacking the white king.
     *
     * @return true if there are black pieces attacking the white king
     */
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
                        return true;
                    }
                }
            }
        }
        return false;
    } // whiteInCheck

    /**
     * Calculates whether any of the kings on the board are under attack by pieces of the opposing color.
     *
     * @return true if the white or black king is under attack by the opposite color
     */
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
                        return true;
                    } else if ((board.getPiece(i, j).getWhite() == true) && (nothingInWay(i, j, blackX, blackY) == true) && (board.getPiece(i, j).canCapture(blackX, blackY))) {
                        return true;
                    }
                }
            }
        }
        return false;
    } // isInCheck

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
        changeScores(dest);
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
     * @return true if the calling piece has no pieces on the way to the destination coordinates
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
     * @return true if there is no piece on the way to toX and toY; false if a piece is in the way
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
     * @return true if there is no piece on the way to toX and toY; false if a piece is in the way
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

    /**
     * Resets the conditions of the board to starting conditions, where it is white to move and no
     * player is in check.
     */
    public void newBoard() {
        this.board = new ChessBoard();
        isWhiteTurn = true;
        inCheck = false;
    } // newBoard

    /**
     * Calculates whether the intended move by the user results in the same user going into check,
     * which constitutes an illegal move.
     *
     * @param origin the square containing the piece intending to move
     * @param dest the square that the piece intends to move to
     * @param thePiece the piece intending to move
     * @param toX the x coordinate of the destination square on the board represented as a 2D array
     * @param toY the y coordinate of the destination square on the board represented as a 2D array
     * @return true if the next move results in the user putting themselves into check
     */
    public boolean nextMoveCheck(Square origin, Square dest, Piece thePiece, int toX, int toY) {
        int originalX = thePiece.getX();
        int originalY = thePiece.getY();
        Piece destPiece = dest.getPiece();
        dest.setPiece(thePiece); // 1
        origin.setPiece(null); // 2
        thePiece.setX(toX); // 3
        thePiece.setY(toY); // 4
        if (((thePiece.getWhite() == false) && (blackInCheck() == true)) ||
        ((thePiece.getWhite() == true) && (whiteInCheck() == true))) {
            if (isInCheck() == true) {
                if (destPiece != null) {
                    dest.setPiece(destPiece); // 1
                } else {
                    dest.setPiece(null);
                }
                origin.setPiece(thePiece); // 2
                thePiece.setX(originalX); // 3
                thePiece.setY(originalY); // 4
                return true;

            }
        }
        if (destPiece != null) {
            dest.setPiece(destPiece); // 1
        } else {
            dest.setPiece(null);
        }
        origin.setPiece(thePiece); // 2
        thePiece.setX(originalX); // 3
        thePiece.setY(originalY); // 4
        return false;
    } // nextMoveCheck

    /**
     * Returns whether it is white to move.
     *
     * @return true if it is white to move
     */
    public boolean getWhiteTurn() {
        return this.isWhiteTurn;
    } // getTurn

    /**
     * Returns whether black is calculated to be in checkmate. Checkmate is determined if the black
     * player is in check and has no valid move that gets them out of check.
     *
     * @return true if the black player has been checkmated
     */
    public boolean blackInCheckmate() {
        for (int pieceX = 0; pieceX < 8; pieceX++) {
            for (int pieceY = 0; pieceY < 8; pieceY++) {
                Piece thePiece = board.getPiece(pieceX, pieceY);
                Square origin = board.getSquare(pieceX, pieceY);
                if ((thePiece != null) && (thePiece.getWhite() == false)) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (board.getPiece(i, j) == null) {
                                if ((thePiece.canMoveTo(i, j)) || (thePiece.canCapture(i, j))) {
                                    if (nextMoveCheck(origin, board.getSquare(i, j), thePiece, i, j) == false) {
                                        System.out.println(pieceX + " " + pieceY + " " + i + " " + j);
                                        return false;
                                    }
                                }
                            } else if (board.getPiece(i, j).getWhite() == true) {
                                if ((thePiece.canMoveTo(i, j)) || (thePiece.canCapture(i, j))) {
                                    if (nextMoveCheck(origin, board.getSquare(i, j), thePiece, i, j) == false) {
                                        System.out.println(pieceX + " " + pieceY + " " + i + " " + j);
                                        return false;
                                    }
                                }
                            }
                        } // for
                    } // for
                }
            }
        }
        return true;
    }

    /**
     * Returns whether white is calculated to be in checkmate. Checkmate is determined if the white
     * player is in check and has no valid move that gets them out of check.
     *
     * @return true if the white player has been checkmated
     */
    public boolean whiteInCheckmate() {
        for (int pieceX = 0; pieceX < 8; pieceX++) {
            for (int pieceY = 0; pieceY < 8; pieceY++) {
                Piece thePiece = board.getPiece(pieceX, pieceY);
                Square origin = board.getSquare(pieceX, pieceY);
                if ((thePiece != null) && (thePiece.getWhite() == true)) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (board.getPiece(i, j) == null) {
                                if ((thePiece.canMoveTo(i, j)) || (thePiece.canCapture(i, j))) {
                                    if (nextMoveCheck(origin, board.getSquare(i, j), thePiece, i, j) == false) {
                                        return false;
                                    }
                                }
                            } else if (board.getPiece(i, j).getWhite() == false) {
                                if ((thePiece.canMoveTo(i, j)) || (thePiece.canCapture(i, j))) {
                                    if (nextMoveCheck(origin, board.getSquare(i, j), thePiece, i, j) == false) {
                                        return false;
                                    }
                                }
                            }
                        } // for
                    } // for
                }
            }
        }
        return true;
    }

    /**
     * Returns the score for white.
     *
     * @return the white player's score
     */
    public int getWhiteScore() {
        return this.whiteScore;
    }

    /**
     * Returns the score for black.
     *
     * @return the black player's score
     */
    public int getBlackScore() {
        return this.blackScore;
    }

    /**
     * Changes the white or black player's score based on what piece is being captured. The score
     * for each capturable piece is 1 for pawns, 3 for knights and bishops, 5 for rooks, and 9
     * for queens.
     *
     * @param toSquare the square containing the piece being captured
     */
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

    } // changeScores

    /**
     * Changes the white and black players' scores to 0.
     */
    public void resetScores() {
        whiteScore = 0;
        blackScore = 0;
    } // resetScores

    /**
     * Handles move processing for a pawn, which is the only piece that has a capturing mechanism
     * different from its movement mechanism.
     *
     * @param theSquare the square where the pawn intending on moving is located
     * @param toSquare the square where the pawn intends to move to
     * @param thePiece the pawn intending on moving
     * @param toX the x coordinate of the destination square on the board represented as a 2D array
     * @param toY the y coordinate of the destination square on the board represented as a 2D array
     */
    private void doPawnMove(Square theSquare, Square toSquare, Piece thePiece, int toX, int toY) {
        if ((thePiece.getWhite() == true) && (isWhiteTurn == true) &&
        ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
            if ((Math.abs(toX - thePiece.getX()) == 1) && (Math.abs(toY - thePiece.getY()) == 1)) {
                if ((toSquare.getPiece() != null) && (toSquare.getPiece().getWhite() == false)) {
                    confirmMove(theSquare, toSquare, thePiece, toX, toY);
                }
            } else if ((toX < thePiece.getX()) && (toSquare.getPiece() == null)) {
                confirmMove(theSquare, toSquare, thePiece, toX, toY);
            }
        } else if ((thePiece.getWhite() == false) && (isWhiteTurn == false) &&
        ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
            if ((Math.abs(toX - thePiece.getX()) == 1) && (Math.abs(toY - thePiece.getY()) == 1)) {
                if ((toSquare.getPiece() != null) && (toSquare.getPiece().getWhite() == true)) {
                    confirmMove(theSquare, toSquare, thePiece, toX, toY);
                }
            } else if ((toX > thePiece.getX()) && (toSquare.getPiece() == null)) {
                confirmMove(theSquare, toSquare, thePiece, toX, toY);
            }
        }
    } // doPawnMove
}
