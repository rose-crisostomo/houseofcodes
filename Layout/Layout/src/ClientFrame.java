import javax.swing.JFrame;

public class ClientFrame extends JFrame {
	
	private ClientPanel currentPanel;
	
	public ClientFrame() {
		setTitle("CLIENT");
		
		currentPanel = new ClientPanel();
		
		setupFrame();
	}
	
	public void setupFrame()
	{
		this.setContentPane(currentPanel);
	}
	
	public static void main(String[] args){
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientFrame().setVisible(true);
            }
        });
	}

}