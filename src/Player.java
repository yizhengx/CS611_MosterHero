import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

// Player Class: a class for player with a team of heros
public class Player {
    private String name;
    private ArrayList<Hero> heros;
    private Integer num; // num of heros
    private Integer x;
    private Integer y;
    private String icon;
    private Boolean dead;

    Player(String name_, Integer num_, String icon_) throws IOException{
        name = name_;
        num = num_;
        icon = icon_;
        heros = new ArrayList<Hero>();
        heroConfigure();
        dead = false;
    }

    // Accessor
    public String getName(){return name;}
    public String getIcon(){return icon;}
    public Integer getX(){return x;}
    public Integer getY(){return y;}
    public Integer getNumHeros(){return heros.size();}
    public Hero getHero(Integer index){return heros.get(index);}
    public ArrayList<Hero> getHeros(){return heros;}
    public Boolean isDead(){return dead;}

    // Setter
    public void setX(Integer x_){x = x_;}
    public void setY(Integer y_){y = y_;}
    public void setDead(){dead = true;}

    // Configure/Initialize heros for a player
    public void heroConfigure() throws IOException{
        HashSet<String> used_name = new HashSet<String>();
        for (int i=0; i<num; i++){
            Hero new_h = RandomCreator.getInstance().createHero("");
            while (used_name.contains(new_h.getName())){new_h = RandomCreator.getInstance().createHero("");}
            heros.add(new_h);
            used_name.add(new_h.getName());
        }
        for (int i=0; i<heros.size();i++){
            System.out.println("Player "+name+" gets hero "+heros.get(i).getName());
        }
    }

    public String toString(){
        String message = "";
        message += "+++++++++++++++++++++++++++++++++++++++++++";
        for (int i=0; i<heros.size(); i++){
            message += "\n"+heros.get(i);
        }
        message += "\n" + "+++++++++++++++++++++++++++++++++++++++++++";
        return message;
    }

    public static void main(String[] args) throws IOException {
        Player p = new Player("22", 2, "X");
    }
}
