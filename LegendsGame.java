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
            Player current_player = player_list.get(0);
            HashSet<String> options = new HashSet<String>();
            String message = "";
            if (current_player.getX()>0 && board.isAccessible(current_player.getX()-1, current_player.getY())==true){
                options.add("w");
                message += "\nw: move up";
            }
            if (current_player.getY()<board.getSize()-1){
                options.add("d");
                message += "\nd: move right";
            }
            if (current_player.getY()>0){
                options.add("a");
                message += "\na: move left";
            }
            if (current_player.getX()<board.getSize()-1){
                options.add("s");
                message += "\ns: move down";
            }
            options.add("q");
            message += "\nq: quit";
            options.add("i");
            message += "\ni: check info";
            String op = IO.getInstance().validSingleString(message+"\nplease enter your move:", options);
            board.removePlayer(current_player.getX(), current_player.getY());
            if (op.equals("w")){current_player.setX(current_player.getX()-1);}
            if (op.equals("s")){current_player.setX(current_player.getX()+1);}
            if (op.equals("a")){current_player.setY(current_player.getY()-1);}
            if (op.equals("d")){current_player.setY(current_player.getY()+1);}
            board.getPlayer(current_player.getX(), current_player.getY(), current_player.getIcon());
            System.out.println(board);
            if (board.getCell(current_player.getX(), current_player.getY()).hasMarket()){System.out.println(board.getCell(current_player.getX(), current_player.getY()).getMarket());}
        }
    }

    // 
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
            new_player.setY(xy.get(1));
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