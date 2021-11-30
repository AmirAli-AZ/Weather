import java.awt.Image;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import Models.Root;
import Models.Weather;

public class WeatherService implements RequestNetWork.requestNetWork{

	private String API_KEY = "";
	private String LOCATION = "";
	private String URL = "";
	private Root root = null;
	
	public WeatherService(String api_key, String location) {
		this.API_KEY = api_key;
		this.LOCATION = location;
		this.URL = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + " &appid=" + API_KEY
				+ "&units=imperial";
		RequestNetWork rq = new RequestNetWork(URL , this);
		rq.read();
	}
	
	public double getTemp() {
		return ((root != null) ? root.getMain().getTemp() : 0);
	}

	public double feelsLike() {
		return ((root != null) ? root.getMain().getFeels_like() : 0);
	}

	public double getSpeed() {
		return ((root != null) ? root.getWind().getSpeed() : 0);
	}

	public double getDeg() {
		return ((root != null) ? root.getWind().getDeg() : 0);
	}

	public int getVisibility() {
		return ((root != null) ? root.getVisibility() : 0);
	}

	public int getHumidity() {
		return ((root != null) ? root.getMain().getHumidity() : 0);
	}

	public int getPressure() {
		return ((root != null) ? root.getMain().getPressure() : 0);
	}

	public Icon getIcon(Class<?> c) {
		if (root != null) {
			List<Weather> list = root.getWeather();
			Image img = null;
			for (Weather w : list) {
				img = new ImageIcon(c.getResource(w.getIcon().trim() + ".png")).getImage();
			}
			return new ImageIcon(img);
		} else {
			Image img2 = new ImageIcon(c.getResource("01d.png")).getImage();
			return new ImageIcon(img2);
		}
	}
	
	@Override
	public void onResponse(String response) {
		try {
			ObjectMapper ob = new ObjectMapper();
			ob.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			root = ob.readValue(response, Root.class);
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
	}

	@Override
	public void onResponseError(String exception, String message) {
		System.err.println(exception);
	}

	@Override
	public void isReading(boolean isReading) {
		// TODO Auto-generated method stub
	}

}
