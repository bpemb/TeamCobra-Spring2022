import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class TowerOfChampions {


	public static void main(String[] args) throws IOException {

		BufferedReader control;
		String input;
		String output;
		
		//---saif created main menu--- 
		MainMenuAndSounds.mainMenu();
		
		Brain txtGame = new Brain();
		txtGame.Intro();
		txtGame.setPuzzleRooms();
		txtGame.setMonsterRooms();
		txtGame.setMonsterItems();
		txtGame.setRoomInventory();
		control = new BufferedReader(new InputStreamReader(System.in));
		
		
		do
		{
			System.out.print("> ");
			input = control.readLine();
			output = txtGame.RunCommand(input);
			System.out.println(output);
		}while (!"stop".equals(input));
	}

}
