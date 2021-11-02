import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RandomCreator implements Factory{
    // keep track of selected heros to make a balanced random creation

    public static RandomCreator random_c = null;
    ArrayList<HashSet<Integer>> used_hero; 

    private RandomCreator(){}

    public static RandomCreator getInstance(){
        if (random_c==null){random_c = new RandomCreator();}
        return random_c;
    }

    @Override
    public Hero createHero(String info) throws IOException{
        // Name/mana/strength/agility/dexterity/starting money/starting experience
        Random rand = new Random();
        Integer type = 0; //rand.nextInt(3);
        if (type==0){
            ArrayList<String> hero_lines = FileIO.getInstance().getWarriorsFile();
            Integer index = rand.nextInt(hero_lines.size()-1)+1;
            String[] specs = hero_lines.get(index).split("\\s+");
            Hero new_hero = new Warriors(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), Integer.parseInt(specs[5]), Integer.parseInt(specs[6]));
            return new_hero;
        }
        else if (type==1){
            ArrayList<String> hero_lines = FileIO.getInstance().getSorcerersFile();
            Integer index = rand.nextInt(hero_lines.size()-1)+1;
            String[] specs = hero_lines.get(index).split("\\s+");
            Hero new_hero = new Sorcerers(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), Integer.parseInt(specs[5]), Integer.parseInt(specs[6]));
            return new_hero;
        }
        else{
            ArrayList<String> hero_lines = FileIO.getInstance().getPaladinsFile();
            Integer index = rand.nextInt(hero_lines.size()-1)+1;
            String[] specs = hero_lines.get(index).split("\\s+");
            Hero new_hero = new Paladins(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), Integer.parseInt(specs[5]), Integer.parseInt(specs[6]));
            return new_hero;
        }
    }

    @Override
    public Monster createMoster(String info) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Potion createPotion(String info) throws IOException {
        // Name/cost/required level/attribute increase/attribute affected
        Random rand = new Random();
        ArrayList<String> potion_lines = FileIO.getInstance().getPaladinsFile();
        Integer index = rand.nextInt(potion_lines.size()-1)+1;
        String[] specs = potion_lines.get(index).split("\\s+");
        Potion new_potion = new Potion(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
            Integer.parseInt(specs[3]), specs[4]);
        return new_potion;
    }

    @Override
    public Weapon createWeapon(String info) throws IOException {
        Random rand = new Random();
        ArrayList<String> weapon_lines = FileIO.getInstance().getWeaponryFile();
        Integer index = rand.nextInt(weapon_lines.size()-1)+1;
        String[] specs = weapon_lines.get(index).split("\\s+");
        Weapon new_weapon = new Weapon(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
            Integer.parseInt(specs[3]), Integer.parseInt(specs[4]));
        return new_weapon;
    }

    @Override
    public Spell createSpell(String info) throws IOException {
        Random rand = new Random();
        Integer type = 0; //rand.nextInt(3);
        if (type==0){
            ArrayList<String> spell_lines = FileIO.getInstance().getFireSpellsFile();
            Integer index = rand.nextInt(spell_lines.size()-1)+1;
            String[] specs = spell_lines.get(index).split("\\s+");
            Spell new_spell = new SpellFire(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]));
            return new_spell;
        }
        else if (type==1){
            ArrayList<String> spell_lines = FileIO.getInstance().getIceSpellsFile();
            Integer index = rand.nextInt(spell_lines.size()-1)+1;
            String[] specs = spell_lines.get(index).split("\\s+");
            Spell new_spell = new SpellIce(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]));
            return new_spell;
        }
        else{
            ArrayList<String> spell_lines = FileIO.getInstance().getLightningSpellsFile();
            Integer index = rand.nextInt(spell_lines.size()-1)+1;
            String[] specs = spell_lines.get(index).split("\\s+");
            Spell new_spell = new SpellLighting(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]));
            return new_spell;
        }
    }

    @Override
    public Armory createArmory(String info) throws IOException {
        // Name/cost/required level/damage reduction
        Random rand = new Random();
        ArrayList<String> armory_lines = FileIO.getInstance().getArmoryFile();
        Integer index = rand.nextInt(armory_lines.size()-1)+1;
        String[] specs = armory_lines.get(index).split("\\s+");
        Armory new_armory = new Armory(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), Integer.parseInt(specs[3]));
        return new_armory;
    }

    public static void main(String[] args) throws IOException {
        RandomCreator rc = RandomCreator.getInstance();
        Hero h = rc.createHero("");
        System.out.println(h.getName());
    }
}
