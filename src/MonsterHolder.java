//Class by Javier Z
public class MonsterHolder 
{
	private MonsterList monsters = new MonsterList();

	public MonsterHolder(MonsterList monsters) {
		
		this.monsters = monsters;
	}

	public MonsterList getMonsters() {
		return monsters;
	}

	public void setMonsters(MonsterList monsters) {
		this.monsters = monsters;
	}

}
