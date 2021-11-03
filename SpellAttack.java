import java.util.*;

public class SpellAttack implements AttackBehavior{
    private Integer damage;
    private String attr_affected;
    private Integer reduce;

    SpellAttack(Integer damage_, String attr, Integer reduce_){
        damage = damage_;
        attr_affected = attr;
        reduce = reduce_;
    }

    @Override
    public void attack(CanBeAttacked obj) {
        ArrayList<String> attr = new ArrayList<String>();
        ArrayList<Integer> reduces = new ArrayList<Integer>();
        attr.add("HP");
        attr.add(attr_affected);
        reduces.add(damage);
        reduces.add(reduce);
        obj.receiveAttack(attr, reduces);
    }
}
