package com.zybooks.coolcheckers;

public class CheckersGameModel {

    public GamePiece[] black_pieces;
    public GamePiece[] red_pieces;

    public enum spaceType {WHITE_SPACE, BLACK_SPACE};
    public spaceType[][] mGameBoard;
    public GamePiece[][] mGamePieces;
    public static final int BOARD_SIZE = 8;


    //constructor
    public CheckersGameModel()
    {
        mGameBoard = new spaceType[BOARD_SIZE][BOARD_SIZE];
        
    }


    public void newGame()
    {
        setUpGameBoard();
        setUpCheckerPositions();
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
}
