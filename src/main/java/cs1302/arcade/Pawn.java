package cs1302.arcade;

/**
 * Defines a Pawn chess piece in a chess game.
 */
public class Pawn extends Piece {

    boolean hasMoved = false;

    /**
     * Constructor for the {@code Pawn}.
     *
     * @param isWhiteColor a {@code boolean} representing whether the piece is white or not
     */
    public Pawn(boolean isWhiteColor, int theX, int theY) {
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
        if (absY == 0) {
            if (hasMoved == false) {
                if (absX == 2) {
                    hasMoved = true;
                    return true;
                } else {
                    return false;
                }
            } else if (absX == 1) {
                hasMoved = true;
                return true;
            } else {
                return false;
            }
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
        int absX = Math.abs(this.getX() - toX);
        int absY = Math.abs(this.getY() - toY);
        System.out.println(this.getX() + " " + this.getY());
        System.out.println(absX + " " + absY);
        if ((absX == 1) && (absY == 1)) {

            return true;
        } else {
            return false;
        }
    } // canCapture

} // Pawn
