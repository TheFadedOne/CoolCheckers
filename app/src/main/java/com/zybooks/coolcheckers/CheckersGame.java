package com.zybooks.coolcheckers;


public class CheckersGame {

    public static GamePiece[] mGamePieces;
    public BoardSpace[] mBoardSpaces;
    public static final int MOVEABLE_SPACES = 32;
    public static final int ROWS = 8;
    public static final int PIECE_AMOUNT = 24;


    //Constructor
    public CheckersGame()
    {
        mGamePieces = new GamePiece[24];
        mBoardSpaces = new BoardSpace[64];
    }


    public void newGame()
    {
        setupSpaces();
        setupPieces();
    }

    /*
     * Sets up the space types of each board position
     */
    public void setupSpaces()
    {

        int spaceCounter = 0;
        int x = 1;
        int y = 1;
        //Sets up black spaces
        for (int i = 1; i <= 8; ++i)
        {
            if (i % 2 == 1) {x = 1;} else {x = 2;}
            for (int j = 1; j <= 4; ++j)
            {
                mBoardSpaces[spaceCounter] = new BoardSpace(spaceType.BLACK, x, y);

                x+=2;
                spaceCounter++;
            }
            y+=1;
        }

        x = 1;
        y = 1;
        //Sets up white spaces
        for (int i = 1; i <= 8; ++i)
        {
            if (i % 2 == 0) {x = 1;} else {x = 2;}
            for (int j = 1; j <= 4; ++j)
            {
                mBoardSpaces[spaceCounter] = new BoardSpace(spaceType.WHITE, x, y);

                x+=2;
                spaceCounter++;
            }
            y+=1;
        }

    }

    /*
     * Sets up the default positions of the player pieces
     */
    public void setupPieces()
    {
        int pieceCounter = 0;
        int rx;
        int ry = 1;
        //Sets up red pieces at the top
        for (int i = 1; i <= 3; ++i)
        {
            if (i == 2) {rx = 2;} else {rx = 1;}
            for (int j = 1; j <= 4; ++j)
            {
                mGamePieces[pieceCounter] = new GamePiece(pieceColor.RED, rx, ry);
                rx+=2;
                pieceCounter++;
            }
            ry+=1;
        }

        int bx;
        int by = 8;
        //Sets up black pieces at the bottom
        for (int i = 1; i <= 3; ++i)
        {
            if (i == 2) {bx = 7;} else {bx = 8;}
            for (int j = 1; j <= 4; ++j)
            {
                mGamePieces[pieceCounter] = new GamePiece(pieceColor.BLACK, bx, by);
                bx-=2;
                pieceCounter++;
            }

            by-=1;
        }
    }



    /*
    Takes input of the player's selected piece position to move and desired position to move it to.
    Returns true if a legal move is possible with the given inputs
     */
    public boolean isValidMove(pieceColor playerMovingColor, GamePiece pieceToMove, BoardSpace spaceToMoveTo)
    {
        //Returns false if the piece position selected is null
        if (pieceToMove == null)
        {
            System.out.println("--- Invalid move --- position selected does not contain an existing piece!");
            return false;
        }



        //Returns false if spaceToMoveTo already contains a piece
        if (spaceToMoveTo.getX() == pieceToMove.getX() && spaceToMoveTo.getY() == pieceToMove.getY())
        {
            return false;
        }
        for (int i = 0; i < 24; ++i)
        {
            if (spaceToMoveTo.getX() == mGamePieces[i].getX() && spaceToMoveTo.getY() == mGamePieces[i].getY()
                    && getPieceWithPosition(spaceToMoveTo.getX(), spaceToMoveTo.getY()).getColor() != pieceColor.NULL)
            {
                System.out.println("--- Invalid move --- desired move space already contains an existing piece!");
                return false;
            }
        }



        //Establishes direction variable as a 1 for downward movement and -1 for upward movement.
        //This allows us to prevent an uncrowned piece from moving in the wrong direction.
        int direction = 0;
        direction = (playerMovingColor == pieceColor.RED) ? 1 : -1;



        //designates a variable to represent the opposite color of piece being moved
        pieceColor oppositeColor = (playerMovingColor == pieceColor.RED) ? pieceColor.BLACK : pieceColor.RED;



        //Checks for valid movement of uncrowned pieces
        if (pieceToMove.crowned == false && pieceToMove.getColor() == playerMovingColor)
        {
            if (spaceToMoveTo.getSpaceType() == spaceType.BLACK
                    && (Math.abs(spaceToMoveTo.getX() - pieceToMove.getX()) == 1)
                    && (spaceToMoveTo.getY() - pieceToMove.getY() == direction))
            {
                return true;
            }
            //If not moving to an adjacent space, check for jump move
            else if (spaceToMoveTo.getSpaceType() == spaceType.BLACK
                    && (Math.abs(spaceToMoveTo.getX() - pieceToMove.getX()) == 2)
                    && (spaceToMoveTo.getY() - pieceToMove.getY() == direction * 2)
                    && (getPieceWithPosition((pieceToMove.getX() + spaceToMoveTo.getX()) / 2,
                    (pieceToMove.getY() + spaceToMoveTo.getY()) / 2).getColor() == oppositeColor))
            {
                return true;
            }


        }
        //Checks for valid movement of crowned pieces
        else if (pieceToMove.crowned == true && pieceToMove.getColor() == playerMovingColor)
        {
            if (spaceToMoveTo.getSpaceType() == spaceType.BLACK
                    && (Math.abs(spaceToMoveTo.getX() - pieceToMove.getX()) == 1)
                    && (Math.abs(spaceToMoveTo.getY() - pieceToMove.getY()) == 1))
            {
                return true;
            }
            //If not moving to an adjacent space, check for jump move
            else if (spaceToMoveTo.getSpaceType() == spaceType.BLACK
                    && (Math.abs(spaceToMoveTo.getX() - pieceToMove.getX()) == 2)
                    && (Math.abs(spaceToMoveTo.getY() - pieceToMove.getY()) == 2)
                    && (getPieceWithPosition((pieceToMove.getX() + spaceToMoveTo.getX()) / 2,
                    (pieceToMove.getY() + spaceToMoveTo.getY()) / 2).getColor() == oppositeColor))
            {
                return true;
            }
        }

        return false;
    }

