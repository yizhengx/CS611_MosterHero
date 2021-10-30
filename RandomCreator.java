import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RandomCreator implements Factory{
    // keep track of selected heros to make a balanced random creation

    public static RandomCreator random_c = null;
    ArrayList<HashSet<Integer>> used_hero; 

    private RandomCreator(){
        // used_hero = new ArrayList<HashSet<Integer>>();

    }

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
    public Potion createPotion(String info) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Weapon createWeapon(String info) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Spell createSpell(String info) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Armory createArmory(String info) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) throws IOException {
        RandomCreator rc = RandomCreator.getInstance();
        Hero h = rc.createHero("");
        System.out.println(h.getName());
    }
}
