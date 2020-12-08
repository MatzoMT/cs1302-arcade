package cs1302.arcade;

/**
 * Represents the state of the chessboard.
 */
public class ChessBoard {
    private Square[][] board = new Square[8][8];
    private King whiteKing = new King(false, 7, 4);
    private King blackKing = new King(true, 0 ,4);
    /**
     * Constructor for the chessboard that initializes all chess pieces at original positions.
     */
    public ChessBoard() {
        // black pieces
        board[0][0] = new Square(new Rook(false, 0, 0), 0, 0);
        board[0][7] = new Square(new Rook(false, 0, 7), 0, 7);
        board[0][1] = new Square(new Knight(false, 0, 1), 0, 1);
        board[0][6] = new Square(new Knight(false, 0, 6), 0, 6);
        board[0][2] = new Square(new Bishop(false, 0, 2), 0, 2);
        board[0][5] = new Square(new Bishop(false, 0, 5), 0 , 5);
        board[0][3] = new Square(new Queen(false, 0, 3), 0, 3);
        board[0][4] = new Square(blackKing, 0, 4);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Square(new Pawn(false, 1, i), 1, i);
        } // for

        // white
        board[7][0] = new Square(new Rook(true, 7, 0), 7, 0);
        board[7][7] = new Square(new Rook(true, 7, 7), 7, 7);
        board[7][1] = new Square(new Knight(true, 7, 1), 7, 1);
        board[7][6] = new Square(new Knight(true, 7, 6), 7, 6);
        board[7][2] = new Square(new Bishop(true, 7, 2), 7, 2);
        board[7][5] = new Square(new Bishop(true, 7, 5), 7, 5);
        board[7][3] = new Square(new Queen(true, 7, 3), 7, 3);
        board[7][4] = new Square(whiteKing, 7, 4);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Square(new Pawn(true, 6, i), 6, i);
        } // for

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(null, i, j);
            } // for
        } // for
    } // ChessBoard

    public Square getSquare(int atX, int atY) {
        return board[atX][atY];
    } // getSquare

    public Piece getPiece(int atX, int atY) {
        return this.getSquare(atX, atY).getPiece();
    } // getPiece

    public King getWhiteKing() {
        return this.whiteKing;
    }

    public King getBlackKing() {
        return this.blackKing;
    }
} // ChessBoard
