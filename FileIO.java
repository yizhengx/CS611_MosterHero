import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
    private final String FireSpells_path = "FireSpells.txt";
    private final String IceSpells_path = "IceSpells.txt";
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
               // process the line.
               if (count==0){
                   count = 1;
                   continue;
               }
               if (line.matches("[\\s]*")){continue;}
               file_content.add(line);
            //    System.out.println(line);
            }
            return file_content;
        }
    }

    public static void main(String[] args) throws IOException {
        FileIO fio = FileIO.getInstance();
    }
}
