import java.io.IOException;
import java.util.*;

// Board Class: game board (or map) for this game
public class Board {
    private Cell[][] board;
    private Integer size = 8;
    HashSet<Integer> used;

    Board()throws IOException{initialization(8);}
    Board(Integer size_) throws IOException{initialization(size_);}

    // Accessor
    public Integer getSize(){return size;}
    public Cell getCell(Integer x, Integer y){return board[x][y];}

    // Setter
    // Set a common cell to a cell with player
    public void getPlayer(Integer x, Integer y, String icon){board[x][y].getPlayer(icon);}
    // Set a cell with player to a cell without player
    public void removePlayer(Integer x, Integer y){board[x][y].removeUser();}

    // Check if a cell is accessible -> T for accessible & F for non-accessible
    public boolean isAccessible(Integer x, Integer y){return board[x][y].isAccessible();}
    // Check if a cell has player
    public boolean hasPlayer(Integer x, Integer y){return board[x][y].hasPlayer();}

    // Initialize the board with a certain size
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
        for (int i=0; i<=size*size*0.1; i++){
            int index = rand.nextInt(size*size);
            while(used.contains(index)){index = rand.nextInt(size*size);}
            board[index/size][index-size*(index/size)].setAccessible(0);
            used.add(index);
        }
        for (int i=0; i<=size*size*0.2; i++){
            int index = rand.nextInt(size*size);
            while(used.contains(index)){index = rand.nextInt(size*size);}
            board[index/size][index-size*(index/size)].setMarket();
            used.add(index);
        }
    }

    // return [x,y] for a random common cell, used to give a random starting point for each player
    public ArrayList<Integer> getRandomCell(){
        Random rand = new Random();
        ArrayList<Integer> xy = new ArrayList<Integer>();
        int index = rand.nextInt(size*size);
        while(used.contains(index)){index = rand.nextInt(size*size);}
        xy.add(index/size);
        xy.add(index-size*(index/size));
        return xy;
    }

    public static void main(String[] args) throws IOException {
        Board board = new Board();
        System.out.println(board);
    }
}
