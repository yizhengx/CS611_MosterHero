import java.util.*;

public class LegendsGame {
    // simplify game logic:
    // get player -> move
    //            -> market -> take action
    //            -> monster -> fight
    //            -> end turn
    private ArrayList<Player> player_list =  new ArrayList<Player>();
    private Board board;

    public void initialization(){
        board = new Board();
        Integer num_players = IO.getInstance().validInteger("Please enter the number of players:", 1, 5);
        HashSet<Integer> used = board.getUsedSet();
        Random rand = new Random();
        for (int i=0; i<num_players;i++){
            String name_ = IO.getInstance().validString("Please enter the name for player "+(i+1));
            Integer num_ = IO.getInstance().validInteger("Please enter the number of heros for player "+(i+1), 1, 3);
            Player new_player = new Player(name_, num_, ""+(i+1));
            player_list.add(new_player);
            Integer randint = rand.nextInt(board.getSize()*board.getSize());
            while (used.contains(randint)){randint = rand.nextInt(board.getSize()*board.getSize());}
            board.getCell(randint).getPlayer(""+(i+1));
        }
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        System.out.println(board);
    }

    public static void main(String[] args) {
        LegendsGame game = new LegendsGame();
        game.initialization();
    }
}
