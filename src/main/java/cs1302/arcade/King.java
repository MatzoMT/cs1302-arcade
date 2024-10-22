package cs1302.arcade;

/**
 * Defines a King chess piece in a chess game.
 */
public class King extends Piece {

    boolean hasMoved = false;
    boolean canCastle = true;

    /**
     * Constructor for the {@code King}.
     *
     * @param isWhiteColor a {@code boolean} representing whether the piece is white or not
     * @param theX the x coordinate to place the calling piece
     * @param theY the y coordinate to place the calling piece
     */
    public King(boolean isWhiteColor, int theX, int theY) {
        super(isWhiteColor, theX, theY);
    } // Piece

    /** {@inheritDoc} */
    public boolean canMoveTo(int toX, int toY) {
        int absX = Math.abs(this.getX() - toX);
        int absY = Math.abs(this.getY() - toY);
        // Checks whether there is only 1 square of movement in the x or y direction
        if (absX + absY == 1) {
            return true;
            // Checks whether there is equal diagonal movement by only one square
        } else if ((absX - absY == 0) && ((absX == 1) || (absY == 1))) {
            return true;
        } else {
            return false;
        }
    } // canMoveTo
} // King
