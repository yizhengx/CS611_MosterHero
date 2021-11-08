// Spell Class: all specific spell class should extend this class
public abstract class Spell extends Item implements Attackable {
    // Name/cost/required level/damage/mana cost
    protected String protoType = "Spell";
    protected Integer damage;
    protected Integer mana_cost;
    protected SpellAttack attack_behavior;

    Spell(String name_, Integer cost_, Integer level_, Integer damage_, Integer mana_cost_){
        super(name_, cost_, level_, "Spell", 0, 1, 0);
        damage = damage_;
        mana_cost = mana_cost_;
    }

    // Accessor
    public abstract String getAffectedAttr();
    public abstract Integer getAffectedRed();
    public Integer getMana(){return mana_cost;}

    // set the base damage by referring to the host who cast the spell for attacking
    public abstract void setBaseDamage(Integer base_damage_);
}
