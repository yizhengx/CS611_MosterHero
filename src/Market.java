import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

// Market Class: class for a market which contains many items
public class Market {

    private ArrayList<Item> items;
    private RandomCreator rc;

    Market() throws IOException{
        items = new ArrayList<Item>();
        rc = RandomCreator.getInstance();
        initialization();
    }

    public ArrayList<Item> getItems(){return items;}

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
        randint = rand.nextInt(2)+3;
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

    public static void main(String[] args) throws IOException {
        Market m = new Market();
    }
    
}
