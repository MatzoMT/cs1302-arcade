package cs1302.arcade;

/**
 * Defines a Bishop chess piece in a chess game.
 */
public class Bishop extends Piece {

    /**
     * Constructor for the {@code Bishop}.
     *
     * @param isWhiteColor a {@code boolean} representing whether the piece is white or not
     */
    public Bishop(boolean isWhiteColor) {
        super(isWhiteColor);
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
        if (absX - absY == 0) {
            return true;
        } else {
            return false;
        }
    } // canMoveTo

    /** Determines whether the piece is able to capture another piece on the desired square
     * according to the game rules and situation.
     *
     * @param toX the x coordinate of the piece to capture
     * @param toY the y coordinate of the piece to capture
     * @return true if the piece can capture the piece on the destination square
     */
    public boolean canCapture(int toX, int toY) {
        return true;
    } // canCapture

} // Bishop
