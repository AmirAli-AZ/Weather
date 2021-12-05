import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;


public class NoConnection extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static int OK = 1;
	public static int CANCEL = 0;
	

	/**
	 * Create the dialog.
	 */
	public NoConnection(Frame owner) {
		super(owner);
		setTitle("No Conncetion");
		setResizable(false);
		setBounds(100, 100, 465, 305);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("ic_warning.png")).getImage();
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(img));
		icon.setBounds(10, 11, 32, 32);
		contentPanel.add(icon);
		
		JLabel lblNewLabel = new JLabel("you don't have internet connection , check your internet connection");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(52, 11, 387, 32);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	public int doModal() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setVisible(true);
		
		return CANCEL;
	}
}
