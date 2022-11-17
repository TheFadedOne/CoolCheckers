package com.zybooks.coolcheckers;

import android.util.Log;

public class CheckersGame {

    public GamePiece[] mGamePieces;
    public BoardSpace[] mBoardSpaces;
    public static final int MOVEABLE_SPACES = 32;
    public static final int ROWS = 8;
    public static final int PIECE_AMOUNT = 24;


    //constructor
    public CheckersGame()
    {
        mGamePieces = new GamePiece[24];
        mBoardSpaces = new BoardSpace[64];
    }


    public void newGame()
    {
        setupSpaces();
        setupPieces();
        printPieces();
    }

    /*
     * sets up the space types of each board position
     */
    public void setupSpaces()
    {

        //black spaces
        int spaceCounter = 0;
        int x = 1;
        int y = 1;
        //sets up black spaces at the top
        for (int i = 1; i <= 8; ++i)
        {
            if (i % 2 == 1) {x = 1;} else {x = 2;}
            for (int j = 1; j <= 4; ++j)
            {
                mBoardSpaces[spaceCounter] = new BoardSpace(spaceType.BLACK, x, y);

                mBoardSpaces[spaceCounter].printSpace();
                x+=2;
                spaceCounter++;
            }
            y+=1;
        }

        x = 1;
        y = 1;
        //sets up white spaces
        for (int i = 1; i <= 8; ++i)
        {
            if (i % 2 == 0) {x = 1;} else {x = 2;}
            for (int j = 1; j <= 4; ++j)
            {
                mBoardSpaces[spaceCounter] = new BoardSpace(spaceType.WHITE, x, y);

                mBoardSpaces[spaceCounter].printSpace();
                x+=2;
                spaceCounter++;
            }
            y+=1;
        }

    	/*
    	int spaceCounter = 0;
    	int x;
    	int y = 1;
    	for (int i = 1; i <= 8; ++i)
    	{
    		if (i % 2 == 1) {x = 1;} else {x = 2;}
    		for (int j = 1; j <= 8; ++j)
    		{
    			if (spaceCounter % 2 == 0)
    			{
    				mBoardSpaces[spaceCounter] = new BoardSpace(spaceType.BLACK, x, y);
    			}
    			else
    			{
    				mBoardSpaces[spaceCounter] = new BoardSpace(spaceType.WHITE, x, y);
    			}
    			mBoardSpaces[spaceCounter].printSpace();
    			++x;
    			++spaceCounter;
    		}
    		++y;
    	}
    	*/
    }

    /*
     * sets up the default positions of the player pieces
     */
    public void setupPieces()
    {
        int pieceCounter = 0;
        int rx;
        int ry = 1;
        //sets up red pieces at the top
        for (int i = 1; i <= 3; ++i)
        {
            if (i == 2) {rx = 2;} else {rx = 1;}
            for (int j = 1; j <= 4; ++j)
            {
                mGamePieces[pieceCounter] = new GamePiece(GamePiece.pieceColor.RED, rx, ry);
                rx+=2;
                pieceCounter++;
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
                mGamePieces[pieceCounter] = new GamePiece(GamePiece.pieceColor.BLACK, bx, by);
                bx-=2;
                pieceCounter++;
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

    /*
     *
     */
    public GamePiece[] move(playerTurn turn, GamePiece[] pieces, GamePiece pieceToMove, BoardSpace spaceToMoveTo)
    {
        checkValidMove();

        return mGamePieces;
    }

    /*
     *
     */
    public GamePiece[] getPieces()
    {
        return mGamePieces;
    }

    public BoardSpace[] getBoardSpaces()
    {
        return mBoardSpaces;
    }


    public void printPieces()
    {
        for (int i = 0; i < 24; ++i)
        {
            mGamePieces[i].printPiece();

        }

    }
}

