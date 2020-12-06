package cs1302.arcade;

/**
 * Defines the attributes of a chess piece in a chess game. The {@code Piece} class defines
 * different methods and values a {@code Piece} can have.
 */
public abstract class Piece {
    private int x;
    private int y;

    /** Determines whether the piece is able to move to the desired square according to the game
     * rules and situation.
     *
     * @param toX the destination x coordinate
     * @param toY the destination y coordinate
     * @return true if the piece can move to the destination square
     */
    public abstract boolean canMoveTo(int toX, int toY);

    /** Determines whether the piece is able to capture another piece on the desired square
     * according to the game rules and situation.
     *
     * @param toX the x coordinate of the piece to capture
     * @param toY the y coordinate of the piece to capture
     * @return true if the piece can capture the piece on the destination square
     */
    public abstract boolean canCapture(int toX, int toY);

    public int getX() {
        return this.x;
    } // getX

    public int getY() {
        return this.y;
    } // getY

} // Piece
