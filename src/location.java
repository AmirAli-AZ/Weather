import java.io.Serializable;

public class location implements Serializable {
	
	private String LOCATION = "Tehran , IR";
	
	public location(String l) {
		this.LOCATION = l;
	}
	public String getLocation() {
		return LOCATION;
	}
}
