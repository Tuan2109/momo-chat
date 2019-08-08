package momo_appchat;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class ThreadLogin extends Thread {
	Socket s = null;
	Connection conn = null;
	Scanner inp = null;
	PrintStream outp = null;
	HashMap<Integer, String> hm = new HashMap<Integer, String>();
	private static int t = 1;
	
	public ThreadLogin(Socket s, Connection conn) {
		this.s = s;
		this.conn = conn;
	}
	
	public void run() {
		try {
			inp = new Scanner(s.getInputStream());
			outp = new PrintStream(s.getOutputStream());
			
			////kiểm tra port cli kết nối
			String ipCli[] = s.getRemoteSocketAddress().toString().split(":");
			System.out.println("-------------------------------------------");
			System.out.println(s.getRemoteSocketAddress() + " đã kết nối với Server.");
			t = Integer.parseInt(ipCli[1]);
			///
			String user = "";
			user = inp.nextLine();
			String pass =inp.nextLine();
			pass = md5(pass);
			System.out.println(user + ":" + pass);
			
	        Statement stmt = conn.createStatement();
	        String query = "SELECT * FROM [momo_dev].[dbo].[User] WHERE username='" + user + "' AND password ='" + pass + "'";
	        ResultSet rs = stmt.executeQuery(query);
	        System.out.println(rs);
	            if(rs.next()) {
	            	//trong talbe user co (id,user,pass)
            		outp.println("name: " + rs.getString(2));
            		outp.println("accept");
            		return;
	            }
	            outp.println("user hoặc pass sai.");
	            outp.println("reject");
		} catch (Exception e) {
		}
	}
	
	public static String md5(String pass) {
		//MessageDigest digest;
		//try {
		//	digest = MessageDigest.getInstance("MD5");
		//	digest.update(pass.getBytes());
		//	BigInteger bigInteger = new BigInteger(1, digest.digest());
		//	pass = bigInteger.toString(16);
		//} catch (Exception e) {
		//	// TODO: handle exception
		//}
		return pass;
	}
}
