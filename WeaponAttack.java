import java.util.ArrayList;

public class WeaponAttack implements AttackBehavior {
    private Integer base_damage;
    private Integer added_damage;

    WeaponAttack(Integer base, Integer added){
        base_damage = base;
        added_damage = added;
    }

    @Override
    public void attack(CanBeAttacked obj) {
        ArrayList<String> attr = new ArrayList<String>();
        ArrayList<Integer> reduces = new ArrayList<Integer>();
        attr.add("HP");
        reduces.add(base_damage+added_damage);
        obj.receiveAttack(attr, reduces);
    }
}
