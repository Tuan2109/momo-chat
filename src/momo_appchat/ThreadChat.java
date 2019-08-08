package momo_appchat;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ThreadChat extends Thread {
	Connection conn = null;
	Scanner inp = null;
	PrintStream outp = null;
	String username = "";
	HashMap<Integer, String> hm = new HashMap<Integer, String>();
	List<ThreadChat> list_user = new ArrayList<>();
	
	public ThreadChat(Scanner inp, PrintStream outp, Connection conn, List<ThreadChat> list_user, String username) {
		this.inp = inp;
		this.outp = outp;
		this.conn = conn;
		this.list_user = list_user;
		this.username = username;
	}
	
	public void run() {
		try {
			while(true) {
				String data = inp.nextLine();
				if(!data.equals("")) {
					String[] split = data.split(":", 3);
					String fromUser = split[0];
					String toUser = split[1];
					String message = split[2];
					if(!checkStatus(toUser)) {
						String warning = "User '" + toUser + "' offline";
						outp.println(warning);
					} else {
						privateMess(fromUser, toUser, message);
						System.out.println(list_user);
					}
				}
				
			}
		} catch (Exception e) {
			updateStatus();
		}
	}
	
	public void updateStatus() {
		try {
			Statement stmt = conn.createStatement();
			String query = "UPDATE [dbo].[User] SET status = 0 WHERE username = '" + username + "'";
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean checkStatus(String username) {
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM [momo_dev].[dbo].[User] WHERE username='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				//trong table user co (id,user,pass,status)
				if(rs.getBoolean(4))
					return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public void privateMess(String fromUser, String toUser, String data) {
		try {
			if(toUser.equals(this.username)) {
				String message = fromUser + ":" + data;
				outp.println(message);
				return;
			}
			for(ThreadChat t : list_user) {
				try {
					if(t.username.equals(toUser)) {
						String exist = fromUser + "->" + toUser + ": " + data;
						if(!data.equals("")) {
							outp.println(exist);
							t.outp.println(exist);
						}
						return;
					}
				} catch (Exception e) {
					list_user.remove(t);
				}
			}
		} catch (Exception e) {
			System.out.println("loi o privateMess");
		}
	}
	
}
