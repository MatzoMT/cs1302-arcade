package cs1302.arcade;

/**
 * Defines a specific square of the chess board and its attributes, which update during gameplay.
 */
public class Square {
    private Piece piece;
    private int x;
    private int y;

    /**
     * Constructor for the {@code Square} that defines the x and y coordinates of the square and the
     * {@code Piece} on the square.
     *
     * @param thePiece the piece to be on the square
     * @param theX the x coordinate of the square
     * @param theY the y coordinate of the square
     */
    public Square(Piece thePiece, int theX, int theY) {
        this.piece = thePiece;
        this.x = theX;
        this.y = theY;
    } // Square

    /**
     * Returns the {@code Piece} at this {@code Square}.
     *
     * @return the {@code Piece} located on this {@code Square}
     */
    public Piece getPiece() {
        return this.piece;
    } // getPiece

    /**
     * Returns the {@code x} coordinate of this {@code Square} based on a 2D array where the top
     * left square (A8) of the board is [0][0].
     *
     * @return the {@code x} of this {@code Square}
     */
    public int getX() {
        return this.x;
    } // getX

    /**
     * Returns the {@code y} coordinate of this {@code Square} based on a 2D array where the top
     * left square (A8) of the board is [0][0].
     *
     * @return the {@code y} of this {@code Square}
     */
    public int getY() {
        return this.y;
    } // getY

    /**
     * Sets the {@code Piece} that is located on the calling {@code Square}.
     *
     * @param newPiece the {@code Piece} to set on the {@code Square}
     */
    public void setPiece(Piece newPiece) {
        this.piece = newPiece;
    } // setPiece

    /**
     * Sets the {@code x} that is located on the calling {@code Square}.
     *
     * @param newX the {@code x} to set on the {@code Square}
     */
    public void setX(int newX) {
        this.x = newX;
    } // setX

    /**
     * Sets the {@code y} that is located on the calling {@code Square}.
     *
     * @param newY the {@code y} to set on the {@code Square}
     */
    public void setY(int newY) {
        this.y = newY;
    } // setY

} // Square
