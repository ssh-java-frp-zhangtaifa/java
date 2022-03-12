package javasocket;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;
class client {
    public static void main(String[] args) {
        
        while(true){
            try {
                
                System.out.print("YOU USERNAME\n");//Login
                Scanner username1 = new Scanner(System.in);
                String username = username1.nextLine();
                System.out.print("YOU PASSWORD\n");
                Scanner userpass1 = new Scanner(System.in);
                String userpass = userpass1.nextLine();
                String psd = "xxx";//mysql password
            String Url = "jdbc:mysql://localhost/java";
            String jdbcName= "com.mysql.cj.jdbc.Driver";//Connect to the MySql database
            Class.forName(jdbcName);
            Connection conn=DriverManager.getConnection(Url,"root",psd);
            Statement stmt = conn.createStatement();
            String sql1 = "select * from javac where user like '%"+username+"%';";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while(rs1.next()){
                String pass = rs1.getString(4);
                if (pass.equals(userpass)) {
                    System.out.print("USER\n");
            Scanner sc = new Scanner(System.in);//Gets the recipient name
            String user = sc.nextLine();
            System.out.println("MSG");
            Scanner sc1 = new Scanner(System.in);
            String msg = sc1.nextLine();
            String sql = "select * from javac where user like '%"+user+"%';";//Query the recipient IP
            ResultSet rs = stmt.executeQuery(sql);//Create a data object
            while (rs.next()){
                try {
                    String ip = rs.getString(3);//Get the recipient IP
                    Socket s = new Socket(ip,10001);//connect
                    BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());//encapsulation
                    String y = msg+"["+username+"]";
                    bos.write(y.getBytes());//send
                    System.out.print("OK\n");
                    bos.flush();
                    bos.close();
                    s.close();
                } catch (Exception e) {
                    System.out.println("Incorrect password or user not found");
                }
                
             }
                } else {
                    System.out.println("Incorrect password or user not found");
                }
                
                
            }
            
             stmt.close();
             conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
