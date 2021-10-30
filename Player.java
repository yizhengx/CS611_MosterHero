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
    }

    // Accessor
    public String getName(){return name;}
    public String getIcon(){return icon;}

    public void heroConfigure() throws IOException{
        for (int i=0; i<num; i++){
            heros.add(RandomCreator.getInstance().createHero(""));
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
}
