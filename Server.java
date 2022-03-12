package javasocket;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;
public class Server {
	public static void main(String[] args) throws IOException,Exception {
		System.out.println("user");
			Scanner sc = new Scanner(System.in);//Login
			String iString = sc.nextLine();
			System.out.println("password");
			Scanner userpass1 = new Scanner(System.in);
                String userpass = userpass1.nextLine();
			System.out.println("ipv6");//Get your own ipv6
			try (Scanner dScanner = new Scanner(System.in)) {
				String dString = dScanner.nextLine(); 
				String psd = "java";//
				String Url = "jdbc:mysql://localhost/java";
				String jdbcName= "com.mysql.cj.jdbc.Driver";
				String h = "UPDATE javac SET ip='"+dString+"' where user like '%"+iString+"%';";//Update your own IP address in the database
				Class.forName(jdbcName);
				Connection conn=DriverManager.getConnection(Url,"root",psd);//Connect to database
				Statement stmt = conn.createStatement();
            String sql1 = "select * from javac where pass like '%"+userpass+"%';";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while(rs1.next()){
				String hf = rs1.getString(4);
				if(hf.equals(userpass)){
					stmt.execute(h);
				}else{
					System.out.println("Incorrect password or user not found");
					System.out.println("Please rerun thanks");
					while(true){
						
					}
				}
			}
			}
		
		System.out.println("Welcome to Zhang Taifa's chat room Email: zhangtaifa123@outlook.com");

		try (ServerSocket server = new ServerSocket(10001)) {
			while (true) {
				Socket socket = server.accept();//Listening
				invoke(socket);//If someone connects, create a new thread invoke to handle	
			}
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
						if (!msg.equals(null)) {//Check whether it is empty, and if it is empty, an error will be reported
							System.out.println(msg);
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
