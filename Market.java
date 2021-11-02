import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Market {

    private ArrayList<Item> items;

    private ArrayList<Spell> spells;
    private ArrayList<Weapon> weapons;
    private ArrayList<Potion> potions;
    private ArrayList<Armory> armories;
    private RandomCreator rc;

    Market() throws IOException{
        spells = new ArrayList<Spell>();
        weapons = new ArrayList<Weapon>();
        potions = new ArrayList<Potion>();
        armories =  new ArrayList<Armory>();
        items = new ArrayList<Item>();
        rc = RandomCreator.getInstance();
        initialization();
    }

    public void initialization() throws IOException{
        Random rand = new Random();
        Integer randint = rand.nextInt(3)+2;
        for (int i=0; i<randint; i++){
            addSpell(rc.createSpell(""));
        }
        randint = rand.nextInt(3)+2;
        for (int i=0; i<randint; i++){
            addWeapon(rc.createWeapon(""));
        }
        randint = rand.nextInt(3)+2;
        for (int i=0; i<randint; i++){
            addPotion(rc.createPotion(""));
        }
        randint = rand.nextInt(3)+2;
        for (int i=0; i<randint; i++){
            addArmory(rc.createArmory(""));
        }
    }

    public void addSpell(Spell spell){items.add(spell);}
    public void addWeapon(Weapon weapon){items.add(weapon);}
    public void addPotion(Potion potion){items.add(potion);}
    public void addArmory(Armory armory){items.add(armory);}

    public Spell getSpell(Integer index){return spells.get(index);}
    public Weapon getWeapon(Integer index){return weapons.get(index);}
    public Potion getPotion(Integer index){return potions.get(index);}
    public Armory getArmory(Integer index){return armories.get(index);}

    public void print(){
        for (int i=0; i<items.size(); i++){
            System.out.println(i+": "+items.get(i));
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
        System.out.println("Spell");
        for (int i=0; i<m.spells.size(); i++){
            System.out.println(m.spells.get(i));
        }
        System.out.println("Weapon");
        for (int i=0; i<m.weapons.size(); i++){
            System.out.println(m.weapons.get(i));
        }
    }
    
}
