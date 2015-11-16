import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Button;

public class ClientPanel extends JPanel implements ActionListener {

	private SpringLayout currentLayout;
	private String[] data = new String[3];
	JTextPane textPane = new JTextPane();
	ButtonGroup group = new ButtonGroup();
	Button button = new Button("Submit");
	
	public ClientPanel() {
		setupPanel();
	}

	private void setupPanel() {
		this.setLayout(currentLayout);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setLayout(null);
		setLayout(null);
		setLayout(null);

		JLabel lblLangguage = new JLabel("Language");
		lblLangguage.setBounds(46, 41, 80, 24);
		add(lblLangguage);

		JPanel panel = new JPanel();
		panel.setBounds(46, 76, 150, 150);
		add(panel);
		panel.setLayout(null);

		JRadioButton CRBtn = new JRadioButton("C");
		CRBtn.setActionCommand("C");
		CRBtn.setBounds(0, 7, 48, 23);
		panel.add(CRBtn);

		JRadioButton CPPRBtn = new JRadioButton("C++");
		CPPRBtn.setActionCommand("C++");
		CPPRBtn.setBounds(0, 33, 55, 23);
		panel.add(CPPRBtn);

		JRadioButton CSRBtn = new JRadioButton("C#");
		CSRBtn.setActionCommand("C#");
		CSRBtn.setBounds(0, 59, 48, 23);
		panel.add(CSRBtn);

		JRadioButton JavaRBtn = new JRadioButton("Java");
		JavaRBtn.setActionCommand("Java");
		JavaRBtn.setBounds(0, 85, 60, 23);
		panel.add(JavaRBtn);

		group.add(CRBtn);
		group.add(CPPRBtn);
		group.add(CSRBtn);
		group.add(JavaRBtn);

		JLabel lblSourceCode = new JLabel("Source Code");
		lblSourceCode.setBounds(235, 46, 100, 14);
		add(lblSourceCode);

		textPane.setBounds(236, 76, 195, 226);
		add(textPane);

		
		button.setBounds(361, 310, 70, 22);
		add(button);

		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			data[0] = group.getSelection().getActionCommand();
			data[2] = textPane.getText();
			String[] lines = data[2].split("\r\n|\r|\n");
			data[1] = String.valueOf(lines.length);
			try {
				Client client = new Client(data);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
