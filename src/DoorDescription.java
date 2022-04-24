import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class DoorDescription {
	
	//attributes
	private String doorDescription;
	private String start;
	private String end;
	private int startRoomID;
	private int endRoomID;

	ArrayList<Door> doors = new ArrayList<Door>();
	
	//read door.txt
	private Scanner readFile = null;
	private String fileName = "doors" + ".txt";  // only change the first quotations

	//method
	public DoorDescription() {
		
		try
		{
			readFile = new Scanner(new File(fileName));
			
			while(readFile.hasNextLine())
			{
				doorDescription = readFile.nextLine();
				
				start = readFile.nextLine();
				startRoomID = Integer.parseInt(start);
				
				end = readFile.nextLine();
				endRoomID = Integer.parseInt(end);
				
				doors.add(new Door(doorDescription, startRoomID, endRoomID));
			}
		}
		catch (IOException ex)
		{
			
		}
		
		
	}

}
