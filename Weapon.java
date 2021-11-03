
public class Weapon extends Item implements Attackable, Equitable{
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
        super(name_, cost_, level_, "Weapon", 0, 0, 1);
        name = name_;
        cost = cost_;
        level = level_;
        damage = damage_;
        required_hands = required_hands_;
    }

    public Integer getDamage(){return damage;}

    public void setBaseDamage(Integer base_damage_){base_damage = base_damage_;}

    @Override
    public void attack(CanBeAttacked obj) {
        attack_behavior = new WeaponAttack(base_damage, damage);
        attack_behavior.attack(obj);
    }


    @Override
    public void equit() {
        // TODO Auto-generated method stub
        
    }

    
}
