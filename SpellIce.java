public class SpellIce extends Spell{
    // An ice spell, apart from the damage it causes it also reduces the damage of the enemy.
    private String attr_affected = "damage";
    private Integer attr_reduction = 10;

    SpellIce(){
        super();
    }

    @Override
    public void attack(CanBeAttacked obj) {
        attack_behavior = new SpellAttack(damage, attr_affected, attr_reduction);
        attack_behavior.attack(obj);
    }
}
