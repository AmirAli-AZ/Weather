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
	private Root root;

	public WeatherService(String api_key , String location) {
		this.API_KEY = api_key;
		this.LOCATION = location;
		this.URL = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + " &appid="
				+ API_KEY + "&units=imperial";
		
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
			e.printStackTrace();
		}
	}

	public double getTemp() {
		return root.getMain().getTemp();
	}

	public double feelsLike() {
		return root.getMain().getFeels_like();
	}

	public double getSpeed() {
		return root.getWind().getSpeed();
	}

	public double getDeg() {
		return root.getWind().getDeg();
	}

	public int getVisibility() {
		return root.getVisibility();
	}

	public int getHumidity() {
		return root.getMain().getHumidity();
	}

	public int getPressure() {
		return root.getMain().getPressure();
	}

	public Icon getIcon(Class c) {
		try {
			List<Weather> list = root.getWeather();
			Image img = null;
			for (Weather w : list) {
				img = new ImageIcon(c.getResource(w.getIcon().trim() + ".png")).getImage();
			}
			return new ImageIcon(img);
		} catch (Exception e) {
			Image img2 = new ImageIcon(c.getResource("01d.png")).getImage();
			return new ImageIcon(img2);
		}
	}
}
