
public class Weapon implements Attackable{
    // Name/cost/level/damage/required hands
    private String name;
    private Integer cost;
    private Integer level; 
    private Integer damage;
    private Integer required_hands;
    private WeaponAttack attack_behavior;
    private Integer base_damage;


    @Override
    public void attack(CanBeAttacked obj) {
        attack_behavior = new WeaponAttack(base_damage, damage);
        attack_behavior.attack(obj);
    }

    
}
