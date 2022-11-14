package com.zybooks.coolcheckers;


public class CheckersGameModel {

<<<<<<< Updated upstream

    public enum spaceType {WHITE_SPACE, BLACK_SPACE};
    public spaceType[][] mGameBoard;
    public checkers[][] mGamePieces;
    public static final int BOARD_SIZE = 8;
=======
    public GamePiece[] black_pieces;
    public GamePiece[] red_pieces;
    
    public GamePiece[] mGamePieces;
    public static final int MOVEABLE_SPACES = 32;
>>>>>>> Stashed changes


    //constructor
    public CheckersGameModel()
    {
<<<<<<< Updated upstream
        mGameBoard = new spaceType[BOARD_SIZE][BOARD_SIZE];
        checkers = new ;
=======
        mGameBoard = new spaceType[MOVEABLE_SPACES];
>>>>>>> Stashed changes
    }


    public void newGame()
    {
<<<<<<< Updated upstream
        setUpGameBoard();
        setUpCheckerPositions();
=======
       setUpGameBoard();
       setUpPiecePositions();
>>>>>>> Stashed changes
    }


    //sets up all of the white and black spaces on the checker board
    public void setUpGameBoard()
    {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; ++i)
        {
            for (int j = 0; j < BOARD_SIZE; ++j)
            {
                mGameBoard[i][j] = (count % 2 == 1) ? spaceType.WHITE_SPACE : spaceType.BLACK_SPACE;
                count++;
            }
        }
    }


    //sets up the default positions of the player pieces
    public void setUpCheckerPositions()
    {
        int count = 0;

        for (int i = 0; i < BOARD_SIZE; ++i)
        {

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

}

