import java.util.ArrayList;

/**
 * Monster
 */
public class Monster implements Attackable, CanBeAttacked{
    
    private String name;
    private String type;
    private Integer HP;
    private Integer level;
    private Integer damage;
    private Integer defense;
    private Integer dodge_chance;
    private NormalAttack attack_behavior;

    Monster(String name_, String type_, Integer HP_, Integer level_, Integer damage_, Integer defense_, Integer dodge_chance_){
        name = name_;
        type = type_;
        HP = HP_;
        level = level_;
        damage = damage_;
        defense = defense_;
        dodge_chance = dodge_chance_;
    }

    @Override
    public void receiveAttack(ArrayList<String> attr_affected, ArrayList<Integer> attr_reduction) {
        // check if dodged
    }

    @Override
    public void attack(CanBeAttacked obj) {
        // TODO Auto-generated method stub
        attack_behavior =  new NormalAttack(damage);
        attack_behavior.attack(obj);
    }
    
}