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
     */
    public King(boolean isWhiteColor, int theX, int theY) {
        super(isWhiteColor, theX, theY);
    } // Piece


    /** Determines whether the piece is able to move to the desired square according to the game
     * rules and situation.
     *
     * @param toX the destination x coordinate
     * @param toY the destination y coordinate
     * @return true if the piece can move to the destination square
     */
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
