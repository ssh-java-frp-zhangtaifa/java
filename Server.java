package javasocket;
import java.io.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
 
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
						int throwsd = 1;
						throwsd ++;
						String msg = in.readLine();//Gets the string
						InetAddress r = client.getInetAddress();
						if (!msg.equals(null)) {//Check whether it is empty, and if it is empty, an error will be reported
							System.out.println(msg+" from "+r);
							try {
								Class.forName("com.mysql.cj.jdbc.Driver");//Upload the information to the database
								Connection con = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/java", "root", "java");
								Statement stmt = con.createStatement();
								stmt.execute("SET SESSION sql_mode=NO_ZERO_IN_DATE;");
								String tString = new String("insert into `java1` (`ip`,`msg`) values (");
								StringBuffer stringBuffer = new StringBuffer(tString);
								stringBuffer.append("'");
								stringBuffer.append(r);
								stringBuffer.append("'");
								stringBuffer.append(",'");
								stringBuffer.append(msg);
								stringBuffer.append("');");
								String dstString = new String(stringBuffer);
								stmt.execute(dstString);
								//System.out.println(rString);
								//TODO
							} catch (Exception e) {
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
