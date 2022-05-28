package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

public class StaffGUI extends JFrame {

	static Staff staff = new Staff();
	Category category = new Category();
	private JPanel w_pane;
	private JTextField fld_hName;
	private JTextField fld_hIdentity;
	private JTextField fld_hPass;
	private JTextField fld_hairdresserId;
	private JTable table_hairdresser;
	private DefaultTableModel hairdresserModel = null;
	private Object[] hairdresserData = null;
	private JTable table_category;
	private JTextField fld_categoryName;
	private DefaultTableModel categoryModel = null;
	private Object[] categoryData = null;
	private JPopupMenu categoryMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffGUI frame = new StaffGUI(staff);
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
	/**
	 * @param staff
	 * @throws SQLException
	 */
	public StaffGUI(Staff staff) throws SQLException {

		hairdresserModel = new DefaultTableModel();
		Object[] colhairdresserName = new Object[4];
		colhairdresserName[0] = "ID";
		colhairdresserName[1] = "Name Surname";
		colhairdresserName[2] = "Identity Number ";
		colhairdresserName[3] = "Password";
		hairdresserModel.setColumnIdentifiers(colhairdresserName);
		hairdresserData = new Object[4];
		for (int i = 0; i < staff.getHairdresserList().size(); i++) {
			hairdresserData[0] = staff.getHairdresserList().get(i).getId();
			hairdresserData[1] = staff.getHairdresserList().get(i).getName();
			hairdresserData[2] = staff.getHairdresserList().get(i).getIdentitynum();
			hairdresserData[3] = staff.getHairdresserList().get(i).getPassword();
			hairdresserModel.addRow(hairdresserData);
		}
		categoryModel = new DefaultTableModel();
		Object[] colCategory = new Object[2];
		colCategory[0] = "ID";
		colCategory[1] = "Category Name";
		categoryModel.setColumnIdentifiers(colCategory);
		categoryData = new Object[2];
		for (int i = 0; i < category.getList().size(); i++) {
			categoryData[0] = category.getList().get(i).getId();
			categoryData[1] = category.getList().get(i).getName();
			categoryModel.addRow(categoryData);

		}
		//WorkerModel
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Name Surname";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		

		setResizable(false);
		setTitle("Coiffeur Appointment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome " +" " + staff.getName());
		lblNewLabel.setBounds(23, 21, 269, 41);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(596, 21, 107, 30);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(23, 73, 680, 377);
		w_pane.add(w_tab);

		JPanel w_hairdresser = new JPanel();
		w_hairdresser.setBackground(Color.WHITE);
		w_tab.addTab("Hairdresser Management", null, w_hairdresser, null);
		w_hairdresser.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Name Surname");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(544, 11, 104, 27);
		w_hairdresser.add(lblNewLabel_1);

		fld_hName = new JTextField();
		fld_hName.setBounds(519, 43, 146, 27);
		w_hairdresser.add(fld_hName);
		fld_hName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Identity Number");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(544, 81, 104, 20);
		w_hairdresser.add(lblNewLabel_2);

		fld_hIdentity = new JTextField();
		fld_hIdentity.setBounds(519, 112, 146, 27);
		w_hairdresser.add(fld_hIdentity);
		fld_hIdentity.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(560, 150, 72, 14);
		w_hairdresser.add(lblNewLabel_3);

		fld_hPass = new JTextField();
		fld_hPass.setBounds(519, 175, 146, 27);
		w_hairdresser.add(fld_hPass);
		fld_hPass.setColumns(10);

		JButton btn_addHairdresser = new JButton("Add");
		btn_addHairdresser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hName.getText().length() == 0 || fld_hPass.getText().length() == 0
						|| fld_hIdentity.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = staff.addHairdresser(fld_hIdentity.getText(), fld_hPass.getText(),
								fld_hName.getText());
						if (control) {
							Helper.showMsg("succes");
							fld_hName.setText(null);
							fld_hIdentity.setText(null);
							fld_hPass.setText(null);
							updateHairdresserModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		btn_addHairdresser.setBackground(Color.GRAY);
		btn_addHairdresser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_addHairdresser.setBounds(553, 218, 95, 33);
		w_hairdresser.add(btn_addHairdresser);

		JLabel lblNewLabel_4 = new JLabel("User Id");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(570, 260, 52, 14);
		w_hairdresser.add(lblNewLabel_4);

		fld_hairdresserId = new JTextField();
		fld_hairdresserId.setBounds(528, 277, 137, 27);
		w_hairdresser.add(fld_hairdresserId);
		fld_hairdresserId.setColumns(10);

		JButton btn_dellHairdresser = new JButton("Delete");
		btn_dellHairdresser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hairdresserId.getText().length() == 0) {
					Helper.showMsg("Please select a valid hairdresser");

				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_hairdresserId.getText());
						try {
							boolean control = staff.deleteHairdresser(selectID);
							if (control) {
								Helper.showMsg("succes");
								fld_hairdresserId.setText(null);
								updateHairdresserModel();

							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}

			}
		});
		btn_dellHairdresser.setBackground(Color.GRAY);
		btn_dellHairdresser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_dellHairdresser.setBounds(548, 315, 117, 23);
		w_hairdresser.add(btn_dellHairdresser);

		JScrollPane w_scrollHairdresser = new JScrollPane();
		w_scrollHairdresser.setBounds(10, 11, 494, 327);
		w_hairdresser.add(w_scrollHairdresser);

		table_hairdresser = new JTable(hairdresserModel);
		w_scrollHairdresser.setViewportView(table_hairdresser);

		table_hairdresser.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_hairdresserId
							.setText(table_hairdresser.getValueAt(table_hairdresser.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});
		table_hairdresser.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_hairdresser.getValueAt(table_hairdresser.getSelectedRow(), 0).toString());
					String selectName = table_hairdresser.getValueAt(table_hairdresser.getSelectedRow(), 1).toString();
					String selectIdentitynum = table_hairdresser.getValueAt(table_hairdresser.getSelectedRow(), 2)
							.toString();
					String selectPass = table_hairdresser.getValueAt(table_hairdresser.getSelectedRow(), 3).toString();

					try {
						boolean control = staff.updateHairdresser(selectID, selectIdentitynum, selectPass, selectName);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel w_category = new JPanel();
		w_category.setBackground(Color.WHITE);
		w_tab.addTab("Category", null, w_category, null);
		w_category.setLayout(null);

		JScrollPane w_scrollcategory = new JScrollPane();
		w_scrollcategory.setBounds(10, 11, 215, 243);
		w_category.add(w_scrollcategory);

		categoryMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Update");
		JMenuItem deleteMenu = new JMenuItem("Delete");
		categoryMenu.add(updateMenu);
		categoryMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_category.getValueAt(table_category.getSelectedRow(), 0).toString());
				Category selectCategory = category.getFetch(selID);
				UpdateCategoryGUI updateGUI = new UpdateCategoryGUI(selectCategory);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateCategoryModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer
							.parseInt(table_category.getValueAt(table_category.getSelectedRow(), 0).toString());
					try {
						if (category.deleteCategory(selID)) {
							Helper.showMsg("succes");
							updateCategoryModel();

						} else {
							Helper.showMsg("error");

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		table_category = new JTable(categoryModel);
		table_category.setComponentPopupMenu(categoryMenu);
		table_category.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_category.rowAtPoint(point);
				table_category.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		w_scrollcategory.setViewportView(table_category);

		JLabel lblcategory = new JLabel("Category");
		lblcategory.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblcategory.setBounds(42, 265, 104, 27);
		w_category.add(lblcategory);

		fld_categoryName = new JTextField();
		fld_categoryName.setColumns(10);
		fld_categoryName.setBounds(156, 267, 146, 27);
		w_category.add(fld_categoryName);

		JButton btn_addcategory = new JButton("Add");
		btn_addcategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_categoryName.getText().length() == 0) {
					Helper.showMsg("fill");

				} else {
					try {
						if (category.addCategory(fld_categoryName.getText())) {
							Helper.showMsg("succes");
							fld_categoryName.setText(null);
							updateCategoryModel();

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btn_addcategory.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_addcategory.setBackground(Color.GRAY);
		btn_addcategory.setBounds(27, 303, 95, 33);
		w_category.add(btn_addcategory);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(411, 22, 254, 314);
		w_category.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_hairdresser = new JComboBox();
		select_hairdresser.setBounds(250, 42, 139, 27);
		for(int i = 0; i < staff.getHairdresserList().size(); i++) {
			select_hairdresser.addItem(new Item(staff.getHairdresserList().get(i).getId(), staff.getHairdresserList().get(i).getName()));
			
			
		}
		select_hairdresser.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
			
		});
		w_category.add(select_hairdresser);
		
		JButton btn_addWorker = new JButton("Add");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_category.getSelectedRow();
				if(selRow >= 0) {
						String selCategory = table_category.getModel().getValueAt(selRow, 0).toString();
						int selCategoryId = Integer.parseInt(selCategory);
						Item hairdresseritem = (Item) select_hairdresser.getSelectedItem();
						try {
							boolean control = staff.addWorker(hairdresseritem.getKey(), selCategoryId);
							if(control) {
								Helper.showMsg("succes");
								DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
								clearModel.setRowCount(0);
								for(int i=0; i < staff.getCategoryHairdresserList(selCategoryId).size(); i++) {
									workerData[0] = staff.getCategoryHairdresserList(selCategoryId).get(i).getId();
									workerData[1] = staff.getCategoryHairdresserList(selCategoryId).get(i).getName();
									workerModel.addRow(workerData);

								}
								table_worker.setModel(workerModel);

							}else {
								Helper.showMsg("error");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}else {
					Helper.showMsg("Please choose category.");
				}
			}
		});
		btn_addWorker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_addWorker.setBackground(Color.GRAY);
		btn_addWorker.setBounds(271, 93, 95, 33);
		w_category.add(btn_addWorker);
		
		JLabel lblCategoryName = new JLabel("Category Name");
		lblCategoryName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblCategoryName.setBounds(262, 153, 104, 27);
		w_category.add(lblCategoryName);
		
		JButton btn_workerSelect = new JButton("Choose");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_category.getSelectedRow();
				if(selRow >= 0) {
					String selCategory = table_category.getModel().getValueAt(selRow, 0).toString();
					int selCategoryId = Integer.parseInt(selCategory);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i=0; i < staff.getCategoryHairdresserList(selCategoryId).size(); i++) {
							workerData[0] = staff.getCategoryHairdresserList(selCategoryId).get(i).getId();
							workerData[1] = staff.getCategoryHairdresserList(selCategoryId).get(i).getName();
							workerModel.addRow(workerData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				}else {
					Helper.showMsg("Please choose category");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_workerSelect.setBackground(Color.GRAY);
		btn_workerSelect.setBounds(270, 192, 95, 33);
		w_category.add(btn_workerSelect);

	}

	public void updateHairdresserModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_hairdresser.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < staff.getHairdresserList().size(); i++) {
			hairdresserData[0] = staff.getHairdresserList().get(i).getId();
			hairdresserData[1] = staff.getHairdresserList().get(i).getName();
			hairdresserData[2] = staff.getHairdresserList().get(i).getIdentitynum();
			hairdresserData[3] = staff.getHairdresserList().get(i).getPassword();
			hairdresserModel.addRow(hairdresserData);
		}
	}

	public void updateCategoryModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_category.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < category.getList().size(); i++) {
			categoryData[0] = category.getList().get(i).getId();
			categoryData[1] = category.getList().get(i).getName();
			categoryModel.addRow(categoryData);

		}
	}
}
