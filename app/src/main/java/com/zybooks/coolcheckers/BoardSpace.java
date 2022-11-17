package com.zybooks.coolcheckers;

public class BoardSpace {

    public int xPosition;
    public int yPosition;
    public spaceType mSpaceType;

    public BoardSpace(spaceType type, int x, int y)
    {
        mSpaceType = type;
        xPosition = x;
        yPosition = y;
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

    public void printSpace()
    {
        System.out.println("space type: " + mSpaceType
                + ",  x: " + xPosition + ",  y: " + yPosition);
    }
}
