package cs1302.arcade;

/**
 * Defines a King chess piece in a chess game.
 */
public class Queen extends Piece {

    /**
     * Constructor for the {@code Queen}.
     *
     * @param isWhiteColor a {@code boolean} representing whether the piece is white or not
     * @param theX the x coordinate to place the calling piece
     * @param theY the y coordinate to place the calling piece
     */
    public Queen(boolean isWhiteColor, int theX, int theY) {
        super(isWhiteColor, theX, theY);
    } // Piece

    /** {@inheritDoc} */
    public boolean canMoveTo(int toX, int toY) {
        int absX = Math.abs(this.getX() - toX);
        int absY = Math.abs(this.getY() - toY);
        // Checks whether there is equal diagonal movement by the same amount of squares in x
        // and y
        if (absX - absY == 0) {
            return true;
            // Checks whether there is movement in x or y direction only implying rook movement
        } else if ((absX >= 1) && (absY == 0)) {
            return true;
        } else if ((absY >= 1) && (absX == 0)) {
            return true;
        } else {
            return false;
        }
    } // canMoveTo

} // Queen
