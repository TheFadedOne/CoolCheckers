package com.zybooks.coolcheckers;

public class Position {

    public int row;
    public int col;

    public Position(){
        row = 0;
        int = 0;
    }

    public Position(int r, int c) {
        row = r;
        col = c;
    }

    public void positon_parse(String st) {
        row = 8 - (st.charAt(1) - '0');
        col= st.charAt(0) - 'A';
        return;
    }
}
