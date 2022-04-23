import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MainMenuAndSounds {
	
	//----Saif Shaikh made this class----
	
	public MainMenuAndSounds() {
		
	}

	//This method is the main menu responsible for starting game and audio files
	public static void mainMenu() throws LineUnavailableException, IOException, UnsupportedAudioFileException  {
		System.out.println("  -----------------------------");
		System.out.println("/                               \\");
		System.out.println("|      Tower of Champions       |");
		System.out.println("|                               |    ");
		System.out.println("|          New Game (1)         |    ");
		System.out.println("|          Continue (2)         |    ");
		System.out.println("|          Setting  (3)         |    ");
		System.out.println("|            Exit   (4)         |    ");
		System.out.println("\\                               /");
		System.out.println("  -----------------------------");
		
		Scanner input = new Scanner(System.in);
		System.out.print("> ");
		int command = input.nextInt();
		
		if (command == 1) {
			//continues to game 
		}
		else if(command == 2){
			
			//leads to saved game? still in the works 
			
		}
		else if(command == 3){
			
			int temp = 0;
			
			do{
				
			System.out.println("  -----------------------------");
			System.out.println("          Commands  (1)             ");
			System.out.println("            Audio   (2)             ");
			System.out.println("          Main menu (3)             ");
			System.out.println("  -----------------------------");

			Scanner input2 = new Scanner(System.in);
			System.out.print("> ");
			int commandSettings = input.nextInt();
			
			if (commandSettings == 1) {
				
				int count = 1;
				for ( String x: Brain.commands)
				{
					System.out.println(count+". " + x);
					count ++;
				}
				
			}
			else if(commandSettings == 2) {
				
				
				File file = new File("src/MusicBox.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				Clip clip = AudioSystem.getClip();
				clip.open(audioStream);
				
				clip.start();
    			
				
			}
			else if(commandSettings == 3){
				
				temp = 3;
			}
			
			}while(temp != 3);
			
			mainMenu();
			
		}
		else if(command == 4){
			System.out.println();
			
			System.out.println("Game Ended, GoodBye!");
			System.exit(0);
		}
		
		
	}
	
	
	//game over music file 
	public static void GameEnd() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		File file = new File("src/GameOver.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		
		clip.start();
		
		
		
		
	}
	

}
