
public class Room extends Thing {
	private int roomID;
	private int n,s,e,w;
	private boolean visited;
	
	public Room(String thingName, String thingDescription, int roomID, int n, int s, int e,
			int w, boolean visited) 
	{
		super(thingName, thingDescription);
		this.roomID = roomID;
		this.n = n;
		this.s = s;
		this.e = e;
		this.w = w;
		this.visited = visited;
	}
	
	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public boolean isVisited() {
		
		if(visited == true)
		{
			System.out.println("This room looks familiar");
			return true;
		}
		else 
		{
		//	System.out.println("You haven't seen this room before");
			return false;
		}
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
