public abstract class Spell implements Attackable {
    // Name/cost/required level/damage/mana cost
    
    protected String name;
    protected Integer cost;
    protected Integer level;
    protected Integer damage;
    protected Integer mana_cost;
    protected SpellAttack attack_behavior;
}
