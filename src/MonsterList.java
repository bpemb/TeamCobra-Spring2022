import java.util.ArrayList;

//Class by Javier Z
public class MonsterList extends ArrayList<Monster> {
	
	//Displays monsters, if no monster available displays attribute noMonster
	public String viewMonsters()
	{
		String noMonster= "";
		
		if(this.size() ==0) {
			noMonster = "There are no monsters";
		}
		else 
		{
			for (Monster x: this)
			{
				noMonster  += x.getThingName() + ": " + x.getThingDescription() + "\n";
			}
		}
		return noMonster ;
	}
	

}
