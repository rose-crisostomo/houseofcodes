import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Server extends JFrame {

	private static ServerPanel currentPanel;
	private static String homedir = System.getProperty("user.dir");
	private static String language;

	public static void main(String[] args) throws Exception {
		Server s = new Server();
		s.setVisible(true);
		s.setSize(700, 500);

		System.out.println("The capitalization server is running.");
		int clientNumber = 0;
		ServerSocket listener = new ServerSocket(9898);
		try {
			while (true) {
				new Capitalizer(listener.accept(), clientNumber++, currentPanel).start();
			}
		} finally {
			listener.close();
		}
	}

	public Server() {
		setTitle("SERVER");
		currentPanel = new ServerPanel();
		setupFrame();
	}

	public void setupFrame() {
		this.setContentPane(currentPanel);
	}

	private static class Capitalizer extends Thread {
		private Socket socket;
		private int clientNumber;
		private ServerPanel currentPanel;

		public Capitalizer(Socket socket, int clientNumber, ServerPanel x) {
			this.socket = socket;
			this.clientNumber = clientNumber;
			currentPanel = x;
			log("New connection with client# " + clientNumber + " at " + socket);
			run();
		}
		
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				int ctr = 0; int lines = 0; String input = "";
				while (true) {
					++ctr;
					if(ctr == 1){
						input = in.readLine();
						language = input;
					}
					else if(ctr == 2){
						input = in.readLine();
						lines = Integer.parseInt(input);
					}
					else{
						input = "";
						for(int i=0; i<lines; i++)
							input += in.readLine() + "\n";
						ChooseLanguage(input);
					}
					//messages
					log("Received: " + input);
					currentPanel.addLog("Received: " + input + "\n");
					
					
				}
			} catch (IOException e) {
				log("Error handling client# " + clientNumber + ": " + e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					log("Couldn't close a socket, what's going on?");
				}
				log("Connection with client# " + clientNumber + " closed");
			}
		}
		
		private void log(String message) {
			System.out.println(message);
		}
		
		private void ChooseLanguage(String file) throws IOException, InterruptedException{
			switch(language){
			case "C":
				CreateFile(".c", file);
				CCompiler();
				Run(homedir + "\\C\\thesis");
				break;
			}
		}
		
		//creating a file
		private void CreateFile(String ext, String program) throws FileNotFoundException{
			File file = new File(homedir + "/" + language + "/prog" + ext);
			file.getParentFile().mkdirs();

			PrintWriter printWriter = new PrintWriter(file);
			printWriter.println(program);
			printWriter.close();
		}

		/******* C O M P I L E R S *******/
		public void CSharpCompiler() throws IOException, InterruptedException {
	        Process(homedir + "\\C#\\", " c:\\Windows\\Microsoft.NET\\Framework\\v3.5\\csc /out:thesis.exe *.cs");
	    }

	    public void CCompiler() throws IOException, InterruptedException{
	        Process(homedir + "\\C\\", "g++ *.c -o thesis");
	    }
	    
	    public void CppCompiler() throws IOException, InterruptedException{
	        Process(homedir + "\\C++\\", " g++ *.cpp -o thesis");
	    }
	    
	    public void JavaCompiler() throws IOException, InterruptedException{
	        Process(homedir + "\\Java\\", "C:\\Program Files\\Java\\jdk" + System.getProperty("java.version") + "\\bin\\javac *.java");
	    }
	    
	    //Runs the compiled program regardless of language
	    public static void Run(String command) throws IOException, InterruptedException {
	        String[] argss = {"cmd", "/c", "start", command};
	        
	        ProcessBuilder pb;
	        
	        pb = new ProcessBuilder(argss);
	        pb.start();
	    }
	    
	    //function to process command lines
	    public static void Process(String command, String exe) throws IOException, InterruptedException {
	        final Process p;
	        if (command != null) {
	            p = Runtime.getRuntime().exec(exe, null, new File(command));
	        } else {
	            p = Runtime.getRuntime().exec(exe);
	        }

	        new Thread(new Runnable() {
	            public void run() {
	                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	                String line = null;

	                try {
	                    while ((line = input.readLine()) != null) {
	                        System.out.println(line);
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }).start();

	        p.waitFor();
	    }
	}
}
