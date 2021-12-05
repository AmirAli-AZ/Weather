import java.io.Serializable;

public class WeatherLocation implements Serializable {
	
	private String LOCATION = "Tehran , IR";
	
	public WeatherLocation(String l) {
		this.LOCATION = l;
	}
	public String getLocation() {
		return LOCATION;
	}
}
