import java.io.File;

public class test {

	public static void main(String[] args) {
		String user = System.getProperty("user.home");
		File file = new File(user + File.separator + "Weather");
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.print(file.getPath());
	}
}
