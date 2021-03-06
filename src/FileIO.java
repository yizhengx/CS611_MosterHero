import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// FileIO Class: parse all file contents to string contents
public class FileIO {
    private final String FireSpells_path = "ConfigFiles/FireSpells.txt";
    private final String IceSpells_path = "ConfigFiles/IceSpells.txt";
    private final String LightningSpells_path = "ConfigFiles/LightningSpells.txt";
    private final String Sorcerers_path = "ConfigFiles/Sorcerers.txt";
    private final String Warriors_path = "ConfigFiles/Warriors.txt";
    private final String Paladins_path = "ConfigFiles/Paladins.txt";
    private final String Spirits_path = "ConfigFiles/Spirits.txt";
    private final String Dragons_path = "ConfigFiles/Dragons.txt";
    private final String Exoskeletons_path = "ConfigFiles/Exoskeletons.txt";
    private final String Armory_path = "ConfigFiles/Armory.txt";
    private final String Weaponry_path = "ConfigFiles/Weaponry.txt";
    private final String Potions_path = "ConfigFiles/Potions.txt";
    private static FileIO file_reader = null;

    public static FileIO getInstance(){
        if (file_reader==null){file_reader = new FileIO();}
        return file_reader;
    }

    
    public ArrayList<String> getFireSpellsFile() throws IOException{return getFileContent(FireSpells_path);}
    public ArrayList<String> getIceSpellsFile() throws IOException{return getFileContent(IceSpells_path);}
    public ArrayList<String> getLightningSpellsFile() throws IOException{return getFileContent(LightningSpells_path);}
    public ArrayList<String> getWarriorsFile() throws IOException{return getFileContent(Warriors_path);}
    public ArrayList<String> getSorcerersFile() throws IOException{return getFileContent(Sorcerers_path);}
    public ArrayList<String> getPaladinsFile() throws IOException{return getFileContent(Paladins_path);}
    public ArrayList<String> getSpiritsFile() throws IOException{return getFileContent(Spirits_path);}
    public ArrayList<String> getDragonsFile() throws IOException{return getFileContent(Dragons_path);}
    public ArrayList<String> getExoskeletonsFile() throws IOException{return getFileContent(Exoskeletons_path);}
    public ArrayList<String> getArmoryFile() throws IOException{return getFileContent(Armory_path);}
    public ArrayList<String> getWeaponryFile() throws IOException{return getFileContent(Weaponry_path);}
    public ArrayList<String> getPotionsFile() throws IOException{return getFileContent(Potions_path);}

    public ArrayList<String> getFileContent(String path) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            ArrayList<String> file_content = new ArrayList<String>();
            Integer count = 0;
            while ((line = br.readLine()) != null) {
               if (count==0){
                   count = 1;
                   continue;
               }
               if (line.matches("[\\s]*")){continue;}
               file_content.add(line);
            }
            return file_content;
        }
    }

    public static void main(String[] args) throws IOException {
        FileIO fio = FileIO.getInstance();
        fio.getPotionsFile();
    }
}
