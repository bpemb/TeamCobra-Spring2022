import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Brain {
	
	
	
    //----Saif Shaikh-----
	itemInventory masterItems = new itemInventory();
	itemInventory playerItems = new itemInventory();
	
	
    PuzzleList roomPuzzles = new PuzzleList(); //A.M

	//pemberton
    Map GameMap = new Map();
    DoorDescription GameDoors = new DoorDescription();
	Player wizard = new Player("Wizard", "Hero", playerItems, 25, GameMap.rooms.get(0), 10);

	//pemberton
	BufferedReader playerInput = new BufferedReader(new InputStreamReader(System.in));
	Player warrior = new Player("", "", playerItems, 25, GameMap.rooms.get(0), 10);

	//pemberton
	List<String> commands = new ArrayList<>(Arrays.asList("n", "s", "e", "w", "take", "drop",
			"inventory","explore","inspect","equip", "unequip", "consume", "attack", "ignore", "help", "save", "load"));
	
    
	MonsterList roomMonsters = new MonsterList(); //Javier Z
    ArrayList<item> armor = new ArrayList<item>();
	ArrayList<item> equipped = new ArrayList<item>();
	
    private String itemName;
    private String itemDescription;
    private int roomID;
    private Room location;   
    private String room;
    private Scanner readFile = null;
    private String textFile = "items" + ".txt";

   
    private int playerMaxHealth;//pemberton
    
    
    //pemberton
    public void Intro() throws IOException
	{
		System.out.println("You have been summoned to defeat the Tower of Warriors.\nWhat is your name?");
		System.out.print("> ");
		String name = playerInput.readLine();
		warrior.setThingName(name);
		System.out.println("Welcome in "+ warrior.getThingName()+"\n----");
		
		playerMaxHealth = warrior.getPlayerHealth();
		
		setPuzzle();
		setMonster();
		setItems();
		
		
		int max = 50;
		int min = 0;
		monsterDmgThreshold = (int)Math.floor(Math.random()*(max-min+1)+min);
		
		String msg;
		msg = warrior.getPlayerPosition().getThingDescription();
		System.out.println(msg);
		System.out.println(" ");
		DirectionMessage();
	}// end of Intro


    
    
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
    
    
	
    //----Saif Shaikh-----
    //this method sets the item to room
	public void setRoomInventory(){
		for (Room x: GameMap.rooms ){
			
			for (item i : masterItems){
				
				if(x.getRoomID() == i.getRoomID()){
					
					x.getInventory().add(i);
					
				}
			}
		}
	}
    
    
	//----Saif Shaikh-----
	//this method checks player inventory
	private void playerInventory(){
		
		if (equipped.size() > 0){
			System.out.println(equipped.get(0).getItemName() + ": Equipped");
			System.out.println("------------------------------------------");
		}
		
		String s = "";
		s = wizard.getInventory().openInventory();
		System.out.println(s);
		
	}
	
	
	//----Saif Shaikh-----
	//this method tracks user input
	private String ParseVerb(List<String> wordlist) throws Exception
{
		String verb;
		String msg = "";
		
		verb = wordlist.get(0);
		
		if(!commands.contains(verb)){
			
			msg = verb + " is not a known command, try again.";
			
		}
		else{
			
			switch (verb){
			
				case "n":
					MovePlayer(wizard.getPlayerPosition().getN());
					break;
				case "s":
					MovePlayer(wizard.getPlayerPosition().getS());
					break;
				case "e":
					MovePlayer(wizard.getPlayerPosition().getE());
					break;
				case "w":
					MovePlayer(wizard.getPlayerPosition().getW());
					break;
				case "explore":
					playerExplore();
					break;
				case "inventory":
					playerInventory();
					break;
				case "heal":
					playerHeal();
					break;
				case "help":
					playerHelp();
					break;
				default:
					msg = verb + " is not understood.";
					break;
			}
		}
		return msg;
		
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

    

    //Javier Z
    //Reads the Monster text files and makes a list of roomMonsters
    public void setMonster()
    {
    	textFile = "monsters" + ".txt";
    	
    	try
    	{
    		itemName = readFile.nextLine();
            itemDescription = readFile.nextLine();
            String winMsg = readFile.nextLine();
			String loseMsg = readFile.nextLine();
			String room = readFile.nextLine();
			int roomID = Integer.parseInt(room);
			String hp = readFile.nextLine();
			int monHp = Integer.parseInt(hp);
			String dmg = readFile.nextLine();
			int monDmg = Integer.parseInt(dmg);
			String thresh = readFile.nextLine();
			int monThreshold = Integer.parseInt(thresh);
			
			roomMonsters.add(new Monster(itemName, itemDescription, winMsg, loseMsg, roomID, monHp, monDmg, monThreshold, Room.class.cast(location)));
    	}
catch (Exception e){
        	
        	//Error message for monster file
            System.out.println("Reading Monster File Error!");
            System.exit(0);
            
        }
    }
    
    
    private void playerExplore()
	{
		String s = "";
		System.out.println(warrior.getPlayerPosition().getThingDescription());
		System.out.println("\nThis room has: ");

		s = warrior.getPlayerPosition().getInventory().openInventory();
		System.out.println(s);
	}
    
    
    private void playerHeal() 
	{
		if (warrior.getPlayerHealth() < playerMaxHealth)
		{
			if(equipped.get(0).getItemHealth() > 0 && equipped.size() > 0)
			{
				warrior.setPlayerHealth(warrior.getPlayerHealth() + equipped.get(0).getItemHealth());
				System.out.println("You have been healed!");
			}
			else 
			{
				System.out.println("This item can't heal you! Find/Equip another.");
			}
		}
	}
    
    private void playerHelp()
	{
		int count = 1;
		for ( String x: commands)
		{
			System.out.println(count+". " + x);
			count ++;
		}
	}
    
    
    private void MoveItem(String verb, String noun)
	{
		if(verb.equalsIgnoreCase("drop"))
		{
			DropItem(noun);
		}
		if(verb.equalsIgnoreCase("Heal"))
		{
			TakeItem(noun);
		}
		if(verb.equalsIgnoreCase("take"))
		{
			TakeItem(noun);
		}
		if(verb.equalsIgnoreCase("inspect"))
		{
			InspectItem(noun);
		}
		if (verb.equalsIgnoreCase("equip"))
		{
			EquipItem(noun);
		}
		if (verb.equalsIgnoreCase("unequip"))
		{
			UnequipItem(noun);
		}
	}
    
	//pemberton
	private void EquipItem(String item)
	{
		for (item x: warrior.getInventory())
		{
			if (x.getItemName().equalsIgnoreCase(item))
			{
				if(x.isArmor() == false)
				{
					if (equipped.size() == 0)
					{
						equipped.add(x);
						
						int i = x.getItemDmg();
						int p = warrior.getPlayerDmg();
						int newDmg = i + p;
						
						System.out.println(warrior.getThingName() + " has equipped the " + x.getItemName());
						warrior.setPlayerDmg(newDmg);
						System.out.println("Your damage has been increased to " + warrior.getPlayerDmg());

					}
					else
					{
						System.out.println("You already have " + equipped.get(0).getItemName() + " equipped. \n You must Un-Equip it to Equip "+ x.getItemName()+ ".");
					}
				}
				
				else if(x.isArmor() == true)
				{
					armor.add(x);
					
					int i = x.getItemHealth();
					int p = warrior.getPlayerHealth();
					int newHealth = i + p;
					
					System.out.println(warrior.getThingName() + " has equipped the " + x.getItemName());
					warrior.setPlayerDmg(newHealth);
					System.out.println("Your health has been inccreased to  " + warrior.getPlayerHealth());
				}
			}
		}
	}// End of EquipItem
	
	
	//pemberton
	private void UnequipItem(String item)
	{
		item unequipped = new item(null, null, roomID, location, roomID, roomID, null, false);
		for (item x: equipped)
		{
			if (x.getItemName().equalsIgnoreCase(item))
			{	
				unequipped = x;
				System.out.println(warrior.getThingName() + " has unequipped the " + x.getItemName());
				warrior.setPlayerDmg(warrior.getPlayerDmg() - x.getItemDmg());
				System.out.println("Your damage has been decreased to " + warrior.getPlayerDmg());
			}
		}
		equipped.remove(unequipped);
	}// End of UnequipItem
	
	
	//pemberton
	private void TakeItem(String item) 
	{
		item taken = new item(null, null, roomID, location, roomID, roomID, null, false);
		for (item x: warrior.getPlayerPosition().getInventory()) 
		{
			if (x.getItemName().equalsIgnoreCase(item))
			{
				taken = x;
				warrior.getInventory().add(x);
				System.out.println(x.getItemName() + " has been added to your Inventory!");
			}
		}
		warrior.getPlayerPosition().getInventory().remove(taken);
	}// End of TakeItem
	
	
	//pemberton
	private void DropItem(String item) 
	{
		item dropped = new item(null, null, roomID, location, roomID, roomID, null, false);
		for (item x: warrior.getInventory()) 
		{
			if (x.getItemName().equalsIgnoreCase(item))
			{
				dropped = x;
				warrior.getPlayerPosition().getInventory().add(x);
				
				System.out.println(warrior.getThingName() + " has dropped the " + x.getItemName());
			}
		}
		warrior.getInventory().remove(dropped);
	}// End of DropItem
	
	
	//pemberton
	private void InspectItem(String item) 
	{
		for (item x: warrior.getInventory())
		{
			if(x.getItemName().equalsIgnoreCase(item))
			{
				System.out.println(x.getItemDescription());
			}
		}
	}// End of InspectItem

	
	//pemberton
	public String ParseVerbNoun(List<String> wordlist)
	{
		String verb;
		String noun;
		String msg = "";
		
		verb = wordlist.get(0);
		noun = wordlist.get(1);
			
		if(!commands.contains(verb))
		{
			msg = verb + " is not a known verb!";
		}
		
		
		for(item x: masterItems)
		{
			if (x.getItemName().contains(noun))
			{
				MoveItem(verb,noun);
			}
		}
		return msg;

	}
	
	//pemberton
		public String parseCommand(List<String> wordlist) throws IOException
		{
			String msg;
			if (wordlist.size() == 1)
			{
				msg = ParseVerb(wordlist);
			}
			else if (wordlist.size() == 2)
			{
				msg = ParseVerbNoun(wordlist);
			}
			else
			{
				msg = "Only 2 word commands allowed!";
			}
			return msg;
		}
		
		
		//pemberton
		public static List<String> wordList(String input)
		{
			String delims = "[ \t,.:;?!\"']+";
			List<String> strlist = new ArrayList<>();
			String[] words = input.split(delims);
			
			for (String word : words)
			{
				strlist.add(word);
			}
			return strlist;
		}
		

		//pemberton
		private void MovePlayer(int pos) throws IOException
		{
			
			if (pos == -1)
			{
				System.out.println("Exit not found, try a different direction");
			}
			else
			{
				for (Door x: GameDoors.doors)
				{
					if (x.getStartRoom() == warrior.getPlayerPosition().getRoomID() && x.getEndRoom() == pos)
					{
						System.out.println("\n"+x.getDoorDescription());
					}
				}

				//set current room to true
				warrior.getPlayerPosition().setVisited(true);
				
				//update player room
				warrior.setPlayerPosition(GameMap.rooms.get(pos));
				
				//print visited boolean
				warrior.getPlayerPosition().isVisited();
				System.out.println(" ");

				//print room description
				System.out.println(warrior.getPlayerPosition().getThingDescription());
				System.out.println(" ");
				
				if(warrior.getPlayerPosition().getPuz().size() >= 1) 
				{
					PuzzleController(pos);
				}
				
				if(warrior.getPlayerPosition().getMon().size() >= 1) 
				{
					MonsterController(pos);
				}
				DirectionMessage();
				
			}
			
		}// end of MovePlayer
		
		
		
		//pemberton
		public  String RunCommand(String inputstr) throws IOException
		{
			List<String> wl;
			String s = "~~``Ending Game``~~";
			String lowstr = inputstr.trim().toLowerCase();
			
			if (!lowstr.equals("stop")) 
			{
				if (lowstr.equals("")) 
				{
					s = "You must enter a command";
				}
				else 
				{
					wl = wordList(lowstr);
					s = parseCommand(wl);
				}
			}
			return s;
		}// End of RunCommand
		
		
		//pemberton
		public void DirectionMessage()
		{
			String msg ="Where would you like to go?: \n - N(orth)\n - S(outh)\n - E(east)\n - W(est)\nPlease type a Letter. ";
			msg += "\nOr type Help.";
			System.out.println(msg);
		}// end of DirectionMessage
		
}
