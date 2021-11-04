import java.io.IOException;
import java.util.ArrayList;

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
        message += "\n|  +++     ++++++  ++++++++  ++++++  +++   ++  ++++++   |";
        message += "\n|  +++     +++     ++        +++     ++++  ++  ++   ++  |";
        message += "\n|  +++     ++++++  ++  ++++  ++++++  ++ ++ ++  ++    ++ |";
        message += "\n|  ++++++  +++     ++    ++  +++     ++  ++++  ++   ++  |";
        message += "\n|  ++++++  ++++++  ++++++++  ++++++  ++   +++  ++++++   |";
        message += "\n*********************************************************";
        System.out.println(message);
    }

    public void displayMap(Board board){
        String board_out = "";
        String line_offset = "*";
        for (int i=0; i<board.getSize(); i++){line_offset+="******";}
        String line_empty_offset = "*";
        for (int i=0; i<board.getSize(); i++){line_empty_offset+="     *";}
        board_out += line_offset+"\n"+"******************  GAME  MAP  ******************"+"\n"+line_offset;
        for (int i=0; i<board.getSize(); i++){
            String line = "*";
            for (int j=0; j<board.getSize(); j++){
                line += "  "+board.getCell(i,j)+"  *";
            }
            board_out+="\n"+line+"\n"+line_offset;
        }
        System.out.println(board_out);
    }

    public static void displayPlayer(Player p){
        String message = "++++++++++++++++++++++++++++++++++++++++++++++  Player "+p.getName()+" Info  +++++++++++++++++++++++++++++++++++++++++++++";
        Integer top_line = message.length();
        ArrayList<Hero> heros = p.getHeros();
        for (int i=0; i<heros.size();i++){
            message += "\nHero "+(i+1)+": " + heros.get(i);
            message += "\nHero "+(i+1)+": " + heros.get(i).getItemsInfo();
        }
        message += "\n";
        for (int i=0;i<top_line;i++){message+="+";}
        System.out.println(message);
        // System.out.printf("%-12s%-12s%-12s%-12s\n"," ","Hero 1","Hero 2", "Hero 3");
        // System.out.printf("%-12s%-12s%-12s%-12s\n","Name","...","asdfadsfasf","adsfa2322");


    }

    public static void main(String[] args) throws IOException {
        // System.out.printf("%-10s%-10s\n","asdsd","asdsfdgfhgh");  //不足十位，后加空格；
        displayPlayer(new Player("ds", 1, "s"));
    }
    // public void displayUser
}
