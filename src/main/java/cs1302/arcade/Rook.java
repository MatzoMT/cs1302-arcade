package cs1302.arcade;

/**
 * Defines a Rook chess piece in a chess game.
 */
public class Rook extends Piece {

    boolean hasMoved = false;
    boolean canCastle = true;

    /**
     * Constructor for the {@code Rook}.
     *
     * @param isWhiteColor a {@code boolean} representing whether the piece is white or not
     * @param theX the x coordinate to place the calling piece
     * @param theY the y coordinate to place the calling piece
     */
    public Rook(boolean isWhiteColor, int theX, int theY) {
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
        // Determines whether movement has occurred on either the x or y axis only, and not both
        if ((absX >= 1) && (absY == 0)) {
            return true;
        } else if ((absY >= 1) && (absX == 0)) {
            return true;
        } else {
            return false;
        }
    } // canMoveTo


} // Rook
