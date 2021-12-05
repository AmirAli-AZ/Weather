import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WeatherLocationManager {
	private static WeatherLocation l = null;

	public static void write(String path, WeatherLocation l) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(path);
		ObjectOutputStream ob = new ObjectOutputStream(fileOut);
		ob.writeObject(l);
		ob.close();
		fileOut.close();
	}

	public static WeatherLocation read(String path) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream ob = new ObjectInputStream(fileIn);
		l = (WeatherLocation) ob.readObject();
		ob.close();
		fileIn.close();
		return l;
	}
}