// P E M B E R T O N
public class Thing {

	private String thingName;
	private String thingDescription;

	public Thing(String thingName, String thingDescription) {
		super();
		this.thingName = thingName;
		this.thingDescription = thingDescription;
	}

	public String getThingName() {
		return thingName;
	}

	public void setThingName(String thingName) {
		this.thingName = thingName;
	}

	public String getThingDescription() {
		return thingDescription;
	}

	public void setThingDescription(String thingDescription) {
		this.thingDescription = thingDescription;
	}

	@Override
	public String toString() {
		return "Name=" + thingName;
	}

}
