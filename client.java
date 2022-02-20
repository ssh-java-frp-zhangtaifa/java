package javasocket;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        while(true){
            try {
                Scanner l = new Scanner(System.in);//获取目标ip
					String e8 =  new String(l.nextLine());
					Socket e = new Socket(e8,10000);//连接
                    Scanner t = new Scanner(System.in);//获取要发送的内容
                    String s = new String(t.nextLine());
					PrintWriter p =new PrintWriter(e.getOutputStream());//发送
					p.println(s);
					p.flush();
					p.close();
					e.close();
            } catch (Exception e) {
                System.out.println("The other party is not online or the port is occupied or does not have this IP");            
            }
            
        }
    }
}
