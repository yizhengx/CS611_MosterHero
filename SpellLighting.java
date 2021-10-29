public class SpellLighting extends Spell {
    // A lightning spell, apart from the damage it causes it also reduces the dodge chance of the enemy.
    private String attr_affected = "dodge_chance";
    private Integer attr_reduction = 10;

    SpellLighting(){
        super();
    }

    @Override
    public void attack(CanBeAttacked obj) {
        attack_behavior = new SpellAttack(damage, attr_affected, attr_reduction);
        attack_behavior.attack(obj);
    }
}
