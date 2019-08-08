package momo_appchat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class client_momo {

	private JFrame frame;
	private JTextField txtUser;
	private static client_momo window;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new client_momo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public client_momo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(52, 44, 247, 42);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket s = null;
				PrintStream outp = null;
				Scanner inp = null;
				try {
					s = new Socket("127.0.0.1", 2109);
					inp = new Scanner(s.getInputStream());
					outp = new PrintStream(s.getOutputStream());
					String user = "";
					user = txtUser.getText();
					@SuppressWarnings("deprecation")
					String pass = txtPass.getText();
					outp.println(user);
					outp.println(pass);
					System.out.println(s);
					
					String answer = inp.nextLine();
					String accept = inp.nextLine();
					if(accept.equals("accept")) {
						frame.setVisible(false);
						new manage_friend_UI(inp, outp, answer).start();
					} else {
						JOptionPane.showMessageDialog(frame, "SERVER: " + answer);
					}
					//s.close();
				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
		btnLogin.setBounds(169, 210, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(52, 123, 247, 42);
		frame.getContentPane().add(txtPass);
	}
}
