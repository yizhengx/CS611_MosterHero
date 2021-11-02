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
    private ArrayList<Item> items;
    private ArrayList<Attackable> attack_tools;
    private ArrayList<Useable> useables_tools;
    

    Hero(String name_, Integer mana_, Integer strength_, Integer agility_, Integer dexterity_, Integer money_, Integer exp_){
        name = name_;
        mana = mana_;
        strength = strength_;
        agility = agility_;
        dexterity = dexterity_;
        money = money_;
        exp = exp_;
        HP = 100;
        level = 1;
        dodge_chance = 0;
    }

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

    public String toString(){
        String message = "";
        message += "name:"+name+"\n";
        message += "HP:"+HP+"\n";
        message += "mana:"+mana+"\n";
        message += "strength:"+strength+"\n";
        message += "agility:"+agility+"\n";
        message += "dexterity:"+dexterity+"\n";
        message += "money:"+money+"\n";
        message += "exp:"+exp+"\n";
        message += "level:"+level;
        return message;
    }
}
