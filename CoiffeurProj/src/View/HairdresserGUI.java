package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Hairdresser;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class HairdresserGUI extends JFrame {

	private JPanel w_pane;
	private static Hairdresser hairdresser = new Hairdresser();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HairdresserGUI frame = new HairdresserGUI(hairdresser);
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
	public HairdresserGUI(Hairdresser hairdresser) throws SQLException {

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Date";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		for (int i = 0; i < hairdresser.getWhourList(hairdresser.getId()).size(); i++) {
			whourData[0] = hairdresser.getWhourList(hairdresser.getId()).get(i).getId();
			whourData[1] = hairdresser.getWhourList(hairdresser.getId()).get(i).getWdate();
			whourModel.addRow(whourData);

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

		JLabel lblNewLabel = new JLabel("Welcome" + hairdresser.getName());
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
		w_tab.setBounds(20, 54, 680, 377);
		w_pane.add(w_tab);

		JPanel w_workhour = new JPanel();
		w_workhour.setBackground(Color.WHITE);
		w_tab.addTab("Work Hours", null, w_workhour, null);
		w_workhour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 145, 25);
		w_workhour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setBounds(166, 11, 69, 25);
		select_time.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		select_time.setModel(new DefaultComboBoxModel(new String[] { "10:00", "10:30", "11:00", "11:30", "12:00",
				"12:30", "13:30", "14:00", "14:30", "15:00", "15:30" }));
		w_workhour.add(select_time);

		JButton btnAdd_whour = new JButton("Add");
		btnAdd_whour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}

				if (date.length() == 0) {
					Helper.showMsg("Please enter valid date !");
				} else {

					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						boolean control = hairdresser.addWhour(hairdresser.getId(), hairdresser.getName(), selectDate);
						if (control) {
							Helper.showMsg("succes");
							updateWhourModel(hairdresser);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		btnAdd_whour.setBounds(240, 11, 69, 25);
		btnAdd_whour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnAdd_whour.setBackground(Color.GRAY);
		w_workhour.add(btnAdd_whour);

		JScrollPane w_scrollwhour = new JScrollPane();
		w_scrollwhour.setBounds(0, 47, 675, 302);
		w_workhour.add(w_scrollwhour);

		table_whour = new JTable(whourModel);
		w_scrollwhour.setViewportView(table_whour);
		
		JButton btn_deleteWhour = new JButton("Delete");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >= 0) {
					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					boolean control;
					try {
						control = hairdresser.deleteWhour(selID);
						if(control) {
							Helper.showMsg("succes");
							updateWhourModel(hairdresser);
							
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMsg("Please chose date !");
					
					
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_deleteWhour.setBackground(Color.GRAY);
		btn_deleteWhour.setBounds(580, 11, 85, 25);
		w_workhour.add(btn_deleteWhour);

	}
	
	public void updateWhourModel(Hairdresser hairdresser) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < hairdresser.getWhourList(hairdresser.getId()).size(); i++) {
			whourData[0] = hairdresser.getWhourList(hairdresser.getId()).get(i).getId();
			whourData[1] = hairdresser.getWhourList(hairdresser.getId()).get(i).getWdate();
			whourModel.addRow(whourData);

		}
		}
	}

