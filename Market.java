import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Market {

    private ArrayList<Item> items;
    // private ArrayList<Spell> spells;
    // private ArrayList<Weapon> weapons;
    // private ArrayList<Potion> potions;
    // private ArrayList<Armory> armories;
    private RandomCreator rc;

    Market() throws IOException{
        // spells = new ArrayList<Spell>();
        // weapons = new ArrayList<Weapon>();
        // potions = new ArrayList<Potion>();
        // armories =  new ArrayList<Armory>();
        items = new ArrayList<Item>();
        rc = RandomCreator.getInstance();
        initialization();
    }

    public void initialization() throws IOException{
        Random rand = new Random();
        HashSet<String> has = new HashSet<String>();
        Integer randint = rand.nextInt(3)+2;
        for (int i=0; i<randint; i++){
            Spell new_spell = rc.createSpell("");
            while (has.contains(new_spell.getName())){
                new_spell = rc.createSpell("");
            }
            addSpell(new_spell);
            has.add(new_spell.getName());
        }
        randint = rand.nextInt(2)+1;
        for (int i=0; i<randint; i++){
            Weapon new_weapon = rc.createWeapon("");
            while (has.contains(new_weapon.getName())){
                new_weapon = rc.createWeapon("");
            }
            addWeapon(new_weapon);
            has.add(new_weapon.getName());

        }
        randint = rand.nextInt(2)+1;
        for (int i=0; i<randint; i++){
            Potion new_potion = rc.createPotion("");
            while (has.contains(new_potion.getName())){
                new_potion = rc.createPotion("");
            }
            addPotion(new_potion);
            has.add(new_potion.getName());
        }
        randint = rand.nextInt(2)+1;
        for (int i=0; i<randint; i++){
            addArmory(rc.createArmory(""));
            Armory new_armory = rc.createArmory("");
            while (has.contains(new_armory.getName())){
                new_armory = rc.createArmory("");
            }
            addArmory(new_armory);
            has.add(new_armory.getName());
        }
    }

    private void addSpell(Spell spell){items.add(spell);}
    private void addWeapon(Weapon weapon){items.add(weapon);}
    private void addPotion(Potion potion){items.add(potion);}
    private void addArmory(Armory armory){items.add(armory);}

    public ArrayList<Item> getItems(){return items;}

    // public Spell getSpell(Integer index){return spells.get(index);}
    // public Weapon getWeapon(Integer index){return weapons.get(index);}
    // public Potion getPotion(Integer index){return potions.get(index);}
    // public Armory getArmory(Integer index){return armories.get(index);}

    public void print(){
        for (int i=0; i<items.size(); i++){
            System.out.println((i+1)+": "+items.get(i));
        }
        // System.out.println("Spell");
        // for (int i=0; i<spells.size(); i++){
        //     System.out.println(i+": "+spells.get(i));
        // }
        // System.out.println("Weapon");
        // for (int i=0; i<weapons.size(); i++){
        //     System.out.println((spells.size()+i)+": "+weapons.get(i));
        // }
        // System.out.println("armory");
        // for (int i=0; i<armories.size(); i++){
        //     System.out.println((spells.size()+weapons.size()+i)+": "+armories.get(i));
        // }
        // System.out.println("potions");
        // for (int i=0; i<potions.size(); i++){
        //     System.out.println((spells.size()+weapons.size()+armories.size()+i)+": "+potions.get(i));
        // }
    }

    public static void main(String[] args) throws IOException {
        Market m = new Market();
        m.print();
    }
    
}
