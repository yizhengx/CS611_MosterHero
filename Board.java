import java.util.*;

public class Board {
    // (randomly assigned) 20% non-accessible cells, 30% markets and 50% common cells
    private Cell[][] board;
    private Integer size = 8;
    HashSet<Integer> used;

    Board(){
        board = new Cell[8][8];
        // random assign 12 non-accessible cells, 19 markets and 33 common cells
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                board[i][j] = new Cell(1, 0, 0);
            }
        }
        used = new HashSet<Integer>();
        // create instance of Random class
        Random rand = new Random();
        for (int i=0; i<=6; i++){
            int index = rand.nextInt(64);
            while(used.contains(index)){index = rand.nextInt(64);}
            board[index/8][index-8*(index/8)].setAccessible(0);
            used.add(index);
        }
        for (int i=0; i<=10; i++){
            int index = rand.nextInt(64);
            while(used.contains(index)){index = rand.nextInt(64);}
            board[index/8][index-8*(index/8)].getMarket();
            used.add(index);
        }
    }

    Board(Integer size_){
        size = size_;
        board = new Cell[size][size];
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                board[i][j] = new Cell(1, 0, 0);
            }
        }
        used = new HashSet<Integer>();
        // create instance of Random class
        Random rand = new Random();
        for (int i=0; i<=6; i++){
            int index = rand.nextInt(size*size);
            while(used.contains(index)){index = rand.nextInt(size*size);}
            board[index/size][index-size*(index/size)].setAccessible(0);
            used.add(index);
        }
        for (int i=0; i<=10; i++){
            int index = rand.nextInt(size*size);
            while(used.contains(index)){index = rand.nextInt(size*size);}
            board[index/size][index-size*(index/size)].getMarket();
            used.add(index);
        }
    }

    public HashSet<Integer> getUsedSet(){return used;}
    public Integer getSize(){return size;}
    public Cell getCell(Integer index){return board[index/size][index-size*(index/size)];}

    public String toString(){
        String board_out = "";
        String line_offset = "*";
        for (int i=0; i<size; i++){line_offset+="******";}
        String line_empty_offset = "*";
        for (int i=0; i<size; i++){line_empty_offset+="     *";}
        board_out += line_offset+"\n"+"******************  GAME  MAP  ******************"+"\n"+line_offset;
        for (int i=0; i<size; i++){
            String line = "*";
            for (int j=0; j<size; j++){
                line += "  "+board[i][j]+"  *";
            }
            board_out+="\n"+line+"\n"+line_offset;
        }
        return board_out;
    }

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
    }
}
