import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportsPanel extends JPanel {
	private JTable table;
	public ReportsPanel() {
		setupPanel();
	}
	
	public void setupPanel()
	{
		setLayout(null);
		
		String[][] data = {};
		String[] column = {"Student", "Activity1", "Activity2", "Activity3", "Average"};
		
		DefaultTableModel mdl = new DefaultTableModel(data, column);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 84, 647, 239);
		add(scrollPane);
		
		table = new JTable(mdl);
		scrollPane.setViewportView(table);
		
		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] row = {" ", " ", " ", " ", " "};
				mdl.addRow(row);
			}
		});
		btnLoadData.setBounds(590, 50, 91, 23);
		add(btnLoadData);
	}
}
