import java.util.ArrayList;
import java.util.Map;

public abstract class Hero implements Attackable, CanBeAttacked{
    // Name/mana/strength/agility/dexterity/starting money/starting experience
    private String name;
    private Integer HP;
    private Integer mana;
    protected Integer strength;     // AAdded to the amount of damage they deal when using a weapon
    protected Integer agility;      // Increases their chance to dodge an incoming attack.
    protected Integer dexterity;    // Increases the amount of damage they deal when casting a spell
    protected Integer dodge_chance; // Determined by agility
    private Integer money;        // Money can used to buy item in the market or obtained from selling item in the market
    private Integer exp;          // 
    private Integer level;

    // Accesser
    public Integer getHP(){return HP;}
    public Integer getMana(){return mana;}
    public Integer getMoney(){return money;}
    public Integer getExp(){return exp;}
    public Integer getLevel(){return level;}
    public String getName(){return name;}

    // Setter
    public void setName(String name_){name = name_;}

    public void use(Useable obj){
        Map<String, Integer> attr_details = obj.beUsed();
    }

    public abstract void level_up();

    @Override
    public void receiveAttack(ArrayList<String> attr_affected, ArrayList<Integer> attr_reduction) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void attack(CanBeAttacked obj) {
        // TODO Auto-generated method stub
        
    }
}
