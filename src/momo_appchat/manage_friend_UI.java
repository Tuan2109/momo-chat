package momo_appchat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

public class manage_friend_UI extends Thread {

	private JFrame frmChat;
	private static JTextField txtMessage;
	private PrintStream outp = null;
	private Scanner inp = null;
	private String username = "";
	private static JTextField txtToUser;
	private static JTextArea txtAreaChat;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			manage_friend_UI window = new manage_friend_UI(this.inp, this.outp, this.username);
			window.frmChat.setVisible(true);
			frmChat.setTitle("Username: " + this.username);
			while(true) {
				txtAreaChat.setCaretPosition(txtAreaChat.getDocument().getLength());
				String DFS = inp.nextLine();
				if(!DFS.equals("")) {
					String area = txtAreaChat.getText();
					if(area.equals("")) {
						txtAreaChat.setText(DFS);
					} else {
						txtAreaChat.setText(area + "\n" + DFS);						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public manage_friend_UI(Scanner inp, PrintStream outp, String username) {
		this.inp = inp;
		this.outp = outp;
		this.username = username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChat = new JFrame();
		frmChat.setTitle("Username: " + username);
		frmChat.setBounds(100, 100, 506, 395);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 22, 139, 236);
		frmChat.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(114, 5, 2, 2);
		panel_1.add(scrollPane);
		
		JButton btnA = new JButton("a");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newToUser = btnA.getText();
				String oldToUser = txtToUser.getText();
				if(!newToUser.equals(oldToUser)) {
					txtToUser.setText(newToUser);
					txtAreaChat.setText("");
				}
			}
		});
		btnA.setBounds(22, 26, 89, 23);
		panel_1.add(btnA);
		
		JButton btnB = new JButton("b");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newToUser = btnB.getText();
				String oldToUser = txtToUser.getText();
				if(!newToUser.equals(oldToUser)) {
					txtToUser.setText(newToUser);
					txtAreaChat.setText("");
				}
			}
		});
		btnB.setBounds(22, 58, 89, 23);
		panel_1.add(btnB);
		
		JButton btnC = new JButton("c");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newToUser = btnC.getText();
				String oldToUser = txtToUser.getText();
				if(!newToUser.equals(oldToUser)) {
					txtToUser.setText(newToUser);
					txtAreaChat.setText("");
				}
			}
		});
		btnC.setBounds(22, 92, 89, 23);
		panel_1.add(btnC);
		
		JButton btnD = new JButton("d");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newToUser = btnD.getText();
				String oldToUser = txtToUser.getText();
				if(!newToUser.equals(oldToUser)) {
					txtToUser.setText(newToUser);
					txtAreaChat.setText("");
				}
			}
		});
		btnD.setBounds(22, 126, 89, 23);
		panel_1.add(btnD);
		
		JButton btnE = new JButton("e");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newToUser = btnE.getText();
				String oldToUser = txtToUser.getText();
				if(!newToUser.equals(oldToUser)) {
					txtToUser.setText(newToUser);
					txtAreaChat.setText("");
				}
			}
		});
		btnE.setBounds(22, 159, 89, 23);
		panel_1.add(btnE);
		
		JPanel panel = new JPanel();
		panel.setBounds(159, 22, 321, 323);
		frmChat.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 45, 301, 267);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 301, 221);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		txtAreaChat = new JTextArea();
		txtAreaChat.setEditable(false);
		txtAreaChat.setBounds(0, 0, 301, 221);
		panel_4.add(txtAreaChat);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 221, 301, 46);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(0, 11, 206, 35);
		panel_5.add(txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = txtMessage.getText();
				txtMessage.setText("");
				String toUser = txtToUser.getText();
				outp.println(username + ":" + toUser + ":" + message);
			}
		});
		btnSend.setBounds(212, 12, 89, 34);
		panel_5.add(btnSend);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(161, 11, 150, 23);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		txtToUser = new JTextField();
		txtToUser.setText("a");
		txtToUser.setEditable(false);
		txtToUser.setBounds(0, 0, 150, 23);
		panel_3.add(txtToUser);
		txtToUser.setColumns(10);
		
		JLabel lblChatWith = new JLabel("Chat with");
		lblChatWith.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblChatWith.setBounds(95, 11, 62, 23);
		panel.add(lblChatWith);
	}
}
