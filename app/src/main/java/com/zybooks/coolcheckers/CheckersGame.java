package com.zybooks.coolcheckers;


import android.util.Log;

public class CheckersGame {
    
    public GamePiece[] mGamePieces;
    public static final int MOVEABLE_SPACES = 32;
    public static final int ROWS = 8;
    public static final int PIECE_AMOUNT = 24;


    //constructor
    public CheckersGame()
    {
        mGamePieces = new GamePiece[24];
    }


    public void newGame()
    {
       setUpPieces();
       //printPieces();
    }


    //sets up the default positions of the player pieces
    public void setUpPieces()
    {
        int rx;
        int ry = 1;
        //sets up red pieces at the top
        for (int i = 1; i <= 3; ++i)
        {
            if (i == 2) {rx = 2;} else {rx = 1;}
            for (int j = 1; j <= 4; ++j)
            {
                mGamePieces[i] = new GamePiece(GamePiece.pieceColor.RED, rx, ry);
                rx+=2;
            }
            ry+=1;
        }

        int bx;
        int by = 8;
        //sets up black pieces at the bottom
        for (int i = 1; i <= 3; ++i)
        {
            if (i == 2) {bx = 7;} else {bx = 8;}
            for (int j = 1; j <= 4; ++j)
            {
                mGamePieces[i] = new GamePiece(GamePiece.pieceColor.BLACK, bx, by);
                by-=2;
            }

            by-=1;
        }
    }

    /*
    takes input of the player's selected piece position to move and desired position to move it to.
    It will check adjacent positions and see if there is an empty space or other piece.
     */
    public boolean checkValidMove()
    {
        return false;
    }

    public void printPieces()
    {
        for (int i = 0; i < 24; ++i)
        {
            mGamePieces[i].printPiece();
            Log.println(Log.DEBUG,"fun", "Your message to print");
        }

    }
}

