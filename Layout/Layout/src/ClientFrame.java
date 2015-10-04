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

}