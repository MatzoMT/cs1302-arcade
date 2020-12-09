package cs1302.arcade;

/**
 * Defines a Knight chess piece in a chess game.
 */
public class Knight extends Piece {

    /**
     * Constructor for the {@code Knight}.
     *
     * @param isWhiteColor a {@code boolean} representing whether the piece is white or not
     */
    public Knight(boolean isWhiteColor, int theX, int theY) {
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
        if (absX * absY == 2) {
            return true;
        } else {
            return false;
        }
    } // canMoveTo


} // Knight
