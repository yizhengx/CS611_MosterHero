import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

// RandomCreator Class: a concrete factory class with a random creating way to create all items/roles
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
        ArrayList<String> warriors_lines = FileIO.getInstance().getWarriorsFile();
        ArrayList<String> sorcerers_lines = FileIO.getInstance().getSorcerersFile();
        ArrayList<String> paladins_lines = FileIO.getInstance().getPaladinsFile();
        Integer index = rand.nextInt(warriors_lines.size()+sorcerers_lines.size()+paladins_lines.size())+1;
        if (index>(warriors_lines.size()+sorcerers_lines.size())){
            String[] specs = paladins_lines.get(index-warriors_lines.size()-sorcerers_lines.size()-1).split("\\s+");
            Hero new_hero = new Paladins(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), Integer.parseInt(specs[5]), Integer.parseInt(specs[6]));
            return new_hero;
        }else if(index>warriors_lines.size()){
            String[] specs = sorcerers_lines.get(index-warriors_lines.size()-1).split("\\s+");
            Hero new_hero = new Sorcerers(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), Integer.parseInt(specs[5]), Integer.parseInt(specs[6]));
            return new_hero;
        }else{
            String[] specs = sorcerers_lines.get(index-1).split("\\s+");
            Hero new_hero = new Warriors(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), Integer.parseInt(specs[5]), Integer.parseInt(specs[6]));
            return new_hero;
        }
    }

    @Override
    public Monster createMoster(String info) throws IOException {
        Random rand = new Random();
        ArrayList<String> d_lines = FileIO.getInstance().getDragonsFile();
        ArrayList<String> e_lines = FileIO.getInstance().getExoskeletonsFile();
        ArrayList<String> s_lines = FileIO.getInstance().getSpiritsFile();
        // Name/level/damage/defense/dodge chance
        Integer index = rand.nextInt(d_lines.size()+e_lines.size()+s_lines.size())+1;
        if (index>(d_lines.size()+e_lines.size())){
            String[] specs = s_lines.get(index-d_lines.size()-e_lines.size()-1).split("\\s+");
            Monster new_moster = new Monster(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), "Spirit");
            return new_moster;
        }else if(index>d_lines.size()){
            String[] specs = e_lines.get(index-d_lines.size()-1).split("\\s+");
            Monster new_moster = new Monster(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), "Exoskeleton");
            return new_moster;
        }else{
            String[] specs = d_lines.get(index-1).split("\\s+");
            Monster new_moster = new Monster(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), Integer.parseInt(specs[3]), Integer.parseInt(specs[4]), "Dragon");
            return new_moster;
        }
    }

    @Override
    public Potion createPotion(String info) throws IOException {
        // Name/cost/required level/attribute increase/attribute affected
        Random rand = new Random();
        ArrayList<String> potion_lines = FileIO.getInstance().getPotionsFile();
        Integer index = rand.nextInt(potion_lines.size());
        String[] specs = potion_lines.get(index).split("\\s+");
        Potion new_potion = new Potion(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
            Integer.parseInt(specs[3]), specs[4]);
        return new_potion;
    }

    @Override
    public Weapon createWeapon(String info) throws IOException {
        Random rand = new Random();
        ArrayList<String> weapon_lines = FileIO.getInstance().getWeaponryFile();
        Integer index = rand.nextInt(weapon_lines.size());
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
            Integer index = rand.nextInt(spell_lines.size());
            String[] specs = spell_lines.get(index).split("\\s+");
            Spell new_spell = new SpellFire(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]));
            return new_spell;
        }
        else if (type==1){
            ArrayList<String> spell_lines = FileIO.getInstance().getIceSpellsFile();
            Integer index = rand.nextInt(spell_lines.size());
            String[] specs = spell_lines.get(index).split("\\s+");
            Spell new_spell = new SpellIce(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), 
                Integer.parseInt(specs[3]), Integer.parseInt(specs[4]));
            return new_spell;
        }
        else{
            ArrayList<String> spell_lines = FileIO.getInstance().getLightningSpellsFile();
            Integer index = rand.nextInt(spell_lines.size());
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
        Integer index = rand.nextInt(armory_lines.size());
        String[] specs = armory_lines.get(index).split("\\s+");
        Armory new_armory = new Armory(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), Integer.parseInt(specs[3]));
        return new_armory;
    }

    public static void main(String[] args) throws IOException {
        RandomCreator rc = RandomCreator.getInstance();
        Potion p = rc.createPotion("");
        System.out.println(p.getInfo());
    }
}
