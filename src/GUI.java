import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import graphics.panelGraphics;
import javax.swing.JLabel;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLabel wind;
	private JLabel feels_like;
	private JLabel icon1;
	private JLabel temp;
	private JLabel visibility;
	private JLabel humidity;
	private JLabel pressure;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private final String userPath = System.getProperty("user.home");
	private static boolean showDialog = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					
					if(showDialog) {
						noConnection dialog = new noConnection(frame);
						if (dialog.doModal() == noConnection.CANCEL) {
							showDialog = false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("Weater");
		setMinimumSize(new Dimension(596, 336));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 336);

		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("More");
		menuBar.add(mnNewMenu);

		Image img2 = new ImageIcon(this.getClass().getResource("ic_settings.png")).getImage();
		mntmNewMenuItem = new JMenuItem("Settings");
		mntmNewMenuItem.setBackground(Color.WHITE);
		mntmNewMenuItem.setIcon(new ImageIcon(img2));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close the JFrame
				dispose();

				settings s = new settings();
				s.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		Image img3 = new ImageIcon(this.getClass().getResource("ic_refresh2.png")).getImage();
		mntmNewMenuItem_1 = new JMenuItem("Refresh");
		mntmNewMenuItem_1.setBackground(Color.WHITE);
		mntmNewMenuItem_1.setIcon(new ImageIcon(img3));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isConnected()) {
					try {
						showNotification("Refreshing", "datas is refreshing...", MessageType.INFO);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
					refresh();
				} else {
					defaultValue();
					noConnection dialog = new noConnection(GUI.this);
					if (dialog.doModal() == noConnection.CANCEL) {}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		temp = new JLabel("Fahrenheit");
		temp.setFont(new Font("Tahoma", Font.BOLD, 22));

		icon1 = new JLabel("");

		feels_like = new JLabel("Feels like");
		feels_like.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JPanel panel = new panelGraphics(true);
		panel.setForeground(Color.LIGHT_GRAY);

		wind = new JLabel("Wind:");
		wind.setFont(new Font("Tahoma", Font.PLAIN, 14));

		visibility = new JLabel("Visibility :");
		visibility.setFont(new Font("Tahoma", Font.PLAIN, 14));

		humidity = new JLabel("Humidity:");
		humidity.setFont(new Font("Tahoma", Font.PLAIN, 14));

		pressure = new JLabel("Pressure:");
		pressure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(5).addComponent(panel,
								GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(15).addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(temp, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE).addGap(10))
								.addGroup(
										gl_contentPane.createSequentialGroup()
												.addComponent(feels_like, GroupLayout.PREFERRED_SIZE, 0,
														Short.MAX_VALUE)
												.addGap(239)))
								.addGap(25)
								.addComponent(icon1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGap(5)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(44).addComponent(temp).addGap(11)
								.addComponent(feels_like, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(26).addComponent(icon1,
								GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
				.addGap(18).addComponent(panel, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE).addGap(6)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(10)
						.addComponent(wind, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(7)
						.addComponent(visibility, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(10)
						.addComponent(humidity, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(3)
						.addComponent(pressure, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE).addGap(10)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(51)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(wind, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(humidity, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(pressure, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(visibility, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
				.addGap(50)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

		if(isConnected()) {
			refresh();
		}else {
			defaultValue();
			showDialog = true;
		}
		
	}

	private void refresh() {
		File file = new File(userPath + File.separator + "Weather");
		String path = userPath + File.separator + "Weather" + File.separator + "data.ser";
		File file2 = new File(path);
		location l = new location("Tehran , IR");
		location l2 = null;

		if (!file.exists()) {
			file.mkdirs();
		}
		if (!file2.exists()) {
			try {
				locationManager.write(path, l);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			l2 = locationManager.read(path);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		WeatherService service = new WeatherService("cfcdd2d8f10c170bb627e8812f1a6fe3", l2.getLocation());
		temp.setText("Fahrenheit " + service.getTemp());
		feels_like.setText("Feels like " + service.feelsLike());
		wind.setText("Wind: " + service.getSpeed() + "/" + service.getDeg());
		visibility.setText("Visibility: " + (service.getVisibility() / 1000) + "KM");
		humidity.setText("Humidity: " + service.getHumidity() + "%");
		pressure.setText("Pressure: " + service.getPressure());
		icon1.setIcon(service.getIcon(GUI.class));

	}

	private boolean isConnected() {
		try {
			URL url = new URL("https://openweathermap.org");
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

	}

	// tray notification
	private void showNotification(String title, String message, MessageType type) throws AWTException {
		if (SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage(GUI.class.getResource("ic_warning.png"));
			TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("Weather");
			tray.add(trayIcon);
			trayIcon.displayMessage(title, message, type);
		} else {
			System.err.println("System tray not supported!");
		}
	}

	private void defaultValue() {
		temp.setText("Fahrenheit " + 0);
		feels_like.setText("Feels like " + 0);
		wind.setText("Wind: " + 0 + "/" + 0);
		visibility.setText("Visibility: " + 0 + "KM");
		humidity.setText("Humidity: " + 0 + "%");
		pressure.setText("Pressure: " + 0);
		Image icon = new ImageIcon(this.getClass().getResource("01d.png")).getImage();
		icon1.setIcon(new ImageIcon(icon));
	}
}
