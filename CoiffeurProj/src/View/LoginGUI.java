package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Customer;
import Model.Hairdresser;
import Model.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_custid;
	private JTextField fld_stfid;
	private JPasswordField fld_stfpass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_customerPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Coiffeur Appointment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 408);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("berber.png")));
		lbl_logo.setBounds(133, 11, 381, 115);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Welcome Coiffeur Appointment System");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(186, 137, 276, 24);
		w_pane.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(22, 162, 614, 196);
		w_pane.add(tabbedPane);

		JPanel w_customerlogin = new JPanel();
		w_customerlogin.setBackground(Color.WHITE);
		tabbedPane.addTab("Customer Login", null, w_customerlogin, null);
		w_customerlogin.setLayout(null);

		JLabel lbl_custid = new JLabel("Identity Number:");
		lbl_custid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lbl_custid.setBounds(22, 11, 128, 24);
		w_customerlogin.add(lbl_custid);

		JLabel lbl_custpass = new JLabel("Password:");
		lbl_custpass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lbl_custpass.setBounds(22, 68, 128, 24);
		w_customerlogin.add(lbl_custpass);

		fld_custid = new JTextField();
		fld_custid.setBounds(150, 14, 220, 24);
		w_customerlogin.add(fld_custid);
		fld_custid.setColumns(10);

		JButton btn_register = new JButton("Register");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();

			}
		});
		btn_register.setBackground(Color.GRAY);
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_register.setBounds(49, 103, 160, 54);
		w_customerlogin.add(btn_register);

		JButton btn_custlogin = new JButton("Login");
		btn_custlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_custid.getText().length() == 0 || fld_customerPass.getText().length() == 0) {
					Helper.showMsg("Fill");
				} else {
					boolean key = true; 
					try {

						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						
						
						while (rs.next()) {

							if (fld_custid.getText().equals(rs.getString("identitynum"))
									&& fld_customerPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("Customer")) {
									Customer customer = new Customer();

									customer.setId(rs.getInt("id"));
									customer.setPassword("password");
									customer.setIdentitynum(rs.getString("identitynum"));
									customer.setName(rs.getString("name"));
									customer.setType(rs.getString("type"));
									CustomerGUI cGUI = new CustomerGUI(customer);
									cGUI.setVisible(true);
									dispose();
									key = false;
								}

								
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("No such customer was found,please register.");
					}

				}

			}
		});
		btn_custlogin.setBackground(Color.GRAY);
		btn_custlogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_custlogin.setBounds(279, 103, 160, 54);
		w_customerlogin.add(btn_custlogin);

		fld_customerPass = new JPasswordField();
		fld_customerPass.setBounds(150, 65, 220, 27);
		w_customerlogin.add(fld_customerPass);

		JPanel w_stafflogin = new JPanel();
		w_stafflogin.setBackground(Color.WHITE);
		tabbedPane.addTab("Staff Login", null, w_stafflogin, null);
		w_stafflogin.setLayout(null);

		JLabel lbl_custid_1 = new JLabel("Identity Number:");
		lbl_custid_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lbl_custid_1.setBounds(28, 11, 128, 24);
		w_stafflogin.add(lbl_custid_1);

		fld_stfid = new JTextField();
		fld_stfid.setColumns(10);
		fld_stfid.setBounds(156, 14, 220, 24);
		w_stafflogin.add(fld_stfid);

		JLabel lbl_custpass_1 = new JLabel("Password:");
		lbl_custpass_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lbl_custpass_1.setBounds(28, 68, 128, 24);
		w_stafflogin.add(lbl_custpass_1);

		JButton btn_stflogin = new JButton("Login");
		btn_stflogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_stfid.getText().length() == 0 || fld_stfpass.getText().length() == 0) {
					Helper.showMsg("fill");

				} else {
					try {

						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {

							if (fld_stfid.getText().equals(rs.getString("identitynum"))
									&& fld_stfpass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("Staff")) {
									Staff bstf = new Staff();

									bstf.setId(rs.getInt("id"));
									bstf.setPassword("password");
									bstf.setIdentitynum(rs.getString("identitynum"));
									bstf.setName(rs.getString("name"));
									bstf.setType(rs.getString("type"));
									StaffGUI sGUI = new StaffGUI(bstf);
									sGUI.setVisible(true);
									dispose();
								}

								if (rs.getString("type").equals("Hairdresser")) {
									Hairdresser hairdresser = new Hairdresser();
									hairdresser.setId(rs.getInt("id"));
									hairdresser.setPassword("password");
									hairdresser.setIdentitynum(rs.getString("identitynum"));
									hairdresser.setName(rs.getString("name"));
									hairdresser.setType(rs.getString("type"));
									HairdresserGUI hGUI = new HairdresserGUI(hairdresser);
									hGUI.setVisible(true);
									dispose();

								}
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		btn_stflogin.setBackground(Color.GRAY);
		btn_stflogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_stflogin.setBounds(38, 103, 531, 54);
		w_stafflogin.add(btn_stflogin);

		fld_stfpass = new JPasswordField();
		fld_stfpass.setBounds(156, 68, 220, 27);
		w_stafflogin.add(fld_stfpass);
	}
}
