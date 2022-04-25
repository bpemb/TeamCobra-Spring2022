
//P E M B E R T O N

public class Room extends itemPlacer {
	private int roomID;
	private int n,s,e,w;
	private boolean visited;
	private PuzzleList puz;
	private MonsterList mon;
	
	
	public Room(String thingName, String thingDescription, itemInventory items, int roomID, int n, int s, int e, int w,
			boolean visited, PuzzleList puz, MonsterList mon) {
		super(thingName, thingDescription, items);
		this.roomID = roomID;
		this.n = n;
		this.s = s;
		this.e = e;
		this.w = w;
		this.visited = visited;
		this.puz = puz;
		this.mon = mon;
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



	public PuzzleList getPuz() {
		return puz;
	}



	public void setPuz(PuzzleList puz) {
		this.puz = puz;
	}



	public MonsterList getMon() {
		return mon;
	}



	public void setMon(MonsterList mon) {
		this.mon = mon;
	}
	
	

}
