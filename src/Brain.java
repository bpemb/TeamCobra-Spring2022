import java.util.ArrayList;
import java.util.Scanner;

public class Brain {
	
	
	
    //----Saif Shaikh-----
	itemInventory masterItems = new itemInventory();
	itemInventory playerItems = new itemInventory();
    PuzzleList roomPuzzles = new PuzzleList();

    ArrayList<item> armor = new ArrayList<item>();
	ArrayList<item> equipped = new ArrayList<item>();
	
    private String itemName;
    private String itemDescription;
    private int roomID;
    private Room location;   
    private String room;
    private Scanner readFile = null;
    private String textFile = "items" + ".txt";




    //----Saif Shaikh-----
    //Reads items.txt and add everything to master items
    public void setItems(){
        textFile = "item.txt";
        try {

            itemName = readFile.nextLine();
            itemDescription = readFile.nextLine();
            room = readFile.nextLine();
            roomID = Integer.parseInt(room);
            String dmg = readFile.nextLine();
            int itemDmg = Integer.parseInt(dmg);

            String health = readFile.nextLine();
            int itemHealth = Integer.parseInt(health);
            String monLocation = readFile.nextLine();
            String armor = readFile.nextLine();
            boolean isArmor = Boolean.parseBoolean(armor);

            masterItems.add(new item(itemName, itemDescription, roomID, Room.class.cast(location), itemDmg, itemHealth, monLocation, isArmor));

        }catch (Exception e){
        	
        	//display message if anything wrong happens when reading items.txt
        	
            System.out.println("Something went wrong reading items txt file!");
            System.exit(0);
            
        }

    }

    //A.M - Puzzle Reader. Reads puzzle from Puzzle.txt
    public void setPuzzle() {
        File puzzleFile = new File("puzzles.txt");
        Scanner puzzleReader = null;

        try {
            puzzleReader = new Scanner(puzzleFile) {
            } catch (FileNotFoundException e) {
                System.out.println("Puzzle file not found.");
            }
        }

        while(puzzleReader != null && puzzleReader.hasNext()) {
            itemName = puzzleReader.nextLine();
            itemDescription = puzzleReader.nextLine();
            String puzzleAnswer = puzzleReader.nextLine();
            String puzzleHint = puzzleReader.nextLine();
            String puzzleRoom = puzzleReader.nextLine();
            int roomID = Integer.parseInt(puzzleRoom);

            roomPuzzles.add(new Puzzle(itemName, itemDescription, puzzleAnswer, puzzleHint, roomID, Room.class.cast(location)));
        }
    }

}