    /*
     *
     */
    public GamePiece[] move(playerTurn turn, GamePiece[] pieces, GamePiece pieceToMove, BoardSpace spaceToMoveTo)
    {
        mGamePieces = pieces;
        pieceColor playerMovingColor;
        if (turn == playerTurn.RED) {playerMovingColor = pieceColor.RED;}
        else {playerMovingColor = pieceColor.BLACK;}


        //Sets the x and y of the Game Piece to match the x and y of the desired space
        //If it is a valid movement

        if (isValidMove(playerMovingColor, pieceToMove, spaceToMoveTo) == true)
        {
            //checks for jump move
            if (isJumpMove(playerMovingColor, pieceToMove, spaceToMoveTo))
            {
                getPieceWithPosition((spaceToMoveTo.getX() + pieceToMove.getX()) / 2,
                        (spaceToMoveTo.getY() + pieceToMove.getY()) / 2).kill();
            }

            //sets new piece position
            pieceToMove.setX(spaceToMoveTo.getX());
            pieceToMove.setY(spaceToMoveTo.getY());

            //crowns piece if reached opposite side
            if (playerMovingColor == pieceColor.RED && spaceToMoveTo.getY() == 8)
            {
                pieceToMove.crown();
            }
            if (playerMovingColor == pieceColor.BLACK && spaceToMoveTo.getY() == 1)
            {
                pieceToMove.crown();
            }

        }



        //Check for double jump




        return mGamePieces;
    }



    /*
     *
     */
    public boolean isJumpMove(pieceColor playerMovingColor, GamePiece pieceToMove, BoardSpace spaceToMoveTo)
    {
        //designates a variable to represent the opposite color of piece being moved
        pieceColor oppositeColor = (playerMovingColor == pieceColor.RED) ? pieceColor.BLACK : pieceColor.RED;

        if (spaceToMoveTo.getSpaceType() == spaceType.BLACK
                && (Math.abs(spaceToMoveTo.getX() - pieceToMove.getX()) == 2)
                && (Math.abs(spaceToMoveTo.getY() - pieceToMove.getY()) == 2)
                && (getPieceWithPosition((pieceToMove.getX() + spaceToMoveTo.getX()) / 2,
                (pieceToMove.getY() + spaceToMoveTo.getY()) / 2).getColor() == oppositeColor))
        {

            return true;
        }

        return false;
    }



    public static GamePiece getPieceWithPosition(int x, int y)
    {
        GamePiece selectedPiece = new GamePiece(pieceColor.NULL, 0, 0);

        for (int i = 0; i < 24; ++i)
        {
            if (mGamePieces[i].getX() == x && mGamePieces[i].getY() == y)
            {
                selectedPiece = mGamePieces[i];
            }
        }

        return selectedPiece;
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

