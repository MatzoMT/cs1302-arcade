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
     * @param theX the x coordinate to place the calling piece
     * @param theY the y coordinate to place the calling piece
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
            // Checks whether the pawn has already moved
            if (hasMoved == false) {
                // Returns true if pawn has not yet moved and wants to move two squares or one
                // square
                if (absX == 2) {
                    hasMoved = true;
                    return true;
                } else if (absX == 1) {
                    hasMoved = true;
                    return true;
                } else {
                    return false;
                }
                // Returns true if the pawn wants to move one square
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
        // Checks whether equal diagonal movement is detected by one square
        if ((absX == 1) && (absY == 1)) {
            // Capturing mechanics for white pawns
            if (this.getWhite() == true) {
                // Determines whether the white pawn is moving up the board and left or right
                if ((this.getX() - toX > 0) && (this.getY() - toY < 0)) {
                    return true;
                } else if ((this.getX() - toX > 0) && (this.getY() - toY > 0)) {
                    return true;
                } else {
                    return false;
                }
                // Capturing mechanics for black pawns
            } else {
                // Determines whether the black pawn is moving down the board and left or right
                if ((this.getX() - toX < 0) && (this.getY() - toY < 0)) {
                    return true;
                } else if ((this.getX() - toX < 0) && (this.getY() - toY > 0)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    } // canCapture


} // Pawn
