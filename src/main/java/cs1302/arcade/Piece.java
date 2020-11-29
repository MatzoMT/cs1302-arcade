/**
 * Defines the attributes of a chess piece in a chess game. The {@code Piece} class defines
 * different methods and values a {@code Piece} can have.
 */
public abstract class Piece {
    private int x;
    private int y;

    public abstract boolean canMoveTo(int toX, int toY);

    public abstract boolean canCapture(int toX, int toY);

    /*
      public int getX() {

}

public int getY() {


}

} // Piece
