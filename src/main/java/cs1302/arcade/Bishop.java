package cs1302.arcade;

/**
 * Defines a Bishop chess piece in a chess game.
 */
public class Bishop extends Piece {

    /**
     * Constructor for the {@code Bishop}.
     *
     * @param isWhiteColor a {@code boolean} representing whether the piece is white or not
     * @param theX the x coordinate to place the calling piece
     * @param theY the y coordinate to place the calling piece
     */
    public Bishop(boolean isWhiteColor, int theX, int theY) {
        super(isWhiteColor, theX, theY);
    } // Piece

    /** {@inheritDoc} */
    public boolean canMoveTo(int toX, int toY) {
        int absX = Math.abs(this.getX() - toX);
        int absY = Math.abs(this.getY() - toY);
        // Checks whether the difference between x and y movement is 0, implying pure diagonal
        // movement.
        if (absX - absY == 0) {
            return true;
        } else {
            return false;
        }
    } // canMoveTo



} // Bishop
