package cs1302.arcade;

public class King extends Piece {

    boolean hasMoved = false;
    boolean canCastle = true;

    /** Determines whether the piece is able to move to the desired square according to the game
     * rules and situation.
     */
    public boolean canMoveTo(int toX, int toY) {
        return true;
    } // canMoveTo

    /** Determines whether the piece is able to capture another piece on the desired square
     * according to the game rules and situation.
     */
    public boolean canCapture(int toX, int toY) {
        return true;
    } // canCapture

} // King
