import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class RequestNetWork {

	public interface requestNetWork {
		void onResponse(String response);
		void onResponseError(String exception, String message);
		void isReading(boolean isReading);
	}

	private requestNetWork listener;
	private String url;

	public RequestNetWork(String url, requestNetWork listener) {
		this.listener = listener;
		this.url = url;
	}

	public void read() {
		try {
			InputStream is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String json = readAll(rd);
			listener.onResponse(json);
			is.close();
		} catch (Exception e) {
			listener.onResponseError(e.toString(), e.getMessage());
		}

	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
			listener.isReading(true);
		}
		listener.isReading(false);
		return sb.toString();
	}
}