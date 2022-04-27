import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField textbox_custIdentity;
	private JTextField textbox_custPass;
	private JTextField textbox_stffidentity;
	private JTextField textbox_passstaf;

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
		setTitle("Coiffeur Automation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 410);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label_logo = new JLabel(new ImageIcon(getClass().getResource("berber.png")));
		label_logo.setBounds(66, 11, 395, 103);
		w_pane.add(label_logo);
		
		JLabel lblNewLabel = new JLabel("Coiffeur Appointment System");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		lblNewLabel.setBounds(173, 125, 204, 33);
		w_pane.add(lblNewLabel);
		
		JTabbedPane customer_panel = new JTabbedPane(JTabbedPane.TOP);
		customer_panel.setBounds(10, 167, 509, 193);
		w_pane.add(customer_panel);
		
		JPanel panel = new JPanel();
		customer_panel.addTab("Customer Login", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Identity Number:");
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(15, 11, 116, 46);
		panel.add(lblNewLabel_1);
		
		textbox_custIdentity = new JTextField();
		textbox_custIdentity.setBounds(120, 22, 198, 27);
		panel.add(textbox_custIdentity);
		textbox_custIdentity.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(20, 57, 106, 32);
		panel.add(lblNewLabel_2);
		
		textbox_custPass = new JTextField();
		textbox_custPass.setBounds(120, 62, 198, 25);
		panel.add(textbox_custPass);
		textbox_custPass.setColumns(10);
		
		JButton btn_customerRegister = new JButton("Register");
		btn_customerRegister.setFont(new Font("Verdana Pro Black", Font.PLAIN, 12));
		btn_customerRegister.setBackground(Color.GRAY);
		btn_customerRegister.setBounds(30, 101, 164, 64);
		panel.add(btn_customerRegister);
		
		JButton btn_customerLogin = new JButton("Login");
		btn_customerLogin.setFont(new Font("Verdana Pro Black", Font.PLAIN, 12));
		btn_customerLogin.setBackground(Color.GRAY);
		btn_customerLogin.setBounds(280, 101, 164, 64);
		panel.add(btn_customerLogin);
		
		JPanel panel_1 = new JPanel();
		customer_panel.addTab("Staff Login", null, panel_1, null);
		panel_1.setLayout(null);
		
		textbox_stffidentity = new JTextField();
		textbox_stffidentity.setColumns(10);
		textbox_stffidentity.setBounds(132, 22, 198, 27);
		panel_1.add(textbox_stffidentity);
		
		JLabel lbl_staffidentity = new JLabel("Identity Number:");
		lbl_staffidentity.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lbl_staffidentity.setBounds(27, 11, 116, 46);
		panel_1.add(lbl_staffidentity);
		
		JLabel lbl_passwordStaff = new JLabel("Password:");
		lbl_passwordStaff.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lbl_passwordStaff.setBounds(32, 57, 106, 32);
		panel_1.add(lbl_passwordStaff);
		
		textbox_passstaf = new JTextField();
		textbox_passstaf.setColumns(10);
		textbox_passstaf.setBounds(132, 60, 198, 25);
		panel_1.add(textbox_passstaf);
		
		JButton btn_StaffLogin = new JButton("Login");
		btn_StaffLogin.setFont(new Font("Verdana Pro Black", Font.PLAIN, 12));
		btn_StaffLogin.setBackground(Color.GRAY);
		btn_StaffLogin.setBounds(142, 101, 164, 64);
		panel_1.add(btn_StaffLogin);
	}
}
