import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Brain {



    //----Saif Shaikh-----
	itemInventory masterItems = new itemInventory();
	itemInventory playerItems = new itemInventory();

	//A.M
    PuzzleList roomPuzzles = new PuzzleList();

	//pemberton
    Map GameMap = new Map();
    DoorDescription GameDoors = new DoorDescription();

	//pemberton
	BufferedReader playerInput = new BufferedReader(new InputStreamReader(System.in));
	Player warrior = new Player("", "", playerItems, 25, GameMap.rooms.get(0), 10);

	//pemberton
	static List<String> commands = new ArrayList<>(Arrays.asList("n", "s", "e", "w", "take", "drop",
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
		s = warrior.getInventory().openInventory();
		System.out.println(s);

	}


	//----Saif Shaikh-----
	//this method tracks user input
	private String ParseVerb(List<String> wordlist) throws IOException
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
					MovePlayer(warrior.getPlayerPosition().getN());
					break;
				case "s":
					MovePlayer(warrior.getPlayerPosition().getS());
					break;
				case "e":
					MovePlayer(warrior.getPlayerPosition().getE());
					break;
				case "w":
					MovePlayer(warrior.getPlayerPosition().getW());
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
            puzzleReader = new Scanner(puzzleFile);
            } catch (FileNotFoundException e) {
                System.out.println("Puzzle file not found.");
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

    //A.M - Set puzzle locations according to its assigned room.
	public void setPuzzleRooms()
	{
		for (Room x: GameMap.rooms )
		{
			for (Puzzle i: roomPuzzles)
			{
				if(x.getRoomID() == i.getRoomID())
				{
					x.getPuz().add(i);

				}
			}
		}
	}

	//A.M - Control Puzzle. Shows and handle a puzzle interface when there is a puzzle availalble within a room.
	public void PuzzleController(int ID) throws IOException
	{
		Puzzle completed = new Puzzle(null, null, null, null, roomID, location);
		
		String answer = "";
		System.out.println("PUZZLING PUZZLE BY THE MASTER PUZZLER");

		System.out.println(GameMap.rooms.get(ID).getPuz().get(0).getThingDescription());
		int a =  GameMap.rooms.get(ID).getPuz().get(0).getAttempts();

		for (int i = 0; i <= GameMap.rooms.get(ID).getPuz().get(0).getAttempts() ; i++) {
			System.out.print("> ");
			answer = playerInput.readLine();

			if(answer.equalsIgnoreCase(GameMap.rooms.get(ID).getPuz().get(0).getAnswer()))
			{
				System.out.println("Correct!");
				i = GameMap.rooms.get(ID).getPuz().get(0).getAttempts();
				completed = GameMap.rooms.get(ID).getPuz().get(0);

			}
			if (answer.equalsIgnoreCase("hint"))
			{
				System.out.println(GameMap.rooms.get(ID).getPuz().get(0).getHint());
			}
			else if(answer.equalsIgnoreCase("stop"))
			{
				System.out.println("Scrapping puzzle, you may continue the game...");
				i = GameMap.rooms.get(ID).getPuz().get(0).getAttempts();
			}

			else if(!answer.equalsIgnoreCase(GameMap.rooms.get(ID).getPuz().get(0).getAnswer()))
			{
				System.out.println("Answer incorrent, try again... .");
				System.out.println("You have "+ a + " tries left.");
				a--;
			}
		}
		if(answer.equalsIgnoreCase(GameMap.rooms.get(ID).getPuz().get(0).getAnswer()))
		{
			GameMap.rooms.get(ID).getPuz().remove(completed);
		}
		System.out.println("You are back in " + GameMap.rooms.get(ID).getThingName());
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

			roomMonsters.add(new Monster(itemName, itemDescription, winMsg, loseMsg, roomID, monHp, monDmg, Room.class.cast(location)));
    	}
catch (Exception e){

        	//Error message for monster file
            System.out.println("Reading Monster File Error!");
            System.exit(0);

        }

    	//sets the monsters roomID by placing their possible locations into a list and picking a random location from the list to set the roomID

    	//Brushmaster Monster
    	for (Monster x: roomMonsters)
		{
    		if (x.getThingName().equalsIgnoreCase("Brushmaster Monster"))
			{
    		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
	        int result;
	        Random rand = new Random();
	        result = list.get(rand.nextInt(list.size()));
	        x.setRoomID(result);
			}

    	//Aspissorous Monster
    	if (x.getThingName().equalsIgnoreCase("Aspissorous"))
		{

		    List<Integer> list = new ArrayList<>(Arrays.asList(6, 7));
	        int result;
	        Random rand = new Random();
	        result = list.get(rand.nextInt(list.size()));
	        x.setRoomID(result);
		}

    	//Marauder Monster
    	if (x.getThingName().equalsIgnoreCase("Marauder"))
		{
		    List<Integer> list = new ArrayList<>(Arrays.asList(6, 17));
	        int result;
	        Random rand = new Random();
	        result = list.get(rand.nextInt(list.size()));
	        x.setRoomID(result);
		}

    	//Sword Monster
    	if (x.getThingName().equalsIgnoreCase("Sword Monster"))
		{
		    List<Integer> list = new ArrayList<>(Arrays.asList(7,8,10,15));
	        int result;
	        Random rand = new Random();
	        result = list.get(rand.nextInt(list.size()));
	        x.setRoomID(result);
		}



    	//Viciossor Monster
    	if (x.getThingName().equalsIgnoreCase("Viciossor"))
		{
		    List<Integer> list = new ArrayList<>(Arrays.asList(11, 13));
	        int result;
	        Random rand = new Random();
	        result = list.get(rand.nextInt(list.size()));
	        x.setRoomID(result);
		}

    	//Arroziossor Monster
    	if (x.getThingName().equalsIgnoreCase("Arroziossor"))
		{

		    List<Integer> list = new ArrayList<>(Arrays.asList(8, 11));
	        int result;
	        Random rand = new Random();
	        result = list.get(rand.nextInt(list.size()));
	        x.setRoomID(result);
		}

    	//Razoriossor monster
    	if (x.getThingName().equalsIgnoreCase("Razoriossor"))
		{
		    List<Integer> list = new ArrayList<>(Arrays.asList(10, 12));
	        int result;
	        Random rand = new Random();
	        result = list.get(rand.nextInt(list.size()));
	        x.setRoomID(result);
		}



		}
		}

    //Javier
    //sets the monster into their rooms if room and monster share the same roomID
    public void setMonsterRooms()
	{
		for (Room r: GameMap.rooms )
		{
			for (Monster m: roomMonsters)
			{
				if(r.getRoomID() == m.getRoomID())
				{

					r.getMon().add(m);


				}
			}
		}
	}
  //Javier
    public void setMonsterItems()
	{
		for (item i: masterItems)
		{
			for (Monster m: roomMonsters)
			{
				if (i.getMonLocation().equalsIgnoreCase(m.getThingName()))
				{
					i.setRoomID(m.getRoomID());
				}
			}
		}
	}

    //Javier
    public void MonsterController(int ID) throws IOException
    {
    	//Prints the monster in the room with their stats
    	System.out.println(" M O N S T E R ༼ง ◉_◉༽ง   ");
    	System.out.println(GameMap.rooms.get(ID).getMon().get(0).getThingName());
    	System.out.println("MONSTER STATS:");
    	System.out.println("Monster HP: " + GameMap.rooms.get(ID).getMon().get(0).getMonHp());
		System.out.println("Monster Damage: " + GameMap.rooms.get(ID).getMon().get(0).getMonDmg());
		System.out.println("_________________________________________________________");

		String answer = "";
		Monster deadMon = new Monster(itemName, itemDescription, itemName, itemName, roomID, roomID, roomID, Room.class.cast(location));

		while(GameMap.rooms.get(ID).getMon().get(0).getMonHp() > 0 )		{
			System.out.println("What will you do? \n Attack or Ignore?: ");
			System.out.print("> ");
			answer = playerInput.readLine();
			//Typing attack starts the battle
			if (answer.equalsIgnoreCase("Attack"))
			{
				System.out.println("Battle Start \n------------");
				while(warrior.getPlayerHealth() > 0 && GameMap.rooms.get(ID).getMon().get(0).getMonHp() > 0)
				{
					System.out.println("Monster Health: "+ GameMap.rooms.get(ID).getMon().get(0).getMonHp()  + "\nPlayer Health: " + warrior.getPlayerHealth());
					System.out.println("------------");
					System.out.println("------------");
					System.out.println("What will you do? \n 1.Attack \n 2. Block \n 3.Heal \n 4.Inventory \n 5.Examine \n 6. Help");
					System.out.print("> ");
					answer = playerInput.readLine();

					//Typing Attack again attacks the monster
					if (answer.equalsIgnoreCase("Attack"))
					{
						GameMap.rooms.get(ID).getMon().get(0).setMonHp(GameMap.rooms.get(ID).getMon().get(0).getMonHp() - warrior.getPlayerDmg());
						System.out.println("You attack with all your might, and the "+ GameMap.rooms.get(ID).getMon().get(0).getThingName() + " takes " +  warrior.getPlayerDmg() + " damage!");

						warrior.setPlayerHealth(warrior.getPlayerHealth() - GameMap.rooms.get(ID).getMon().get(0).getMonDmg());
						System.out.println("The "+ GameMap.rooms.get(ID).getMon().get(0).getThingName() + " attacks, and you take " + GameMap.rooms.get(ID).getMon().get(0).getMonDmg() + " damage!");

					}
					//blocks attack, just prints out a string, no values are changed
					if (answer.equalsIgnoreCase("Block"))
					{
						System.out.println("You block the Attack!");
					}

					//Heals the player + a free block
					if (answer.equalsIgnoreCase("Heal"))
					{
						playerHeal();
						System.out.println("The "+ GameMap.rooms.get(ID).getMon().get(0).getThingName() + " attacks, but " + warrior.getThingName() + " blocks it!");
					}
					//checks the current inventory
					if (answer.equalsIgnoreCase("Inventory"))
					{
						String command;

						playerInventory();

						System.out.println("Equip or Unequip an item?");
						System.out.print("> ");
						command = playerInput.readLine();
						List<String> wl;
						String lowstr = command.trim().toLowerCase();
						wl = wordList(lowstr);
						 parseCommand(wl);

					}
					//examines the current monster
					if (answer.equalsIgnoreCase("Examine"))
					{
						System.out.println(GameMap.rooms.get(ID).getMon().get(0).getThingDescription()+ "\n----");
						System.out.println(GameMap.rooms.get(ID).getMon().get(0).getThingName() + "'s Damage: " + GameMap.rooms.get(ID).getMon().get(0).getMonDmg()+ "\n");
					}

					//displays help message and commands
					if (answer.equalsIgnoreCase("Help"))
					{
						playerHelp();
					}
				}

					//players health reaches 0 and dies
					if (warrior.getPlayerHealth() <= 0)
					{
						System.out.println(GameMap.rooms.get(ID).getMon().get(0).getThingName() +" has killed " + warrior.getThingName());
						System.out.println(GameMap.rooms.get(ID).getMon().get(0).getLoseMsg());
						System.out.println("What will you do?\n1.Quit \n2.Restart");
						System.out.print("> ");
						answer = playerInput.readLine();

						if (answer.equalsIgnoreCase("Quit"))
						{
							System.out.println("\nThanks for playing!");
							System.exit(0);
						}
						System.out.println("\n\n Restarting the Tower of Champions");
						if (answer.equalsIgnoreCase("Restart"))
						{
							TowerOfChampions.main(null);
						}
						else
						{
							System.out.println("Please enter Quit or Restart");
						}
					}



				}


			if (answer.equalsIgnoreCase("Ignore"))
			{

				GameMap.rooms.get(ID).getMon().get(0).setMonHp(0);
				deadMon = GameMap.rooms.get(ID).getMon().get(0);
			}
		}

		//Victory mechanic, ,monster is defeated and item is displayed + added to inventory
		if (GameMap.rooms.get(ID).getMon().get(0).getMonHp() <= 0 && warrior.getPlayerHealth() > 0)
		{
			System.out.println(warrior.getThingName() + " has Won!");
			System.out.println("\n"+GameMap.rooms.get(ID).getMon().get(0).getWinMsg() + "\n");

			for (item x: masterItems)
			{
				if (x.getMonLocation().equalsIgnoreCase(GameMap.rooms.get(ID).getMon().get(0).getThingName()))
				{

					TakeItem(x.getItemName());
				}
			}

		}

		GameMap.rooms.get(ID).getMon().remove(deadMon);
		System.out.println("----\nYou're still in the " + GameMap.rooms.get(ID).getThingName()+" room.");
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
		}// end of DirectionMessage+






}
