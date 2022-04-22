
public class Door {

	//attributes
	private String doorDescription;
	private int startRoomID;
	private int endRoomID;
	
	
	//constructor
	public Door(String doorDescription, int startRoomID, int endRoomID) 
	{
		super();
		this.doorDescription = doorDescription;
		this.startRoomID = startRoomID;
		this.endRoomID = endRoomID;
	}


	//getters and setters
	public String getDoorDescription() {
		return doorDescription;
	}


	public void setDoorDescription(String doorDescription) {
		this.doorDescription = doorDescription;
	}



	public int getStartRoom() {
		return startRoomID;
	}



	public void setStartRoom(int startRoom) {
		this.startRoomID = startRoom;
	}


	public int getEndRoom() {
		return endRoomID;
	}


	public void setEndRoom(int endRoom) {
		this.endRoomID = endRoom;
	}
	

}
