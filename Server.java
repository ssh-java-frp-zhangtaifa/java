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
			Socket socket = server.accept();//Listening
			invoke(socket);//If someone connects, create a new thread invoke to handle
			
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
						String msg = in.readLine();//Gets the string
						InetAddress r = client.getInetAddress();
						if (!msg.equals(null)) {//Check whether it is empty, and if it is empty, an error will be reported
							System.out.println(msg+" come from "+r);
						if (msg.equals("bye")) {
							break;
						}
						} else {							
						}
					}
				} catch(IOException ex) {//
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
//Very simple chat room，Meet the client.java use
//Thanks to watching
