package sudoku.vista;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicStuff 
{
	void playMusic(String directory) 
	{
		try 
		{
			
			File musicPath = new File(directory);
			
			if (musicPath.exists()) 
			{
				AudioInputStream audioInput= AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				//clip.loop(Clip.LOOP_CONTINUOUSLY);
				
				
			}
			else 
			{
				System.out.println("No pudo encontrar el archivo");
			}
		
		}
		
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

}