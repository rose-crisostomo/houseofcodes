import javax.swing.JFrame;

public class ServerFrame extends JFrame {
	
	private ServerPanel currentPanel;
	
	public ServerFrame() {
		setTitle("SERVER");
		
		currentPanel = new ServerPanel();
		
		setupFrame();
	}
	
	public void setupFrame()
	{
		this.setContentPane(currentPanel);
	}
	
	public static void main(String[] args){
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
	}
}
