package com.zybooks.coolcheckers;

public class GamePieces {

    public boolean dark;
    public boolean crowned;
    public boolean captured;
    public CheckersGameModel position = new CheckersGameModel();

    public static CheckersGameModel[] push_back(CheckersGameModel[] array, CheckersGameModel push) {
        int count = 0;
        for(CheckersGameModel x : array) {
            count++;
        }
        CheckersGameModel[] longer = new CheckersGameModel[count + 1];
            for(int i = 0; i < count; i++){
                longer[i] = array[i];
                longer[count] = push;
                return longer;
            }
    }

    public String toString() {
        String str = new String();
        if (this.crowned == false) {
            if (this.dark == false) {
                str = "r";
            } else {
                str = "b";
            }
        } else {
            if (this.dark == false) {
                str = "R";
            } else {
                str = "B";
            }
        }
        return str;
    }

    public GamePieces() {
        crowned = false;
        captured = false;
        dark = false;
        position.row = 0;
        position.col = 0;
    }

    public void piece_print() {
        if (this.crowned == false) {
            if (this.dark == false) {
                System.out.print("r");
            } else {
                System.out.print("b");
            }
        } else {
            if (this.dark == false) {
                System.out.print("R");
            } else {
                System.out.print("B");
            }
        }
        return;
    }

    public CheckersGameModel[]
    piece_positions_in_direction(int dir) {
        GamePieces gp1 = new GamePieces();
        GamePieces gp2 = new GamePieces();

        GamePieces[] v_out = new GamePieces[0];
        GamePieces[] v_out_pos = new GamePieces[0];
        boolean dark = this.dark;
        boolean crowned = this.crowned;
        boolean pass = false;

        if (crowned == true) {
            pass = true;
        } else {
            if (dir == 0 && dark == true) {
                pass = true;
            } else if (dir == 1 && dark == true) {
                pass = true;
            } else if (dir == 2 && dark == true) {
                pass = true;
            } else if (dir == 3 && dark == true) {
                pass = true;
            }
        }

        if (pass == true) {
            if (dir == 0) {
                //piece moves north east
                gp1.position.row = this.position.row - 1;
                gp1.position.col = this.position.col + 1;
                gp2.position.row = this.position.row - 1 - 1;
                gp2.position.col = this.position.col + 1 + 1;
            } else if (dir == 1) {
                //piece moves north west
                gp1.position.row = this.position.row - 1;
                gp1.position.col = this.position.col - 1;
                gp2.position.row = this.position.row - 1 - 1;
                gp2.position.row = this.position.col - 1 - 1;
            } else if (dir == 2) {
                //piece moves south west
                gp1.position.row = this.position.row + 1;
                gp1.position.col = this.position.col - 1;
                gp2.position.row = this.position.row + 1 + 1;
                gp2.position.col = this.position.col - 1 - 1;
            } else if (dir == 3) {
                //piece moves south east
                gp1.position.row = this.position.row + 1;
                gp1.position.col = this.position.col + 1;
                gp2.position.row = this.position.row + 1 + 1;
                gp2.position.col = this.position.col + 1 + 1;
            }
        } else {
            v_out = v_out;
        }

        if (gp1.position.row <= 7 && gp1.position.col <= 7) {
            if (gp1.position.row >= 0 && gp1.position.col >= 0) {
                v_out_pos = push_back(v_out_pos, gp1.position);
            }
        }

        if (gp2.position.row <= 7 && gp2.position.col <= 7) {
            if (gp2.position.row >= 0 && gp2.position.col >= 0) {
                v_out_pos = push_back(v_out_pos, gp2.position);
            }
        }
        return v_out_pos;
    }

}
