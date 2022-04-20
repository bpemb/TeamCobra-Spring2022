import java.util.ArrayList;

public class itemInventory extends ArrayList<item>{
	
	//----Saif Shaikh made this class----

	//checks inventory
	public String openInventory(){
	
		String noInventory = "";
		
		if(this.size() == 0) {
			
			noInventory = "Inventory is Empty";
			
		}
		else {
			
			for (item x: this){
				noInventory += x.getItemName() + ": " + x.getItemDescription() + "\n";
			}
			
		}
		return noInventory;
	}
	
	

}
