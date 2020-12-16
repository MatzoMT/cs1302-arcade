package cs1302.arcade;

/**
 * Defines the attributes of a chess piece in a chess game. The {@code Piece} class defines
 * different methods and values a {@code Piece} can have.
 */
public abstract class Piece {
    private Square square;
    private int x;
    private int y;
    private boolean isWhite = true;

    /**
     * Constructor for the {@code Piece}.
     *
     * @param isWhiteColor a {@code boolean} representing whether the piece is white or not
     * @param theX the x coordinate of the piece on the board represented as a 2D array
     * @param theY the y coordinate of the piece on the board represented as a 2D array
     */
    public Piece(boolean isWhiteColor, int theX, int theY) {
        this.isWhite = isWhiteColor;
        this.x = theX;
        this.y = theY;
    } // Piece

/** Determines whether the piece is able to move to the desired square according to the game
     * rules and situation.
     *
     * @param toX the destination x coordinate
     * @param toY the destination y coordinate
     * @return true if the piece can move to the destination square
     */
    public abstract boolean canMoveTo(int toX, int toY);


    /** Determines whether the piece is able to capture another piece on the desired square
     * according to the game rules and situation.
     *
     * @param toX the x coordinate of the piece to capture
     * @param toY the y coordinate of the piece to capture
     * @return true if the piece can capture the piece on the destination square
     */
    public boolean canCapture(int toX, int toY) {
        if (this.canMoveTo(toX, toY) == true) {
            return true;
        } else {
            return false;
        }
    } // canCapture



    /**
     * Returns the x coordinate of the calling {@code Piece}.
     *
     * @return the x coordinate
     */
    public int getX() {
        return this.x;
    } // getX

    /**
     * Returns the y coordinate of the calling {@code Piece}.
     *
     * @return the y coordinate
     */
    public int getY() {
        return this.y;
    } // getY

    /**
     * Returns the color of the calling {@code Piece}.
     *
     * @return true if the piece is white
     */
    public boolean getWhite() {
        return this.isWhite;
    } // getWhite

    /**
     * Sets the x coordinate of the calling {@code Piece}.
     *
     * @param toX the new x coordinate
     */
    public void setX(int toX) {
        this.x = toX;
    }

    /**
     * Sets the y coordinate of the calling {@code Piece}.
     *
     * @param toY the new x coordinate
     */
    public void setY(int toY) {
        this.y = toY;
    }

    /**
     * Sets the color of the calling {@code Piece}.
     *
     * @param white color to set the piece to (true if white)
     */
    public void setWhite(boolean white) {
        this.isWhite = white;
    } // setWhite

} // Piece
