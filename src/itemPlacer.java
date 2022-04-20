import java.util.ArrayList;

public class itemPlacer extends Thing{
	
	//----Saif Shaikh made this class----
	
	
	private itemInventory items = new itemInventory();

	
	//constructor
	public itemPlacer(String thingName, String thingDescription, itemInventory items) {
		super(thingName, thingDescription);
		this.items = items;
	}

	
	//getters and setters
	public itemInventory getInventory() {
		return items;
	}

	public void setInventory(itemInventory items) {
		this.items = items;
	}
	


}
