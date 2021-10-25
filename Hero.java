public class Hero{
    // Name/mana/strength/agility/dexterity/starting money/starting experience
    private String name;
    private Integer mana;
    private Integer strength;
    private Integer agility;
    private Integer dexterity;
    private Integer money;
    private Integer exp;
    private Integer level;
    private Integer HP;

    public void receiveAttack(){}
    public void attack(Attackable obj_att, CanBeAttacked obj_BeAttacked){
        obj_att.attack(obj_BeAttacked);
    }
}
