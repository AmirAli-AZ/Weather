import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.fasterxml.jackson.databind.ObjectMapper;
import Models.Root;
import Models.Weather;

public class WeatherService {

	private String API_KEY = "";
	private String LOCATION = "";
	private String URL = "";
	private String jsonCode;
	private Root root = null;

	public WeatherService(String api_key, String location) {
		this.API_KEY = api_key;
		this.LOCATION = location;
		this.URL = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + " &appid=" + API_KEY
				+ "&units=imperial";

		try {
			StringBuilder builder = new StringBuilder();
			URL url = new URL(URL);
			URLConnection connection = url.openConnection();
			BufferedReader bd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = bd.readLine()) != null) {
				builder.append(line);
			}
			bd.close();
			jsonCode = builder.toString();
			ObjectMapper ob = new ObjectMapper();
			root = ob.readValue(jsonCode, Root.class);
		} catch (Exception e) {
			root = null;
			e.printStackTrace();
		}
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
}
