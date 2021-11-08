import java.io.*;
import javax.sound.sampled.*;

// Music Class: provides a way of playing bgm
public class Music {
	private String path;
	Music(String path_){
		path = path_;
	}

	void playMusic(){
		try{
			File musicPath = new File(path);
			if(musicPath.exists()){
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch(Exception ex){ex.printStackTrace();}
	}
    public static void main(String[] args) {
        String filepath = "config/bgm.wav";
        Music musicObject = new Music(filepath);
        musicObject.playMusic();
    }
}