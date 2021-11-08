// Item Class: all items can be put into a market should extends this class since they all have cost, name, level
public abstract class Item {
    protected String prototype;
    protected String name;
    protected Integer cost;
    protected Integer level;
    private Integer useable;
    private Integer attackable;
    private Integer equitable;

    // Constructor
    Item(String name_, Integer cost_, Integer level_, String proto_, Integer useable_, Integer attackable_, Integer equitable_){
        name = name_;
        cost = cost_;
        level = level_;
        prototype = proto_;
        useable = useable_;
        attackable = attackable_;
        equitable = equitable_;
    }

    // Accessor
    public String getName(){return name;}
    public String getType(){return prototype;}
    public Integer getCost(){return cost;}
    public Integer getLevel(){return level;}
    public Integer getUseable(){return useable;}
    public Integer getAttackable(){return attackable;}
    public Integer getEquitable(){return equitable;}
    public abstract String getInfo();

}
