import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class locationManager {
	private static location l = null;

	public static void write(String path, location l) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(path);
		ObjectOutputStream ob = new ObjectOutputStream(fileOut);
		ob.writeObject(l);
		ob.close();
		fileOut.close();
	}

	public static location read(String path) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream ob = new ObjectInputStream(fileIn);
		l = (location) ob.readObject();
		ob.close();
		fileIn.close();
		return l;
	}
}