import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	
	private String roomName;
	private String roomDescription;
	private String roomID;
	private String roomNorth;
	private String roomSouth;
	private String roomEast;
	private String roomWest;
	private String booleanVisited;
	private int north;
	private int south;
	private int east;
	private int west;
	
	private Scanner readFile = null;
	private String fileName = "rooms" + ".txt";
	
	ArrayList<Room> rooms = new ArrayList<Room>();
	
	public Map() {
		try
		{
			readFile = new Scanner(new File(fileName));
			
			
			
			while(readFile.hasNextLine())
			{
				 
				
				
				roomName = readFile.nextLine();
				
				String roomDESCLine1 =readFile.nextLine();
				String roomDESCLine2 =readFile.nextLine();
				String roomDESCLine3 =readFile.nextLine();
				
				roomDescription = roomDESCLine1 +"\n"+ roomDESCLine2 +"\n"+ roomDESCLine3;
				
				
				roomID = readFile.nextLine();
				int ID = Integer.parseInt(roomID);
				
				roomNorth = readFile.nextLine();
				north = Integer.parseInt(roomNorth);
				
				roomSouth = readFile.nextLine();
				south = Integer.parseInt(roomSouth);
				
				roomEast = readFile.nextLine();
				east = Integer.parseInt(roomEast);
				
				roomWest = readFile.nextLine();
				west = Integer.parseInt(roomWest);
				
				booleanVisited = readFile.nextLine();
				boolean visited = Boolean.parseBoolean(booleanVisited);
				
				itemInventory inv = new itemInventory();
				PuzzleController puz = new PuzzleController(); 
				MonsterList mon = new MonsterList();
				rooms.add(new Room(roomName, roomDescription, inv, ID, north, south, east, west, visited, puz, mon ));
				
			}//end of while loop
			
			
			
		}// end of try
		catch (IOException ex)
		{
			System.out.println("Problem reading file occured");
			ex.printStackTrace();
		}// end of catch
		finally{readFile.close();}
		
	}

}
