import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
    private final String FireSpells_path = "FireSpells.txt";
    private final String IceSpells_spell_path = "IceSpells.txt";
    private final String LightningSpells_path = "LightningSpells.txt";
    private final String Sorcerers_path = "Sorcerers.txt";
    private final String Warriors_path = "Warriors.txt";
    private final String Paladins_path = "Paladins.txt";
    private final String Spirits_path = "Spirits.txt";
    private final String Dragons_path = "Dragons.txt";
    private final String Exoskeletons_path = "Exoskeletons.txt";
    private final String Armory_path = "Armory.txt";
    private final String Weaponry_path = "Weaponry.txt";
    private final String Potions_path = "Potions.txt";
    private static FileIO file_reader = null;

    public static FileIO getInstance(){
        if (file_reader==null){file_reader = new FileIO();}
        return file_reader;
    }

    public ArrayList<String> getFireSpell() throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(FireSpells_path))) {
            String line;
            ArrayList<String> file_content = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
               // process the line.
               file_content.add(line);
               System.out.println(line);
            }
            return file_content;
        }
    }

    public static void main(String[] args) throws IOException {
        FileIO fio = FileIO.getInstance();
        fio.getFireSpell();
    }
}
