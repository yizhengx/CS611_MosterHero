import java.util.ArrayList;

public class SpellFire extends Spell {
    // A fire spell, apart from the damage it causes it also reduces the defense of the enemy.
    private Integer base_damage = 0;
    private String attr_affected = "defense";
    private Integer attr_reduction = 10;

    SpellFire(){
        super();
    }

    // 

    @Override
    public void attack(CanBeAttacked obj) {
        attack_behavior = new SpellAttack(damage, attr_affected, attr_reduction);
        attack_behavior.attack(obj);
    }

    public static void main(String[] args) {
        SpellFire sf = new SpellFire();
    }
}
