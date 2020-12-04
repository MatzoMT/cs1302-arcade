package cs1302.arcade;

import cs1302.arcade.Piece;

public class Square {
    private Piece piece;
    private int x;
    private int y;

    public Square(Piece thePiece, int theX, int theY) {
        this.piece = thePiece;
        this.x = theX;
        this.y = theY;
    } // Square

    public Piece getPiece() {
        return this.piece;
    } // getPiece

    public int getX() {
        return this.x;
    } // getX

    public int getY() {
        return this.y;
    } // getY

    public void setPiece(Piece newPiece) {
        this.piece = newPiece;
    } // setPiece

    public void setX(int newX) {
        this.x = newX;
    } // setX

    public void setY(int newY) {
        this.y = newY;
    } // setY

} // Square
