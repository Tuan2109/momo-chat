package momo_appchat;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ser_momo {
	private static String DB_URL = "jdbc:sqlserver://T-H-T\\SQLEXPRESS;"
            + "databaseName=momo_dev;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123";
    
	public static void main(String[] agrs) {
		System.out.println("Server đã bật chờ kết nối.");
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(2109);
			while(true) {
				new ThreadLogin(ss.accept(), conn).start();
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
}
