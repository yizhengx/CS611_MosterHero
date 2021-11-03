public class Item {
    private String prototype;
    private String name;
    private Integer cost;
    private Integer level;
    private Integer useable;
    private Integer attackable;
    private Integer equitable;

    // Accessor
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



    public String toString(){
        String message = "[" + prototype + "]" + " name: " + name +" & cost: "+cost+" & level: "+level;
        return message;
    }
}
