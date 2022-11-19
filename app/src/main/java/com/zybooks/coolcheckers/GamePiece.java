package com.zybooks.coolcheckers;

public class GamePiece {

    public pieceColor mPieceColor;
    public boolean crowned;
    public boolean captured;
    public int xPosition;
    public int yPosition;

    public GamePiece(pieceColor type, int x, int y) {
        crowned = false;
        captured = false;
        mPieceColor = type;
        xPosition = x;
        yPosition = y;
    }

    public pieceColor getColor()
    {
        return mPieceColor;
    }

    public int getX()
    {
        return xPosition;
    }

    public int getY()
    {
        return yPosition;
    }

    public void setX(int x)
    {
        xPosition = x;
    }

    public void setY(int y)
    {
        yPosition = y;
    }

    public void printPiece()
    {

        if (this.crowned == false)
        {

            if (mPieceColor == pieceColor.RED)
            {
                System.out.print("red");
            }
            else
            {
                System.out.print("black");
            }
        }

        if (this.crowned == true)
        {
            if (mPieceColor == pieceColor.RED)
            {
                System.out.print("RED");
            }
            else
            {
                System.out.print("BLACK");
            }
        }

        System.out.println(" (position x: " + xPosition + ",  y: " + yPosition + ")");
    }
}
