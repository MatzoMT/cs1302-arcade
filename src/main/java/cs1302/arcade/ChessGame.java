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
        // Scanner objects parses each subsequent int as fromX, fromY, toX, toY
        Scanner scanString = new Scanner(goodMove);
        int fromX = scanString.nextInt();
        int fromY = scanString.nextInt();
        int toX = scanString.nextInt();
        int toY = scanString.nextInt();
        scanString.close();
        Piece thePiece = board.getPiece(fromX, fromY);
        Square theSquare = board.getSquare(fromX, fromY);
        Square toSquare = board.getSquare(toX, toY);
        // Checks whether the selected square for the move actually contains a piece
        if (thePiece != null) {
            // Runs doPawnMove to handle pawn movement if pawn is selected
            if (thePiece instanceof Pawn) {
                doPawnMove(theSquare, toSquare, thePiece, toX, toY);
                // Checks whether the piece has room to move to the destination square
            } else if (nothingInWay(fromX, fromY, toX, toY) == true) {
                // if statement for handling a white piece selected and on white to move
                if ((thePiece.getWhite() == true) && (isWhiteTurn == true) &&
                        ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
                    // Checks if the destination square is empty
                    if ((toSquare.getPiece() == null)) {
                        // Move is performed if moving player does not place themselves in check
                        if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                            confirmMove(theSquare, toSquare, thePiece, toX, toY);
                        }
                    } else {
                        // Checks if the piece at the destination square is of an opposite color
                        if (toSquare.getPiece().getWhite() == false) {
                            if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                                confirmMove(theSquare, toSquare, thePiece, toX, toY);
                            }
                        }
                    }
                } else if ((thePiece.getWhite() == false) && (isWhiteTurn == false) &&
                        ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
                    // Checks if the destination square is empty
                    if (toSquare.getPiece() == null) {
                        // Move is performed if moving player does not place themselves in check
                        if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                            confirmMove(theSquare, toSquare, thePiece, toX, toY);
                        }
                    } else {
                        // Checks if the piece at the destination square is of an opposite color
                        if (toSquare.getPiece().getWhite() == true) {
                            if (nextMoveCheck(theSquare, toSquare, thePiece, toX, toY) == false) {
                                confirmMove(theSquare, toSquare, thePiece, toX, toY);
                            } // if
                        } // if
                    } // if-else
                } // if-else
            } // if-else
        } // if-else
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
        // Nested for loops that go through each square on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Nested if statements that check whether the reached square contains black king
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i,j) instanceof King) &&
                            (board.getPiece(i, j).getWhite() == false)) {
                        // Assigns coordinates of black king
                        blackX = i;
                        blackY = j;
                    }
                }
            }
        }
        // Nested for loops that go through each square on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Nested if statements that check whether the reached square contains a white
                // piece that can attack the black king
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i, j).getWhite() == true) &&
                            (nothingInWay(i, j, blackX, blackY) == true) &&
                            (board.getPiece(i, j).canCapture(blackX, blackY))) {
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
        // Nested for loops that go through each square on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Nested if statements that check whether the reached square contains white king
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i,j) instanceof King) &&
                            (board.getPiece(i, j).getWhite() == true)) {
                        // Assigns coordinates of white king
                        whiteX = i;
                        whiteY = j;
                    }
                }
            }
        }
        // Nested for loops that go through each square on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Nested if statements that check whether the reached square contains a black
                // piece that can attack the white king
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i, j).getWhite() == false) &&
                            (nothingInWay(i, j, whiteX, whiteY) == true) &&
                            (board.getPiece(i, j).canCapture(whiteX, whiteY))) {
                        return true;
                    }
                }
            }
        }
        return false;
    } // whiteInCheck

    /**
     * Calculates whether any kings on the board are under attack by pieces of the opposing color.
     *
     * @return true if the white or black king is under attack by the opposite color
     */
    public boolean isInCheck() {
        int whiteX = 0;
        int whiteY = 0;
        int blackX = 0;
        int blackY = 0;
        // Nested for loops that go through each square on the chess board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // if statements that check whether the square contains the white or black king
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i,j) instanceof King) &&
                            (board.getPiece(i, j).getWhite() == true)) {
                        whiteX = i;
                        whiteY = j;
                    } else if ((board.getPiece(i,j) instanceof King) &&
                            (board.getPiece(i, j).getWhite() == false)) {
                        blackX = i;
                        blackY = j;
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // if statements that check whether a piece is able to attack the opposite king
                if (board.getPiece(i, j) != null) {
                    if ((board.getPiece(i, j).getWhite() == false) &&
                            (nothingInWay(i, j, whiteX, whiteY) == true) &&
                            (board.getPiece(i, j).canCapture(whiteX, whiteY))) {
                        return true;
                    } else if ((board.getPiece(i, j).getWhite() == true) &&
                            (nothingInWay(i, j, blackX, blackY) == true) &&
                            (board.getPiece(i, j).canCapture(blackX, blackY))) {
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
        // Updates the moves
        changeScores(dest);
        // Sets the piece at the destination square to be the piece performing the move
        dest.setPiece(thePiece);
        // Removes the piece from its starting square
        origin.setPiece(null);
        // if statements that swap the turn of the colors
        if (isWhiteTurn == true) {
            isWhiteTurn = false;
        } else {
            isWhiteTurn = true;
        }
        // Updates piece coordinates
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
        // if-else statements that check the type of piece performing the move and calls the
        // appropriate method checking if it can move to destination square
        if (thePiece instanceof Bishop) {
            return bishopWay(fromX, fromY, toX, toY);
        } else if (thePiece instanceof Rook) {
            return rookWay(fromX, fromY, toX, toY);
        } else if (thePiece instanceof Queen) {
            // Calls the rookWay or bishopWay methods depending on whether the Queen move
            // represents a rook or bishop's movement
            if ((fromX == toX) || (fromY == toY)) {
                return rookWay(fromX, fromY, toX, toY);
            } else {
                return bishopWay(fromX, fromY, toX, toY);
            }
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
        // Determines if bishop is moving to the top-right in relation to current position
        if ((fromX > toX) && (fromY > toY)) {
            // while loop that runs until destination square is reached
            while ((tempX > toX) && (tempY > toY)) {
                tempX--;
                tempY--;
                // Checks if the square is not the intended square and if it contains a piece
                if ((tempX != toX) || (tempY != toY)) {
                    if (board.getPiece(tempX, tempY) != null) {
                        return false;
                    }
                } else {
                    return true;
                }
            }
            // Same as above comments for top-right movement in relation to current position
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
            // Same as above comments for bottom-right movement in relation to current position
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
            // Same as above comments for bottom-left movement in relation to current position
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
        // if statement that checks for horizontal movement
        if (tempX == toX) {
            // if statement that checks for left or right movement
            if (fromY > toY) {
                while (tempY > toY) {
                    tempY--;
                    // if statement that checks if the final square is not yet reached and if the
                    // current square contains a piece
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
            // Same as above comments for top-down movement processing
        } else if (tempY == toY) {
            // if statement that checks for leftward movement
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
        // Simulates confirmMove method before seeing if it results in check
        dest.setPiece(thePiece); // 1
        origin.setPiece(null); // 2
        thePiece.setX(toX); // 3
        thePiece.setY(toY); // 4
        // Checks whether the moving piece puts the king of the same color in check
        if (((thePiece.getWhite() == false) && (blackInCheck() == true)) ||
                ((thePiece.getWhite() == true) && (whiteInCheck() == true))) {
            // Checks whether performing the move places the king in check
            if (isInCheck() == true) {
                // Undos the state of the destination square
                if (destPiece != null) {
                    dest.setPiece(destPiece); // 1
                } else {
                    dest.setPiece(null);
                }
                // Undos any movement by thePiece
                origin.setPiece(thePiece); // 2
                thePiece.setX(originalX); // 3
                thePiece.setY(originalY); // 4
                return true;
            }
        }
        // Undos the state of the destination square
        if (destPiece != null) {
            dest.setPiece(destPiece); // 1
        } else {
            dest.setPiece(null);
        }
        // Undos any movement by thePiece
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
        // Nested for loops that initialize the x and y coordinate of all pieces on the board
        for (int pieceX = 0; pieceX < 8; pieceX++) {
            for (int pieceY = 0; pieceY < 8; pieceY++) {
                Piece thePiece = board.getPiece(pieceX, pieceY);
                Square origin = board.getSquare(pieceX, pieceY);
                // Checks whether there is a piece at pieceX and pieceY on the board and if black
                if ((thePiece != null) && (thePiece.getWhite() == false)) {
                    // Nested for loops that represent all other squares for thePiece to
                    // potentially move to
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (board.getPiece(i, j) == null) {
                                // Checks if a black piece's move can stop check
                                if ((thePiece.canMoveTo(i, j)) || (thePiece.canCapture(i, j))) {
                                    if (nextMoveCheck(origin, board.getSquare(i, j),
                                            thePiece, i, j) == false) {
                                        return false;
                                    }
                                }
                                // Checks if the attacking piece can be captured
                            } else if (board.getPiece(i, j).getWhite() == true) {
                                if ((thePiece.canMoveTo(i, j)) || (thePiece.canCapture(i, j))) {
                                    if (nextMoveCheck(origin, board.getSquare(i, j),
                                            thePiece, i, j) == false) {
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
        // Nested for loops that initialize the x and y coordinate of all pieces on the board
        for (int pieceX = 0; pieceX < 8; pieceX++) {
            for (int pieceY = 0; pieceY < 8; pieceY++) {
                Piece thePiece = board.getPiece(pieceX, pieceY);
                Square origin = board.getSquare(pieceX, pieceY);
                // Checks whether there is a piece at pieceX and pieceY on the board and if black
                if ((thePiece != null) && (thePiece.getWhite() == true)) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            // Nested for loops that represent all other squares for thePiece to
                            // potentially move to
                            if (board.getPiece(i, j) == null) {
                                // Checks if a white piece's move can stop check
                                if ((thePiece.canMoveTo(i, j)) || (thePiece.canCapture(i, j))) {
                                    if (nextMoveCheck(origin, board.getSquare(i, j),
                                            thePiece, i, j) == false) {
                                        return false;
                                    }
                                }
                                // Checks if the attacking piece can be captured
                            } else if (board.getPiece(i, j).getWhite() == false) {
                                if ((thePiece.canMoveTo(i, j)) || (thePiece.canCapture(i, j))) {
                                    if (nextMoveCheck(origin, board.getSquare(i, j),
                                            thePiece, i, j) == false) {
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
        // Checks whether the destination square contains a piece to be captured
        if (toSquare.getPiece() != null) {
            // Checks if the piece is black
            if (toSquare.getPiece().getWhite() == false) {
                // if-else statements that increment white's score based on the type of piece
                // captured
                if (toSquare.getPiece() instanceof Pawn) {
                    whiteScore++;
                } else if ((toSquare.getPiece() instanceof Knight) || (toSquare.getPiece()
                        instanceof Bishop)) {
                    whiteScore = whiteScore + 3;
                } else if (toSquare.getPiece() instanceof Rook) {
                    whiteScore = whiteScore + 5;
                } else if (toSquare.getPiece() instanceof Queen) {
                    whiteScore = whiteScore + 9;
                }
                // Checks if the piece is white
            } else {
                // if-else statements that increment white's score based on the type of piece
                // captured
                if (toSquare.getPiece() instanceof Pawn) {
                    blackScore++;
                } else if ((toSquare.getPiece() instanceof Knight) || (toSquare.getPiece()
                        instanceof Bishop)) {
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
        // Checks if the piece at focus is white and on white to move
        if ((thePiece.getWhite() == true) && (isWhiteTurn == true) &&
                ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
            // Checks whether the white pawn is moving diagonally by one square
            if ((Math.abs(toX - thePiece.getX()) == 1) && (Math.abs(toY - thePiece.getY()) == 1)) {
                if ((toSquare.getPiece() != null) && (toSquare.getPiece().getWhite() == false)) {
                    confirmMove(theSquare, toSquare, thePiece, toX, toY);
                }
                // Checks if the white pawn is moving up the board without attacking a piece
            } else if ((toX < thePiece.getX()) && (toSquare.getPiece() == null)) {
                confirmMove(theSquare, toSquare, thePiece, toX, toY);
            }
            // Checks if the piece at focus is black and on black to move
        } else if ((thePiece.getWhite() == false) && (isWhiteTurn == false) &&
                ((thePiece.canMoveTo(toX, toY)) || (thePiece.canCapture(toX, toY)))) {
            // Checks whether the black pawn is moving diagonally by one square
            if ((Math.abs(toX - thePiece.getX()) == 1) && (Math.abs(toY - thePiece.getY()) == 1)) {
                if ((toSquare.getPiece() != null) && (toSquare.getPiece().getWhite() == true)) {
                    confirmMove(theSquare, toSquare, thePiece, toX, toY);
                }
                // Checks if the black pawn is moving down the board without attacking a piece
            } else if ((toX > thePiece.getX()) && (toSquare.getPiece() == null)) {
                confirmMove(theSquare, toSquare, thePiece, toX, toY);
            }
        }
    } // doPawnMove
}
