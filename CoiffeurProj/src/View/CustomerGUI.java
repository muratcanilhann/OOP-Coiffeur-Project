package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Category;
import Model.Customer;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class CustomerGUI extends JFrame {

	private JPanel w_pane;
	private static Customer customer = new Customer();
	private Category category = new Category();
	private JTable table_hairdresser;
	private DefaultTableModel hairdresserModel;
	private Object[] hairdresserData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectHairdresserID = 0;
	private String selectHairdresserName = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI(customer);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public CustomerGUI(Customer customer) throws SQLException {

		hairdresserModel = new DefaultTableModel();
		Object[] colhairdresser = new Object[2];
		colhairdresser[0] = "ID";
		colhairdresser[1] = "Name Surname";
		hairdresserModel.setColumnIdentifiers(colhairdresser);
		hairdresserData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Date";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Hairdresser Name";
		colAppoint[2] = "Date";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for (int i = 0; i < appoint.getCustomerList(customer.getId()).size(); i++) {
			appointData[0] = appoint.getCustomerList(customer.getId()).get(i).getId();
			appointData[1] = appoint.getCustomerList(customer.getId()).get(i).getHairdresserName();
			appointData[2] = appoint.getCustomerList(customer.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);

		}

		setResizable(false);
		setTitle("Coiffeur Appointment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome," + customer.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 269, 41);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(583, 11, 107, 30);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(20, 63, 680, 377);
		w_pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Appointment System", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollhairdresser = new JScrollPane();
		w_scrollhairdresser.setBounds(10, 33, 240, 294);
		w_appointment.add(w_scrollhairdresser);

		table_hairdresser = new JTable(hairdresserModel);
		w_scrollhairdresser.setViewportView(table_hairdresser);

		JLabel lblNewLabel_1 = new JLabel("Hairdresser List");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(26, 8, 127, 24);
		w_appointment.add(lblNewLabel_1);

		JLabel lblcategory = new JLabel("Category");
		lblcategory.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblcategory.setBounds(308, 11, 68, 27);
		w_appointment.add(lblcategory);

		JComboBox select_category = new JComboBox();
		select_category.setBounds(271, 33, 142, 24);
		select_category.addItem("--Choose Category--");
		for (int i = 0; i < category.getList().size(); i++) {
			select_category.addItem(new Item(category.getList().get(i).getId(), category.getList().get(i).getName()));

		}

		select_category.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_category.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_hairdresser.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < category.getCategoryHairdresserList(item.getKey()).size(); i++) {
							hairdresserData[0] = category.getCategoryHairdresserList(item.getKey()).get(i).getId();
							hairdresserData[1] = category.getCategoryHairdresserList(item.getKey()).get(i).getName();
							hairdresserModel.addRow(hairdresserData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_hairdresser.getModel();
					clearModel.setRowCount(0);

				}
			}
		});
		w_appointment.add(select_category);

		JLabel lblChooseHairdresser = new JLabel("Choose Hairdresser");
		lblChooseHairdresser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblChooseHairdresser.setBounds(282, 68, 120, 27);
		w_appointment.add(lblChooseHairdresser);

		JButton btn_selhairdresser = new JButton("Choose");
		btn_selhairdresser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_hairdresser.getSelectedRow();
				if (row >= 0) {
					String value = table_hairdresser.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					table_whour.setModel(whourModel);
					selectHairdresserID = id;
					selectHairdresserName = table_hairdresser.getModel().getValueAt(row, 1).toString();
					// System.out.println(selectHairdresserID + " - "+selectHairdresserName);
				} else {
					Helper.showMsg("Please choose Hairdresser");

				}
			}
		});
		btn_selhairdresser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_selhairdresser.setBackground(Color.GRAY);
		btn_selhairdresser.setBounds(295, 92, 95, 33);
		w_appointment.add(btn_selhairdresser);

		JLabel lblNewLabel_1_1 = new JLabel("Available Hours");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(441, 8, 127, 24);
		w_appointment.add(lblNewLabel_1_1);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(425, 33, 240, 294);
		w_appointment.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);

		JLabel lblMakeAnAppointment = new JLabel("Appointment");
		lblMakeAnAppointment.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblMakeAnAppointment.setBounds(305, 136, 82, 27);
		w_appointment.add(lblMakeAnAppointment);

		JButton btn_addApp = new JButton("Make appointment");
		btn_addApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = customer.addAppointment(selectHairdresserID, customer.getId(),
								selectHairdresserName, customer.getName(), date);
						if (control) {
							Helper.showMsg("succes");
							customer.updateWhourStatus(selectHairdresserID, date);
							updateWhourModel(selectHairdresserID);
							updateAppointModel(customer.getId());

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Helper.showMsg("Please enter a valid date please");
				}
			}
		});
		btn_addApp.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_addApp.setBackground(Color.GRAY);
		btn_addApp.setBounds(260, 164, 153, 33);
		w_appointment.add(btn_addApp);

		JPanel w_appoint = new JPanel();
		w_tab.addTab("My Appointments", null, w_appoint, null);
		w_appoint.setLayout(null);

		JScrollPane w_scrollappoint = new JScrollPane();
		w_scrollappoint.setBounds(10, 11, 655, 327);
		w_appoint.add(w_scrollappoint);

		table_appoint = new JTable(appointModel);
		w_scrollappoint.setViewportView(table_appoint);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
	}

	public void updateWhourModel(int hairdresser_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(hairdresser_id).size(); i++) {
			whourData[0] = whour.getWhourList(hairdresser_id).get(i).getId();
			whourData[1] = whour.getWhourList(hairdresser_id).get(i).getWdate();
			whourModel.addRow(whourData);

		}
	}
	
	public void updateAppointModel(int customer_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < appoint.getCustomerList(customer_id).size(); i++) {
			appointData[0] = appoint.getCustomerList(customer_id).get(i).getId();
			appointData[1] = appoint.getCustomerList(customer_id).get(i).getHairdresserName();
			appointData[2] = appoint.getCustomerList(customer_id).get(i).getAppDate();
			appointModel.addRow(appointData);

		}
	}
}
