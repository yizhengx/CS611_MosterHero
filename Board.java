import java.util.ArrayList;

public class Board {
    private Cell[][] board;

    Board(){
        board = new Cell[8][8];
    }

    Board(Integer size){
        board = new Cell[size][size];
    }
}
