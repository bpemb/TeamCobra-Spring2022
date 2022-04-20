
public class item extends Thing{
	
	//----Saif Shaikh made this class----
	
	//attributes 
	
	private String itemName;
	private String itemDescription;
	private int roomID;
	private Room location;
	private int itemDmg;
	private int itemHealth;
	private String monLocation;
	private boolean isArmor;
	
    

	//constructor
    public item(String itemName, String itemDescription, int roomID, Room location, int itemDmg, int itemHealth,String monLocation, boolean isArmor) {
		
    	super(itemName, itemDescription);
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.roomID = roomID;
		this.location = location;
		this.itemDmg = itemDmg;
		this.itemHealth = itemHealth;
		this.monLocation = monLocation;
		this.isArmor = isArmor;
	}


    //getters and setters
    
	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getItemDescription() {
		return itemDescription;
	}



	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}



	public int getRoomID() {
		return roomID;
	}



	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}



	public Room getLocation() {
		return location;
	}



	public void setLocation(Room location) {
		this.location = location;
	}



	public int getItemDmg() {
		return itemDmg;
	}



	public void setItemDmg(int itemDmg) {
		this.itemDmg = itemDmg;
	}



	public int getItemHealth() {
		return itemHealth;
	}



	public void setItemHealth(int itemHealth) {
		this.itemHealth = itemHealth;
	}



	public String getMonLocation() {
		return monLocation;
	}



	public void setMonLocation(String monLocation) {
		this.monLocation = monLocation;
	}



	public boolean isArmor() {
		return isArmor;
	}



	public void setArmor(boolean isArmor) {
		this.isArmor = isArmor;
	}

	//toString
	@Override
	public String toString() {
		return "itemName: " + itemName;
		
		
	}
    
  

    

}
