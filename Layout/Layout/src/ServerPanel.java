import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class ServerPanel extends JPanel {
	public ServerPanel() {
		setupPanel();
	}
	
	public void setupPanel()
	{
		setLayout(null);
		
		JLabel lblLogs = new JLabel("Logs");
		lblLogs.setBounds(39, 39, 46, 14);
		add(lblLogs);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(39, 63, 377, 188);
		add(textPane);
	}
	
}
