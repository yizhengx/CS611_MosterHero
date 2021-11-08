// SpellFire Class: a class for fire spell
public class SpellFire extends Spell {
    // A fire spell, apart from the damage it causes it also reduces the defense of the enemy.
    private Integer base_damage = 0;
    private String attr_affected = "defense";
    private Integer attr_reduction = 10*level;

    SpellFire(String name_, Integer cost_, Integer level_, Integer damage_, Integer mana_cost_){
        super(name_, cost_, level_, damage_, mana_cost_);
    }

    @Override
    public void attack(CanBeAttacked obj) {
        attack_behavior = new SpellAttack(base_damage+damage/10, attr_affected, attr_reduction);
        attack_behavior.attack(obj);
    }

    @Override
    public String getAffectedAttr() {return attr_affected;}

    @Override
    public Integer getAffectedRed() {return attr_reduction;}

    @Override
    public void setBaseDamage(Integer base_damage_) {
        base_damage = base_damage_;
    }

    @Override
    public String getInfo() {
        String message = "[" + prototype + "]" + " name: " + name +" & cost: "+cost+" & level: "+level + " & damage: "+damage+
            " & attr_affected: " + attr_affected + " & attr_reduction: "+attr_reduction;
        return message;
    }
}
