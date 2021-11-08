import java.io.IOException;
import java.util.ArrayList;

// Display Class: used to display map/market/player
public class Display {
    static Display gui = null;

    private Display(){};
    public static Display getInstance(){
        if (gui==null){gui=new Display();}
        return gui;
    }

    public void displayIntro(){
        String message = "";
        message += "*********************************************************";
        message += ColorString.getColor("\n|  +++     ++++++  ++++++++  ++++++  +++   ++  ++++++   |", "red");
        message += ColorString.getColor("\n|  +++     +++     ++        +++     ++++  ++  ++   ++  |", "blue");
        message += ColorString.getColor("\n|  +++     ++++++  ++  ++++  ++++++  ++ ++ ++  ++    ++ |", "yellow");
        message += ColorString.getColor("\n|  ++++++  +++     ++    ++  +++     ++  ++++  ++   ++  |", "purple");
        message += ColorString.getColor("\n|  ++++++  ++++++  ++++++++  ++++++  ++   +++  ++++++   |", "green");
        message += "\n*********************************************************";
        System.out.println(message);
        System.out.println("Welcom to LEGENDS designed by Yizheng!!!");
        System.out.println("In this game, multiple players are allowed to own 1-3 heros to explore a map.");
        System.out.println("Each time a player have a chance to encourter monsters with the same number as heros.");
        System.out.println("Each time in a market, the player can buy or sell items for each hero.");
        System.out.println("Now enjoy your journey.");
    }

    public void displayEnd() {
        String message = "";
        message += "*********************************************************";
        message += "\n|  ++++++  ++++++  ++++++  ++     ++   ++++    ++    ++ |";
        message += "\n|  ++      +++     +++      ++  ++   ++    ++  ++    ++ |";
        message += "\n|  ++++++  ++++++  ++++++    ++++    ++    ++  ++    ++ |";
        message += "\n|      ++  +++     +++        ++     ++    ++  ++    ++ |";
        message += "\n|  ++++++  ++++++  ++++++     ++       ++++     ++++++  |";
        message += "\n*********************************************************";
        System.out.println(message);
    }

    public void displayMap(Board board){
        String board_out = "";
        String line_offset = "*";
        for (int i=0; i<board.getSize(); i++){line_offset+="******";}
        String title_offset_left = "";
        for (int i=0; i<(line_offset.length()-13)/2;i++){title_offset_left+="*";}
        String title_offset_right = "";
        for (int i=0; i<line_offset.length()-title_offset_left.length()-13;i++){title_offset_right+="*";}
        board_out += line_offset+"\n"+title_offset_left+"  GAME  MAP  "+title_offset_right+"\n"+line_offset;
        for (int i=0; i<board.getSize(); i++){
            String line = "*";
            for (int j=0; j<board.getSize(); j++){
                line += "  "+board.getCell(i,j)+"  *";
            }
            board_out+="\n"+line+"\n"+line_offset;
        }
        System.out.println(board_out);
    }

    public void displayPlayer(Player p){
        String message = "++++++++++++++++++++++++++++++++++++++++++++++++++++  Player "+p.getName()+" Info  +++++++++++++++++++++++++++++++++++++++++++++++++++";
        Integer top_line = message.length();
        ArrayList<Hero> heros = p.getHeros();
        for (int i=0; i<heros.size();i++){
            message += "\nHero "+(i+1)+": " + heros.get(i);
            String weapon_name = "None";
            String armory_name = "None";
            if (heros.get(i).getEquippedWeapon()!=null){weapon_name = heros.get(i).getEquippedWeapon().getName();}
            if (heros.get(i).getEquippArmory()!=null){armory_name = heros.get(i).getEquippArmory().getName();}
            message += "\nHero "+(i+1)+": " + "Equipped Weapon: " + weapon_name +" & Equipped Armory: "+ armory_name
             + " & "+  heros.get(i).getItemsInfo();
        }
        message += "\n";
        for (int i=0;i<top_line;i++){message+="+";}
        System.out.println(ColorString.getColor(message, "yellow"));
    }

    public void displayMarket(Market m){
        System.out.println(ColorString.getColor("--------------------------------------------------------- Market -------------------------------------------------------", "blue"));
        for (int i=0; i<m.getItems().size(); i++){
            System.out.println(ColorString.getColor((i+1)+": "+m.getItems().get(i).getInfo(), "blue"));
        }
        System.out.println(ColorString.getColor("------------------------------------------------------------------------------------------------------------------------", "blue"));

    }

    public static void main(String[] args) throws IOException {
        Display gui = new Display();
        gui.displayIntro();
    }
}
