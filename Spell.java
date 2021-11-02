public abstract class Spell implements Attackable {
    // Name/cost/required level/damage/mana cost
    
    protected String protoType = "Spell";
    protected String name;
    protected Integer cost;
    protected Integer level;
    protected Integer damage;
    protected Integer mana_cost;
    protected SpellAttack attack_behavior;

    Spell(String name_, Integer cost_, Integer level_, Integer damage_, Integer mana_cost_){
        name = name_;
        cost = cost_;
        level = level_;
        damage = damage_;
        mana_cost = mana_cost_;
    }

    public String toString(){
        String message = "" + "name: " + name +"\ncost: "+cost+"\nlevel: "+level;
        return message;
    }

    public void test(){
        System.out.println("Spell Class");
    }


}
