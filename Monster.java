import java.util.ArrayList;
import java.util.Random;

/**
 * Monster
 */
public class Monster implements Attackable, CanBeAttacked{
    
    private String name;
    private String type;
    private Integer level;
    private Integer damage;
    private Integer defense;
    private Integer dodge_chance;
    private NormalAttack attack_behavior;

    Monster(String name_, String type_, Integer level_, Integer damage_, Integer defense_, Integer dodge_chance_){
        name = name_;
        type = type_;
        level = level_;
        damage = damage_;
        defense = defense_;
        dodge_chance = dodge_chance_;
    }

    @Override
    public void receiveAttack(ArrayList<String> attr_affected, ArrayList<Integer> attr_reduction) {
        // check if dodged
        Random rand = new Random();
        Integer randint = rand.nextInt(100);
        if (randint<dodge_chance){
            System.out.println("Unfortunely, Monster "+ name + " dodge this attack.");
        }
        else{
            Integer defense_lose = 0;
            Integer damage_lose = 0;
            for (int i=0; i<attr_affected.size(); i++){
                if (attr_affected.get(i)=="HP"){defense_lose += attr_reduction.get(i);}
                if (attr_affected.get(i)=="damage"){damage_lose += attr_reduction.get(i);}
                if (attr_affected.get(i)=="defense"){defense_lose += attr_reduction.get(i);}
            }
            defense -= defense_lose;
            damage -= damage_lose;
            String message = "Monster " + name + " loses "+defense_lose+" defense";
            if (damage_lose>0){message += " abd loses "+damage_lose+" damage.";}
            else{message+=".";}
            System.out.println(message);
        }
    }

    public String toString() {
        String message = "[Monster] Name: "+name+" Level: "+level;
        return message;
    }

    @Override
    public void attack(CanBeAttacked obj) {
        // TODO Auto-generated method stub
        attack_behavior =  new NormalAttack(damage);
        attack_behavior.attack(obj);
    }

    @Override
    public void setBaseDamage(Integer base_damage_) {}
    
}