import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class ServerPanel extends JPanel {
	JTextPane textPane = new JTextPane();
	
	public ServerPanel() {
		setupPanel();
	}
	
	public void setupPanel()
	{
		setLayout(null);
		setSize(700,700);
		
		JLabel lblLogs = new JLabel("Logs");
		lblLogs.setBounds(39, 39, 46, 14);
		add(lblLogs);
		
		textPane.setBounds(39, 63, 377, 188);
		add(textPane);
	}
	
	public void addLog(String text){
		textPane.setText(text);
	}
	
}
