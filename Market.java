import java.util.ArrayList;

public class Market {

    private ArrayList<Spell> spells;
    private ArrayList<Weapon> weapons;
    private ArrayList<Potion> potions;
    private ArrayList<Armory> armories;
    private RandomCreator rc;

    Market(){
        spells = new ArrayList<Spell>();
        weapons = new ArrayList<Weapon>();
        potions = new ArrayList<Potion>();
        armories =  new ArrayList<Armory>();;
        rc = RandomCreator.getInstance();
    }

    

    public void addSpell(Spell spell){spells.add(spell);}
    public void addWeapon(Weapon weapon){weapons.add(weapon);}
    public void addPotion(Potion potion){potions.add(potion);}
    public void addArmory(Armory armory){armories.add(armory);}

    public Spell getSpell(Integer index){return spells.get(index);}
    public Weapon getWeapon(Integer index){return weapons.get(index);}
    public Potion getPotion(Integer index){return potions.get(index);}
    public Armory getArmory(Integer index){return armories.get(index);}


    
}
