import java.io.IOException;
import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Hero> heros;
    private Integer num; // num of heros
    private Integer x;
    private Integer y;
    private String icon;
    private Integer dead;

    Player(String name_, Integer num_, String icon_) throws IOException{
        name = name_;
        num = num_;
        icon = icon_;
        heros = new ArrayList<Hero>();
        heroConfigure();
        dead = 0;
    }

    // Accessor
    public String getName(){return name;}
    public String getIcon(){return icon;}
    public Integer getX(){return x;}
    public Integer getY(){return y;}
    public Integer getNumHeros(){return heros.size();}
    public Hero getHero(Integer index){return heros.get(index);}
    public ArrayList<Hero> getHeros(){return heros;}

    // Setter
    public void setX(Integer x_){x = x_;}
    public void setY(Integer y_){y = y_;}

    public void heroConfigure() throws IOException{
        for (int i=0; i<num; i++){
            heros.add(RandomCreator.getInstance().createHero(""));
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
