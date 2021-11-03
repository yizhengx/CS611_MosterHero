public class SpellIce extends Spell{
    // An ice spell, apart from the damage it causes it also reduces the damage of the enemy.
    private Integer base_damage;
    private String attr_affected = "damage";
    private Integer attr_reduction = 10;

    SpellIce(String name_, Integer cost_, Integer level_, Integer damage_, Integer mana_cost_){
        super(name_, cost_, level_, damage_, mana_cost_);
    }

    @Override
    public void attack(CanBeAttacked obj) {
        attack_behavior = new SpellAttack(base_damage+damage, attr_affected, attr_reduction);
        attack_behavior.attack(obj);
    }
    
    @Override
    public String getAffectedAttr() {return attr_affected;}

    @Override
    public Integer getAffectedRed() {return attr_reduction;}

    @Override
    public void setBaseDamage(Integer base_damage_) {base_damage = base_damage_;}
}
