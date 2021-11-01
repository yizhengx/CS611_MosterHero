import java.io.IOException;
import java.util.*;

public class LegendsGame {
    // simplify game logic:
    // get player -> move
    //            -> market -> take action
    //            -> monster -> fight
    //            -> end turn
    private ArrayList<Player> player_list =  new ArrayList<Player>();
    private Board board;

    public void run() throws IOException{
        intro();
        initialization();
        while (true){
            HashSet<String> options = new HashSet<String>();
            options.add("w");
            options.add("a");
            options.add("s");
            options.add("d");
            String op = IO.getInstance().validSingleString("please enter your move:", options);
            System.out.println(op);
        }
        
    }

    public void intro() throws IOException{
        System.out.println("Welcome to Legends!");
    }

    public void initialization() throws IOException{
        board = new Board();
        Integer num_players = IO.getInstance().validInteger("Please enter the number of players:", 1, 5);
        for (int i=0; i<num_players;i++){
            String name_ = IO.getInstance().validString("Please enter the name for player "+(i+1));
            Integer num_ = IO.getInstance().validInteger("Please enter the number of heros for player "+(i+1), 1, 3);
            Player new_player = new Player(name_, num_, ""+(i+1));
            ArrayList<Integer> xy = board.getRandomCell();
            new_player.setX(xy.get(0));
            new_player.setX(xy.get(1));
            board.getPlayer(new_player.getX(), new_player.getY(), new_player.getIcon());
            player_list.add(new_player);
        }
        System.out.println(board);
    }

    public static void main(String[] args) throws IOException {
        LegendsGame game = new LegendsGame();
        game.run();
    }
}
