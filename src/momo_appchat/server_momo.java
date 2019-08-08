package momo_appchat;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class server_momo {
	private static String DB_URL = "jdbc:sqlserver://T-H-T\\SQLEXPRESS;"
            + "databaseName=momo_dev;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123";
	public final static List<ThreadChat> list_user = new ArrayList<>();
	private static Scanner inp = null;
	private static PrintStream outp = null;
	private static Connection conn = null;
	private static String username = "";
    
	public static void main(String[] agrs) {
		System.out.println("Server đã bật chờ kết nối.");
		conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(2109);
			while(true) {
				Socket s = ss.accept();
				inp = new Scanner(s.getInputStream());
				outp = new PrintStream(s.getOutputStream());
				Boolean check = checkLogin();
				if(check.equals(true)) {
					ThreadChat user = new ThreadChat(inp, outp, conn, list_user, username);
					addClient(user);
					user.start();
				}
			}
		} catch (Exception e) {
			return;
		}
	}
	 public static Connection getConnection(String dbURL, String userName, String password) {
	        Connection conn = null;
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            conn = DriverManager.getConnection(dbURL, userName, password);
	            System.out.println("connect db successfully!");
	        } catch (Exception ex) {
	            System.out.println("connect db failure!");
	            ex.printStackTrace();
	        }
	        return conn;
    }
	public static void addClient(ThreadChat user) {
	        synchronized (user) {
	            list_user.add(user);
	        }
	}
	
	public static boolean checkLogin() {
		username = inp.nextLine();
		String pass = inp.nextLine();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM [momo_dev].[dbo].[User] WHERE username='" + username + "' AND password ='" + pass + "'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				//trong talbe user co (id,user,pass)
				username = rs.getString(2);
				outp.println(rs.getString(2));
				outp.println("accept");
				
				Statement stmt_2 = conn.createStatement();
				String query_2 = "UPDATE [dbo].[User] SET status = 1 WHERE username = '" + username + "'";
				stmt_2.execute(query_2);
				return true;
			}
			outp.println("User & pass do not match");
			outp.println("reject");
			return false;
		} catch(Exception e) {
		}
		return false;
	}
}
