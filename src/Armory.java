// Armory Class: class for armory item
public class Armory extends Item implements Equitable{
    // Name/cost/required level/damage reduction
    private Integer damage_red;

    // Constructor
    Armory(String name_, Integer cost_, Integer level_, Integer damage_red_){
        super(name_, cost_, level_, "Armory", 0, 0, 1);
        damage_red = damage_red_;
    }

    // Accessor
    public Integer getDamageRed(){return damage_red;}

    @Override
    public void equit() {}

    @Override
    public String getInfo() {
        String message = "[" + prototype + "]" + " name: " + name +" & cost: "+cost+" & level: "+level + " & damage reduction: "+damage_red;
        return message;
    }
}
