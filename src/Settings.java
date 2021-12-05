import java.awt.AWTException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Settings extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField city_name;
	private JLabel lblNewLabel_1;
	private JTextField state_code;
	private final String userPath = System.getProperty("user.home");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		setMinimumSize(new Dimension(596, 336));
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 336);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Image img = new ImageIcon(this.getClass().getResource("Arrows-Back-icon.png")).getImage();
		JLabel back = new JLabel("");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// close the JFrame
				dispose();
				
				GUI g = new GUI();
				g.setVisible(true);
			}
		});
		back.setIcon(new ImageIcon(img));
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cityName = city_name.getText().trim();
				String stateCode = state_code.getText().trim();
				if(cityName.length() == 0 && stateCode.length() == 0) {
					try {
						showNotification("Weather" , "enter city name and sate code" , MessageType.WARNING);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				}else {
					String location = cityName + "," + stateCode;
					WeatherLocation l = new WeatherLocation(location);
					String path = userPath + File.separator + "Weather" + File.separator + "data.ser";
					try {
						WeatherLocationManager.write(path , l);
						try {
							showNotification("Location" , "Location saved" , MessageType.INFO);
						} catch (AWTException e2) {
							e2.printStackTrace();
						}
						
					}catch(IOException i) {
						i.printStackTrace();
					}
				}
			}
		});
		
		lblNewLabel = new JLabel("City name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		city_name = new JTextField();
		addPopupMenu(city_name);
		city_name.setColumns(10);
		
		lblNewLabel_1 = new JLabel("State code");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		state_code = new JTextField();
		addPopupMenu(state_code);
		
		state_code.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(back, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(545, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(185)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(city_name, Alignment.LEADING)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(state_code, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
					.addGap(193))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(232)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(235))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(back, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(city_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(state_code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public static void showNotification(String title, String message, MessageType type) throws AWTException {
		if (SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage(Settings.class.getResource("ic_warning.png"));
			TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("Weather");
			tray.add(trayIcon);
			trayIcon.displayMessage(title, message, type);
		} else {
			System.err.println("System tray not supported!");
		}
	}
	private static void addPopupMenu(JTextField text) {
		JPopupMenu menu = new JPopupMenu();
		
		
		Action cut = new DefaultEditorKit.CutAction();
		cut.putValue(Action.NAME, "cut");
		cut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
		menu.add(cut);
		
		Action copy = new DefaultEditorKit.CopyAction();
		copy.putValue(Action.NAME, "copy");
		copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
		menu.add(copy);
		
		Action paste = new DefaultEditorKit.PasteAction();
		paste.putValue(Action.NAME, "paste");
		paste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
		menu.add(paste);
		
		Action selectAll = new SelectAll();
		menu.add(selectAll);
		
		text.setComponentPopupMenu(menu);
	}
	public static class SelectAll extends TextAction {
		
		public SelectAll() {
			super("Select All");
			putValue(Action.ACCELERATOR_KEY , KeyStroke.getKeyStroke("control S"));
		}
		public void actionPerformed(ActionEvent e) {
			JTextComponent componenet = getFocusedComponent();
			componenet.selectAll();
			componenet.requestFocusInWindow();
		}
	}
}
