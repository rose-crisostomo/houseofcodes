import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A server program which accepts requests from clients to capitalize strings.
 * When clients connect, a new thread is started to handle an interactive dialog
 * in which the client sends in a string and the server thread sends back the
 * capitalized version of the string.
 *
 * The program is runs in an infinite loop, so shutdown in platform dependent.
 * If you ran it from a console window with the "java" interpreter, Ctrl+C
 * generally will shut it down.
 */
public class Server extends JFrame {

	private static ServerPanel currentPanel;

	/**
	 * Application method to run the server runs in an infinite loop listening
	 * on port 9898. When a connection is requested, it spawns a new thread to
	 * do the servicing and immediately returns to listening. The server keeps a
	 * unique client number for each client that connects just to show
	 * interesting logging messages. It is certainly not necessary to do this.
	 */
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

	/**
	 * A private thread to handle capitalization requests on a particular
	 * socket. The client terminates the dialogue by sending a single line
	 * containing only a period.
	 */
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

		/**
		 * Services this thread's client by first sending the client a welcome
		 * message then repeatedly reading strings and sending back the
		 * capitalized version of the string.
		 */
		public void run() {
			try {

				// Decorate the streams so we can send characters
				// and not just bytes. Ensure output is flushed
				// after every newline.
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				while (true) {
					String input = in.readLine();
					log("Received: " + input);
					currentPanel.addLog("Received: " + input + "\n");
					if (input == null) {
						break;
					}
				}
			} catch (IOException e) {
				log("Error handling client# " + clientNumber + ": " + e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					log("Couldn't close a socket, what's going on?");
				}
				log("Connection with client# " + clientNumber + " closed");
			}
		}

		/**
		 * Logs a simple message. In this case we just write the message to the
		 * server applications standard output.
		 */
		private void log(String message) {
			System.out.println(message);
		}
	}
}
