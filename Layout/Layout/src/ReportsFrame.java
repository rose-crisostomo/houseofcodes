import java.awt.Dimension;

import javax.swing.JFrame;

public class ReportsFrame extends JFrame {
	
	private ReportsPanel currentPanel;
	
	public ReportsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("REPORTS");
		
		currentPanel = new ReportsPanel();
		
		setupFrame();
	}
	
	public void setupFrame()
	{
		this.setContentPane(currentPanel);
	}
	
	public static void main(String[] args){
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportsFrame().setVisible(true);
            }
        });
	}
}
