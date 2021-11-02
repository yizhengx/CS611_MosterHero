import java.net.Inet4Address;

public class Weapon implements Attackable{
    // Name/cost/level/damage/required hands
    private String protoType = "Weapon";
    private String name;
    private Integer cost;
    private Integer level; 
    private Integer damage;
    private Integer required_hands;
    private WeaponAttack attack_behavior;
    private Integer base_damage;

    Weapon(String name_, Integer cost_, Integer level_, Integer damage_, Integer required_hands_){
        name = name_;
        cost = cost_;
        level = level_;
        damage = damage_;
        required_hands = required_hands_;
    }


    public String toString(){
        String message = "" + "name: " + name +"\ncost: "+cost+"\nlevel: "+level;
        return message;
    }


    @Override
    public void attack(CanBeAttacked obj) {
        attack_behavior = new WeaponAttack(base_damage, damage);
        attack_behavior.attack(obj);
    }

    
}
