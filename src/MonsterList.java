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
	public Monster thisItem (String monName)
	{
		Monster aMon = null;
		String mon = "";
		String monLowCase = monName.trim().toLowerCase();
		
		for (Monster x: this)
		{
			mon = x.getThingName().trim().toLowerCase();
			if (mon.equals(monLowCase))
			{
				aMon = x;
			}
		}
		return aMon;
	}
	

}
