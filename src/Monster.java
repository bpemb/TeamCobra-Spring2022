//Class made by Javier Z
public class Monster extends Thing
{

	//attributes for monster
	private String winMsg;
	private String loseMsg;
	private int roomID;
	private int monHp;
	private int monDmg;
	private int monThreshold;
	private Room location;

	//Constructor for our monster

	public Monster(String thingName, String thingDescription, String winMsg, String loseMsg, int roomID, int monHp, int monDmg, int monThreshold,
			Room location) {
		super(thingName, thingDescription);
		this.roomID = roomID;
		this.monHp = monHp;
		this.monDmg = monDmg;
		this.monThreshold = monThreshold;
		this.location = location;
		this.winMsg = winMsg;
		this.loseMsg = loseMsg;
	}

	public String getWinMsg() {
		return winMsg;
	}

	public void setWinMsg(String winMsg) {
		this.winMsg = winMsg;
	}

	public String getLoseMsg() {
		return loseMsg;
	}

	public void setLoseMsg(String loseMsg) {
		this.loseMsg = loseMsg;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getMonHp() {
		return monHp;
	}

	public void setMonHp(int monHp) {
		this.monHp = monHp;
	}

	public int getMonDmg() {
		return monDmg;
	}

	public void setMonDmg(int monDmg) {
		this.monDmg = monDmg;
	}

	public int getMonThreshold() {
		return monThreshold;
	}

	public void setMonThreshold(int monThreshold) {
		this.monThreshold = monThreshold;
	}

	public Room getLocation() {
		return location;
	}

	public void setLocation(Room location) {
		this.location = location;
	}


	
}
