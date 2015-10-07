import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;


public class problemInput {

	private JFrame frmCreateProblem;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					problemInput window = new problemInput();
					window.frmCreateProblem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public problemInput() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateProblem = new JFrame();
		frmCreateProblem.setTitle("Create Problem");
		frmCreateProblem.setBounds(100, 100, 455, 309);
		frmCreateProblem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateProblem.getContentPane().setLayout(null);
		
		JLabel lblSourceCode = new JLabel("Problem:");
		lblSourceCode.setBounds(30, 11, 99, 18);
		frmCreateProblem.getContentPane().add(lblSourceCode);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(224, 238, 89, 23);
		frmCreateProblem.getContentPane().add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(323, 238, 89, 23);
		frmCreateProblem.getContentPane().add(btnCancel);
		
		textField = new JTextField();
		textField.setBounds(30, 48, 192, 181);
		frmCreateProblem.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(272, 48, 148, 179);
		frmCreateProblem.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRecquired = new JLabel("Required:");
		lblRecquired.setBounds(272, 13, 65, 14);
		frmCreateProblem.getContentPane().add(lblRecquired);
		
		JLabel lblNewLabel = new JLabel("Functions:");
		lblNewLabel.setBounds(274, 29, 59, 14);
		frmCreateProblem.getContentPane().add(lblNewLabel);
	}
}
