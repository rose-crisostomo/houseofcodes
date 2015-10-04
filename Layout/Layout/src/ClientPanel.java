import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Button;

public class ClientPanel extends JPanel {
	
	private SpringLayout currentLayout;
	
	public ClientPanel() {
		setupPanel();
	}
	
	private void setupPanel()
	{
		this.setLayout(currentLayout);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setLayout(null);
		setLayout(null);
		setLayout(null);
		
		JLabel lblLangguage = new JLabel("Language");
		lblLangguage.setBounds(46, 41, 62, 24);
		add(lblLangguage);
		
		JPanel panel = new JPanel();
		panel.setBounds(46, 76, 103, 124);
		add(panel);
		panel.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("C");
		chckbxNewCheckBox.setBounds(0, 7, 48, 23);
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxC = new JCheckBox("C++");
		chckbxC.setBounds(0, 33, 48, 23);
		panel.add(chckbxC);
		
		JCheckBox chckbxC_1 = new JCheckBox("C#");
		chckbxC_1.setBounds(0, 59, 48, 23);
		panel.add(chckbxC_1);
		
		JCheckBox chckbxJava = new JCheckBox("Java");
		chckbxJava.setBounds(0, 85, 48, 23);
		panel.add(chckbxJava);
		
		JLabel lblSourceCode = new JLabel("Source Code");
		lblSourceCode.setBounds(235, 46, 68, 14);
		add(lblSourceCode);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(236, 76, 195, 226);
		add(textPane);
		
		Button button = new Button("Submit");
		button.setBounds(361, 310, 70, 22);
		add(button);
	}
}
