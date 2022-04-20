import java.util.ArrayList;
import java.util.Scanner;

public class Brain {
	
	
	
    //----Saif Shaikh-----
	itemInventory masterItems = new itemInventory();
	itemInventory playerItems = new itemInventory();
	
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

}
