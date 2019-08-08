package momo_appchat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class manage_friend_UI extends Thread {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					manage_friend_UI window = new manage_friend_UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public manage_friend_UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 427, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(75, 22, 230, 25);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("");
		lblName.setBounds(10, 0, 89, 14);
		panel.add(lblName);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(75, 65, 230, 270);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(114, 5, 2, 2);
		panel_1.add(scrollPane);
		
		JButton btnB = new JButton("A");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnB.setBounds(22, 26, 89, 23);
		panel_1.add(btnB);
		
		JButton btnC = new JButton("B");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnC.setBounds(22, 58, 89, 23);
		panel_1.add(btnC);
		
		JButton btnD = new JButton("C");
		btnD.setBounds(22, 92, 89, 23);
		panel_1.add(btnD);
		
		JButton btnE = new JButton("E");
		btnE.setBounds(22, 126, 89, 23);
		panel_1.add(btnE);
	}
}
