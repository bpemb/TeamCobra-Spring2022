
public class Player extends itemPlacer {

	private int playerHealth;
	private Room playerPosition;
	private int playerDmg;
	
	
	public Player(String thingName, String thingDescription, itemInventory items, int playerHealth, Room playerPosition, int playerDmg) {
		super(thingName, thingDescription, items);
		this.playerHealth = playerHealth;
		this.playerPosition = playerPosition;
		this.playerDmg = playerDmg;
	}


	public int getPlayerHealth() {
		return playerHealth;
	}


	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}


	public Room getPlayerPosition() {
		return playerPosition;
	}


	public void setPlayerPosition(Room playerPosition) {
		this.playerPosition = playerPosition;
	}


	public int getPlayerDmg() {
		return playerDmg;
	}


	public void setPlayerDmg(int playerDmg) {
		this.playerDmg = playerDmg;
	}
	
	
}
