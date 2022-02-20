package javasocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server {
	public static void main(String[] args) throws IOException,Exception {
		ServerSocket server = new ServerSocket(10000);
		
		while (true) {
			Socket socket = server.accept();
			invoke(socket);
			
		}

	}
	
	private static void invoke(final Socket client) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				BufferedReader in = null;
				PrintWriter out = null;
				try {
					in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					out = new PrintWriter(client.getOutputStream());
					
					while (true) {
						String msg = in.readLine();
						if (!msg.equals(null)) {
							System.out.println(msg);
						if (msg.equals("bye")) {
							break;
						}
						} else {							
						}
					}
				} catch(IOException ex) {
					ex.printStackTrace();
				} finally {
					try {
						in.close();
					} catch (Exception e) {}
					try {
						out.close();
					} catch (Exception e) {}
					try {
						client.close();
					} catch (Exception e) {}
				}
			}
		}).start();
	}
}
