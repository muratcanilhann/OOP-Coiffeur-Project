package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Customer;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_idintity;
	private JPasswordField fld_pass;
	private Customer customer = new Customer();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setResizable(false);
		setTitle("Coiffeur Appointment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name Surname");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(85, 11, 104, 27);
		w_pane.add(lblNewLabel_1);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(14, 43, 260, 27);
		w_pane.add(fld_name);
		
		JLabel lblNewLabel_1_1 = new JLabel("Identity Number");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(85, 81, 104, 27);
		w_pane.add(lblNewLabel_1_1);
		
		fld_idintity = new JTextField();
		fld_idintity.setColumns(10);
		fld_idintity.setBounds(14, 113, 260, 27);
		w_pane.add(fld_idintity);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(105, 148, 64, 27);
		w_pane.add(lblNewLabel_1_1_1);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(14, 186, 260, 27);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Register");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_idintity.getText().length() == 0 || fld_pass.getText().length() == 0 || fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = customer.register(fld_idintity.getText(), fld_pass.getText(), fld_name.getText());
						if(control) {
							Helper.showMsg("succes");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
							
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_register.setBackground(Color.GRAY);
		btn_register.setBounds(35, 224, 210, 21);
		w_pane.add(btn_register);
		
		JButton btn_back = new JButton("Turn back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_back.setBackground(Color.GRAY);
		btn_back.setBounds(35, 262, 210, 21);
		w_pane.add(btn_back);
	}
}
