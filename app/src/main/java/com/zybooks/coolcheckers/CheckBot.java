package com.zybooks.coolcheckers;

import java.util.Random;

public class CheckBot {


    public CheckersGame testGame;
    public static GamePiece[] mPieces;
    public static BoardSpace[] mSpaces;


    public CheckBot(GamePiece[] pieces, BoardSpace[] spaces)
    {
        mPieces = new GamePiece[24];
        mPieces = pieces;
        mSpaces = new BoardSpace[64];
        mSpaces = spaces;
        testGame = new CheckersGame();
    }


    public int[] generateMove(GamePiece[] pieces, BoardSpace[] spaces, playerTurn turn) {


        int pieceX = -1;
        int pieceY = -1;
        int spaceX = -1;
        int spaceY = -1;

        Random rand = new Random();
        testGame.mGamePieces = mPieces;

        while (true)
        {

            pieceX = rand.nextInt(8) + 1;
            pieceY = rand.nextInt(8) + 1;
            spaceX = rand.nextInt(8) + 1;
            spaceY = rand.nextInt(8) + 1;

            pieceColor movingColor;
            movingColor = (turn == playerTurn.RED) ? pieceColor.RED : pieceColor.BLACK;

            if (testGame.isValidMove(movingColor, getPieceWithPosition(pieceX, pieceY), getBoardSpaceWithPosition(spaceX, spaceY)) == true)
            {
                break;
            }

        }

        int[] botResults = new int[4];
        botResults[0] = pieceX; botResults[1] = pieceY; botResults[2] = spaceX; botResults[3] = spaceY;

        return botResults;
    }



    /*
     * returns a GamePiece within the mPieces array that corresponds with the
     * coordinates of the button pressed.
     * If the coordinates of the button pressed do not correspond with an actual piece within the array
     * then will return a null GamePiece which will tell the CheckersGame model class that
     * no real piece has been selected.
     */

    public static GamePiece getPieceWithPosition(int x, int y)
    {
        GamePiece selectedPiece = null;

        for (int i = 0; i < 24; ++i)
        {
            if (mPieces[i].getX() == x && mPieces[i].getY() == y)
            {
                selectedPiece = mPieces[i];
            }
        }

        return selectedPiece;
    }


    /*
     * has similar function to the 'getPieceWithPosition' method, except for Board Spaces
     */

    public static BoardSpace getBoardSpaceWithPosition(int x, int y)
    {
        BoardSpace selectedSpace = null;

        for (int i = 0; i < 64; ++i)
        {
            if (mSpaces[i].getX() == x && mSpaces[i].getY() == y)
            {
                selectedSpace = mSpaces[i];
            }
        }

        return selectedSpace;
    }


}
