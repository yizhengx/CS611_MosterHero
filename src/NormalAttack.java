import java.util.*;

// NormalAttack Class: an attack strategy without any weapon or magic
public class NormalAttack implements AttackBehavior {
    private Integer damage;

    NormalAttack(Integer damage_){
        damage = damage_;
    }

    @Override
    public void attack(CanBeAttacked obj) {
        ArrayList<String> attr = new ArrayList<String>();
        ArrayList<Integer> reduces = new ArrayList<Integer>();
        attr.add("HP");
        reduces.add(damage);
        obj.recieveAttack(attr, reduces);
    }
}
