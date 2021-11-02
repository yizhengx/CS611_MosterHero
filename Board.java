import java.io.IOException;
import java.util.*;

public class Board {
    // (randomly assigned) 20% non-accessible cells, 30% markets and 50% common cells
    private Cell[][] board;
    private Integer size = 8;
    HashSet<Integer> used;

    Board() throws IOException{initialization(8);}
    Board(Integer size_) throws IOException{initialization(size_);}

    public void initialization(Integer size_) throws IOException{
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
            board[index/size][index-size*(index/size)].setMarket();
            used.add(index);
        }
    }
    public HashSet<Integer> getUsedSet(){return used;}
    public Integer getSize(){return size;}
    // public Cell getCell(Integer index){return board[index/size][index-size*(index/size)];}
    public Cell getCell(Integer x, Integer y){return board[x][y];}


    // set a common cell to a cell with player
    public void getPlayer(Integer x, Integer y, String icon){board[x][y].getPlayer(icon);}

    // set a cell with player to a cell without player
    public void removePlayer(Integer x, Integer y){board[x][y].removeUser();}

    // check if a cell is accessible -> T for accessible & F for non-accessible
    public boolean isAccessible(Integer x, Integer y){return board[x][y].isAccessible();}

    // return [x,y] for a random common cell
    public ArrayList<Integer> getRandomCell(){
        Random rand = new Random();
        ArrayList<Integer> xy = new ArrayList<Integer>();
        int index = rand.nextInt(size*size);
        while(used.contains(index)){index = rand.nextInt(size*size);}
        xy.add(index/size);
        xy.add(index-size*(index/size));
        return xy;
    }

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

    public static void main(String[] args) throws IOException {
        Board board = new Board();
        System.out.println(board);
    }
}
